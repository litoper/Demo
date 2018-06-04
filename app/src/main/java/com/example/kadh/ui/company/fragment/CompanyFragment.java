package com.example.kadh.ui.company.fragment;

import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.company.contract.CompanyFragContract;
import com.example.kadh.ui.company.presenter.CompanyPresenter;

public class CompanyFragment extends BaseFragmentView<CompanyPresenter> implements CompanyFragContract.View {

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public int getLayoutResId() {
        return 0;
    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }
}
