package com.example.kadh.ui.work.activity.pending;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.adapter.ProcessDetailAdapter;
import com.example.kadh.ui.work.bean.ProcessDetailedBean;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.view.LoadingLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PendingDealProcessActivity extends BaseActivity {
    @BindView(R.id.activity_pending_deal_process_btn_reject)
    Button mBtnReject;
    @BindView(R.id.activity_pending_deal_process_btn_agree)
    Button mBtnAgree;
    @BindView(R.id.activity_pending_deal_process_rv)
    RecyclerView mRv;
    @BindView(R.id.activity_pending_deal_process_loading)
    LoadingLayout mLoading;
    private String mId;
    private String mAllowid;
    private ProcessDetailedBean mProcessDetailedBean;
    private ProcessDetailAdapter mProcessDetaiAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        mId = getIntent().getStringExtra("id");
        mAllowid = getIntent().getStringExtra("allowid");
        getIntent().getStringExtra("ptype");

        RxManager.getInstant().getRxApi().publishHandledDetailed(new SubProtect<BaseResponse<ProcessDetailedBean>>(new SubNextImpl<BaseResponse<ProcessDetailedBean>>() {
            @Override
            public void onSubSuccess(BaseResponse<ProcessDetailedBean> response) {
                if (mProcessDetaiAdapter == null) {
                    mProcessDetaiAdapter = new ProcessDetailAdapter(response.data.getPthing(), response.data);
                    mLayoutManager = new LinearLayoutManager(mContext);
                    mRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
                    mRv.setLayoutManager(mLayoutManager);
                    mRv.setAdapter(mProcessDetaiAdapter);
                    mLoading.showContent();
                } else {
                    mProcessDetaiAdapter.notifyDataSetChanged();
                }
            }
        }), mId, NullUtils.filterNull(mAllowid));
    }

    @Override
    public void initToolBar() {

        mCommonToolbar.setTitle("流程审批");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    protected void attachView() {


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pending_deal_process;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @OnClick({R.id.activity_pending_deal_process_btn_reject, R.id.activity_pending_deal_process_btn_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_pending_deal_process_btn_reject:
                break;
            case R.id.activity_pending_deal_process_btn_agree:
                break;
        }
    }
}
