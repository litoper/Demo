package com.example.kadh.ui.work.fragment.pending;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.kadh.R;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.adapter.PendingExpandableAdapter;
import com.example.kadh.ui.work.bean.BacklogListBean;
import com.example.kadh.ui.work.bean.BacklogListHeaderBean;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.view.LoadingLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
public class PendingAllFragment extends BaseFragment {
    @BindView(R.id.fragment_work_process_pending_base_rv)
    RecyclerView mRv;
    @BindView(R.id.fragment_work_process_pending_base_loading)
    LoadingLayout mLoading;
    @BindView(R.id.fragment_work_process_pending_base_srl)
    SmartRefreshLayout mSrl;
    private SubProtect<BaseResponse<List<BacklogListBean>>> mSubProtect;
    private LinearLayoutManager mLayoutManager;
    private DividerItemDecoration mItemDecoration;
    private PendingExpandableAdapter mPendingExpandableAdapter;
    private ArrayList<MultiItemEntity> mItemEntities;
    private BacklogListHeaderBean mHeaderProcess;
    private BacklogListHeaderBean mHeaderMeeting;
    private BacklogListHeaderBean mHeaderNotice;
    private BacklogListHeaderBean mHeaderCoreValue;
    private BacklogListHeaderBean mHeaderTension;
    private BacklogListHeaderBean mHeaderGoal;

    public static PendingAllFragment newInstance() {
        return new PendingAllFragment();
    }

    public PendingAllFragment() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_work_process_pending_base;
    }

    @Override
    protected void configViews() {
    }


    @Override
    protected void initDatas() {
        mLoading.showLoading();
        mSubProtect = new SubProtect<>(new SubNextImpl<BaseResponse<List<BacklogListBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<BacklogListBean>> response) {
                mItemEntities.clear();
                mHeaderProcess = new BacklogListHeaderBean("流程");
                mHeaderMeeting = new BacklogListHeaderBean("会议");
                mHeaderNotice = new BacklogListHeaderBean("知会");
                mHeaderCoreValue = new BacklogListHeaderBean("核心价值观");
                mHeaderTension = new BacklogListHeaderBean("张力");
                mHeaderGoal = new BacklogListHeaderBean("目标");

                for (BacklogListBean bean : response.data) {
                    switch (bean.getPtype()) {
                        case "3"://流程
                            mHeaderProcess.addSubItem(bean);
                            break;
                        case "4"://会议
                            mHeaderMeeting.addSubItem(bean);
                            break;
                        case "5"://知会
                            mHeaderNotice.addSubItem(bean);
                            break;
                        case "6"://核心价值观
                            mHeaderCoreValue.addSubItem(bean);
                            break;
                        case "7"://张力
                            mHeaderTension.addSubItem(bean);
                            break;
                        case "8"://目标
                            mHeaderGoal.addSubItem(bean);
                            break;
                        default:
                            break;
                    }
                }

                mItemEntities.add(mHeaderProcess);
                mItemEntities.add(mHeaderMeeting);
                mItemEntities.add(mHeaderNotice);
                mItemEntities.add(mHeaderCoreValue);
                mItemEntities.add(mHeaderTension);
                mItemEntities.add(mHeaderGoal);

                mPendingExpandableAdapter.notifyDataSetChanged();
                mPendingExpandableAdapter.expandAll();
                if (NullUtils.isNull(mItemEntities)) {
                    mLoading.showEmpty();
                } else {
                    mLoading.showContent();
                }
            }
        });
        RxManager.getInstant().getRxApi().getBacklogList(mSubProtect, "0");
    }

    @Override
    protected void attachView() {
        mItemEntities = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(mContext);
        mItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mPendingExpandableAdapter = new PendingExpandableAdapter(mItemEntities);
        mPendingExpandableAdapter.setOnItemClickListener(mPendingExpandableAdapter);
        mRv.setLayoutManager(mLayoutManager);
        mRv.addItemDecoration(mItemDecoration);
        mRv.setAdapter(mPendingExpandableAdapter);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSubProtect.dispose("onDestroyView: 0");
    }
}
