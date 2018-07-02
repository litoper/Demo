package com.example.kadh.ui.work.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.adapter.ProcessStartedAdapter;
import com.example.kadh.ui.work.bean.ProcessAlReadyBean;
import com.example.kadh.utils.IMEUtils;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.view.LoadingLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/2
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessStartedActivity extends BaseActivity {
    @BindView(R.id.activity_work_process_started_iv_clear)
    ImageView mIvClear;
    @BindView(R.id.activity_work_process_started_et_search)
    EditText mEtSearch;
    @BindView(R.id.activity_work_process_started_rv_process)
    RecyclerView mRvProcess;
    @BindView(R.id.activity_work_process_started_loading)
    LoadingLayout mLoading;
    @BindView(R.id.activity_work_process_started_srl)
    SmartRefreshLayout mSrl;
    private String mType = "";//搜索关键词
    private String mKeyword = "";//数据筛选（0-全部 1-已同意 2-被驳回 3-进行中）
    private int mPage = 1;
    private ProcessStartedAdapter mStartedAdapter;
    private List<ProcessAlReadyBean> mReadyBeanList = new ArrayList<>();

    @Override
    public void configViews() {
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getProcessAlReadyList(++mPage);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getProcessAlReadyList(mPage = 1);
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    IMEUtils.hideSoftInput(ProcessStartedActivity.this);
                    mKeyword = mEtSearch.getText().toString();
                    getProcessAlReadyList(mPage = 1);
                    return true;
                }
                return false;
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mIvClear.setVisibility(View.GONE);
                } else {
                    mIvClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mIvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyword = "";
                mEtSearch.setText("");
                getProcessAlReadyList(mPage = 1);
            }
        });
    }

    @Override
    public void initDatas() {
        getProcessAlReadyList(mPage = 1);
    }

    private void getProcessAlReadyList(int i) {
        RxManager.getInstant().getRxApi().getProcessAlReadyList(new SubProtect<BaseResponse<List<ProcessAlReadyBean>>>(new SubNextImpl<BaseResponse<List<ProcessAlReadyBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<ProcessAlReadyBean>> response) {
                mSrl.finishLoadMore();
                mSrl.finishRefresh();

                if (NullUtils.isNull(response.data)) {
                    mLoading.showEmpty();
                    mSrl.setEnableLoadMore(false);
                    return;
                }

                if (mPage == 1) {
                    mReadyBeanList.clear();
                }

                mReadyBeanList.addAll(response.data);

                if (mStartedAdapter == null) {
                    mStartedAdapter = new ProcessStartedAdapter(R.layout.item_process_started, mReadyBeanList);
                    mRvProcess.setLayoutManager(new LinearLayoutManager(mContext));
                    mRvProcess.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
                    mRvProcess.setAdapter(mStartedAdapter);
                    mLoading.showContent();
                } else {
                    mStartedAdapter.setNewData(mReadyBeanList);
                }

                if (mReadyBeanList.size() >= Integer.parseInt(response.total)) {
                    mSrl.setEnableLoadMore(false);
                } else {
                    mSrl.setEnableLoadMore(true);
                }
            }
        }), mType, mKeyword, String.valueOf(mPage));
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
        mCommonToolbar.setTitle("已发起");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_process_started;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
