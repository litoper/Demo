package com.example.kadh.demo;

import com.example.kadh.utils.RxJava.RxApi.RxApiManager;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {

    private RxApiManager mRxApi;

    @Inject
    public MainPresenter(RxApiManager rxApi) {
        mRxApi = rxApi;
    }

    @Override
    public void checkVersion() {

    }
}
