package com.example.kadh.demo;

import com.example.kadh.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void processVersion();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void checkVersion();
    }
}
