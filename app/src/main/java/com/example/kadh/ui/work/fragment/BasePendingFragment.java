package com.example.kadh.ui.work.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.adapter.PendingSingleAdapter;
import com.example.kadh.ui.work.bean.BacklogListBean;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.view.LoadingLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public abstract class BasePendingFragment extends BaseFragment {
    @BindView(R.id.fragment_work_process_pending_base_rv)
    RecyclerView mRv;
    @BindView(R.id.fragment_work_process_pending_base_loading)
    LoadingLayout mLoading;
    @BindView(R.id.fragment_work_process_pending_base_srl)
    SmartRefreshLayout mSrl;
    private List<BacklogListBean> mBacklogListBeans;
    private PendingSingleAdapter mSingleAdapter;
    private DividerItemDecoration mItemDecoration;
    private SubProtect<BaseResponse<List<BacklogListBean>>> mSubProtect;

    /*
     *  "0";//查询全部
     *  "3";//查询流程
     *  "4";//查询会议
     *  "5";//查询知会
     *  "6";//查询核心价值观
     *  "7";//查询张力
     *  "8";//查询目标
     */
    protected String mType = "0";
    private LinearLayoutManager mLayoutManager;

    @Override
    public int getLayoutResId() {
        mBacklogListBeans = new ArrayList<>();
        return R.layout.fragment_work_process_pending_base;
    }

    @Override
    protected void configViews() {
        mSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initDatas();
            }
        });
        mSingleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onAdapterItemClick(adapter, view, position, mBacklogListBeans.get(position));
            }
        });

    }

    public abstract void onAdapterItemClick(BaseQuickAdapter adapter, View view, int position, BacklogListBean backlogListBean);


    @Override
    protected void initDatas() {
        mSubProtect = new SubProtect<>(new SubNextImpl<BaseResponse<List<BacklogListBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<BacklogListBean>> response) {
                mSrl.finishRefresh();
                mBacklogListBeans.clear();
                mBacklogListBeans.addAll(response.data);
                mSingleAdapter.notifyDataSetChanged();
                if (NullUtils.isNull(mBacklogListBeans)) {
                    mLoading.showEmpty();
                } else {
                    mLoading.showContent();
                }
            }
        });
        RxManager.getInstant().getRxApi().getBacklogList(mSubProtect, mType);
    }

    @Override
    protected void attachView() {
        Log.e("888888", "attachView: " + mType);
        mLayoutManager = new LinearLayoutManager(mContext);
        mSingleAdapter = new PendingSingleAdapter(R.layout.item_process_pending_child, mBacklogListBeans);
        mItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mRv.setLayoutManager(mLayoutManager);
        mRv.addItemDecoration(mItemDecoration);
        mRv.setAdapter(mSingleAdapter);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("888888", "onSaveInstanceState(): " + mType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSubProtect.dispose("onDestroyView:" + mType);
    }
}
