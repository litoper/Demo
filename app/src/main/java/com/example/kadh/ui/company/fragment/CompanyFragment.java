package com.example.kadh.ui.company.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.company.adapter.BaseRecyclerAdapter;
import com.example.kadh.ui.company.adapter.SmartViewHolder;
import com.example.kadh.ui.company.contract.CompanyFragContract;
import com.example.kadh.ui.company.presenter.CompanyPresenter;
import com.example.kadh.view.LoadingLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.layout.simple_list_item_2;

public class CompanyFragment extends BaseFragmentView<CompanyPresenter> implements CompanyFragContract.View, AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public enum Item {
        ThirdParty("集成第三方控件", CompanyFragment.class),
        NestedInner("内部嵌套", CompanyFragment.class),
        NestedOuter("外部嵌套", CompanyFragment.class),;
        public String   name;
        public Class<?> clazz;

        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }
    }


    @BindView(R.id.fragment_mian_company_rv)
    RecyclerView       mRv;
    @BindView(R.id.fragment_mian_company_loading)
    LoadingLayout      mLoading;
    @BindView(R.id.fragment_mian_company_srl)
    SmartRefreshLayout mSrl;
    Unbinder unbinder;

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
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRv.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2, CompanyFragment.this) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                holder.text(android.R.id.text1, model.name());
                holder.text(android.R.id.text2, model.name);
                holder.textColorId(android.R.id.text2, R.color.blue);
            }
        });

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
