package com.example.kadh.ui.home;

import com.example.kadh.utils.RxJava.RxApi.RxApi;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {

    private RxApi mRxApi;

    @Inject
    public MainPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }

    @Override
    public void checkVersion() {

    }

    @Override
    public void atachView(Object view) {

    }

    @Override
    public void detachView() {

    }
}
