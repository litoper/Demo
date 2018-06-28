package com.example.kadh.ui.company.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.company.activity.MessageActivity;
import com.example.kadh.ui.company.activity.NewsDetailActivity;
import com.example.kadh.ui.company.activity.NoticeDetailActivity;
import com.example.kadh.ui.company.adapter.PublishAdapter;
import com.example.kadh.ui.company.bean.PublishListBean;
import com.example.kadh.ui.company.contract.CompanyFragContract;
import com.example.kadh.ui.company.presenter.CompanyPresenter;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.view.LoadingLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CompanyFragment extends BaseFragmentView<CompanyPresenter> implements CompanyFragContract.View {


    @BindView(R.id.fragment_mian_company_rv)
    RecyclerView mRv;
    @BindView(R.id.fragment_mian_company_loading)
    LoadingLayout mLoading;
    @BindView(R.id.fragment_mian_company_srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.fragment_main_company_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_main_company_barlayout)
    AppBarLayout mBarlayout;

    private int mPage = 1;
    private PublishAdapter mPublishAdapter;
    private List<PublishListBean> mPublishListBeans = new ArrayList<>();
    private MenuItem mItemMsg;

    public static CompanyFragment newInstance() {
        return new CompanyFragment();
    }

    public CompanyFragment() {
    }


    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main_company;
    }

    @Override
    protected void configViews() {
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getPublishList(++mPage);
                refreshLayout.setEnableRefresh(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getPublishList(mPage = 1);
                refreshLayout.setEnableLoadMore(false);
            }
        });

        mPublishAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PublishListBean item = (PublishListBean) adapter.getItem(position);
                switch (adapter.getItemViewType(position)) {
                    case PublishListBean.NEWS:
                        Bundle newsB = new Bundle();
                        newsB.putString("publishId", item.getProPublishId());
                        openActivity(NewsDetailActivity.class, newsB);
                        break;
                    case PublishListBean.NOTICE:
                        Bundle noticeB = new Bundle();
                        noticeB.putString("publishId", item.getProPublishId());
                        openActivity(NoticeDetailActivity.class, noticeB);
                        break;
                    case PublishListBean.PROCESS:
                        break;
                    case PublishListBean.INFROM:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initDatas() {
        mPresenter.getPublishList(mPage);
        mPublishAdapter = new PublishAdapter(mPublishListBeans);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mRv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mRv.setAdapter(mPublishAdapter);
        initToolBar();
    }

    public void initToolBar() {
        mToolbar.setTitle("东经科技");
        setHasOptionsMenu(true);
        ((AppCompatActivity) mActivity).setSupportActionBar(mToolbar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_company, menu);
        mItemMsg = menu.findItem(R.id.action_message);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_message:
                openActivity(MessageActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showPublishList(List<PublishListBean> beanList, String total) {
        if (mPage == 1) {
            mPublishListBeans.clear();
        }
        mPublishListBeans.addAll(beanList);
        mPublishAdapter.setNewData(mPublishListBeans);

        mSrl.finishRefresh();
        mSrl.finishLoadMore();

        if (mPublishListBeans.size() >= Integer.parseInt(total)) {
            mSrl.setEnableLoadMore(false);
        } else {
            mSrl.setEnableLoadMore(true);
        }
        mSrl.setEnableRefresh(true);

        if (NullUtils.isNull(mPublishListBeans)) {
            mLoading.showEmpty();
        } else {
            mLoading.showContent();
        }
    }

    @Override
    public void upMessageState(String state) {
        if (mItemMsg != null) {
            switch (state) {
                case "0":
                    mItemMsg.setIcon(R.mipmap.ic_message_normal);
                    break;
                case "1":
                    mItemMsg.setIcon(R.mipmap.ic_message_red);
                    break;
                default:
                    mItemMsg.setIcon(R.mipmap.ic_message_normal);
                    break;
            }
        }

    }


}
