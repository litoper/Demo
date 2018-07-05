package com.example.kadh.ui.work.fragment.pending;

import android.support.v7.widget.RecyclerView;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.adapter.ProcessPendingSingleAdapter;
import com.example.kadh.ui.work.bean.BacklogListBean;
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
    private List<BacklogListBean> mBacklogListBeans;
    private ProcessPendingSingleAdapter mAllAdapter;
    private SubProtect<BaseResponse<List<BacklogListBean>>> mSubProtect;

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
        mBacklogListBeans = new ArrayList<>();
        mSubProtect = new SubProtect<>(new SubNextImpl<BaseResponse<List<BacklogListBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<BacklogListBean>> response) {
                mBacklogListBeans.clear();
                mBacklogListBeans.addAll(response.data);
                mAllAdapter.notifyDataSetChanged();
                if (NullUtils.isNull(mBacklogListBeans)) {
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
