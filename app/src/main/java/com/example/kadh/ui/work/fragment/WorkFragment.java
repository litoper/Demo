package com.example.kadh.ui.work.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.work.adapter.WorkGridAdapter;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.ui.work.bean.ProcessStatusBean;
import com.example.kadh.ui.work.contract.WorkFragContract;
import com.example.kadh.ui.work.presenter.WorkPresenter;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.view.BadgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class WorkFragment extends BaseFragmentView<WorkPresenter> implements WorkFragContract.View {
    @BindView(R.id.fragment_main_work_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_main_work_barlayout)
    AppBarLayout mBarlayout;
    @BindView(R.id.fragment_main_work_iv_pending)
    ImageView mIvPending;
    @BindView(R.id.fragment_main_work_ll_pending)
    LinearLayout mLlPending;
    @BindView(R.id.fragment_main_work_ll_finish)
    LinearLayout mLlFinish;
    @BindView(R.id.fragment_main_work_ll_started)
    LinearLayout mLlStarted;
    @BindView(R.id.fragment_main_work_rl_recently)
    LinearLayout mRlRecently;
    @BindView(R.id.fragment_main_work_rv)
    RecyclerView mRv;
    private List<ProcessModuleBean> mModuleBeans = new ArrayList<>();
    private WorkGridAdapter mGridAdapter;
    private ProcessStatusBean mStatusBean;
    private BadgeView mBadgeView;
    private GridLayoutManager mGridLayoutManager;

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main_work;
    }

    @Override
    protected void configViews() {
        mToolbar.setTitle("工作");

    }

    @Override
    protected void initDatas() {
        mPresenter.getProcessModuletList();
        mPresenter.getProcessStatus();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showProcessModuletList(List<ProcessModuleBean> moduleBeans) {
        mModuleBeans.clear();
        mModuleBeans.addAll(moduleBeans);

        if (mGridAdapter == null) {
            mGridLayoutManager = new GridLayoutManager(mContext, 3);
            mRv.setLayoutManager(mGridLayoutManager);
            mGridAdapter = new WorkGridAdapter(R.layout.item_work_gridview, mModuleBeans);
            mRv.setAdapter(mGridAdapter);
        }

        mGridAdapter.setNewData(mModuleBeans);
    }

    @Override
    public void showProcessStatus(List<ProcessStatusBean> statusBeans) {
        mStatusBean = statusBeans.get(0);

        if (mBadgeView == null) {
            mBadgeView = new BadgeView(getActivity());
            mBadgeView.setTargetView(mIvPending);//绑定需要显示消息数量的控件
            mBadgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
        }
        mBadgeView.setBadgeCount(Integer.parseInt(NullUtils.filterNull(mStatusBean.getIncompletedNumber(), "0")));
    }
}
