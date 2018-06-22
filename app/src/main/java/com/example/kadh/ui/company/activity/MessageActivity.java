package com.example.kadh.ui.company.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.company.adapter.MessageAdapter;
import com.example.kadh.ui.company.bean.MessageBean;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/7
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class MessageActivity extends BaseActivity {
    int mCurrentPage = 1;
    @BindView(R.id.activity_message_rv)
    RecyclerView mRv;
    @BindView(R.id.activity_message_srl)
    SmartRefreshLayout mSrl;
    //    @BindView(R.id.activity_message_loading)
//    LoadingLayout mLoading;
    private List<MessageBean> mMessageBeans = new ArrayList<>();
    private MessageAdapter mMessageAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public void configViews() {

        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getMessageList(++mCurrentPage);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getMessageList(mCurrentPage = 1);
            }
        });
    }

    @Override
    public void initDatas() {

        mMessageAdapter = new MessageAdapter(R.layout.item_message, mMessageBeans);
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(mLinearLayoutManager);
        mRv.setAdapter(mMessageAdapter);
        mSrl.autoRefresh();
    }

    private void getMessageList(int currentPage) {
        RxManager.getInstant().getRxApi().mqPushMsgList(new SubProtect<BaseResponse<List<MessageBean>>>(new SubNextImpl<BaseResponse<List<MessageBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<MessageBean>> response) {
                if (mCurrentPage == 1) {
                    mMessageBeans.clear();
                }
                mMessageBeans.addAll(response.data);
                mMessageAdapter.setNewData(mMessageBeans);
                mSrl.finishRefresh(500);
                mSrl.finishLoadMore(500);
//                mLoading.showContent();
                if (mMessageBeans.size() >= Integer.parseInt(response.total)) {
                    mSrl.setEnableLoadMore(false);
                } else {
                    mSrl.setEnableLoadMore(true);
                }
            }
        }), "10", String.valueOf(currentPage), "");
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("消息通知");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
