package com.example.kadh.ui.home;

import com.example.kadh.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void processVersion();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void checkVersion();
    }
}
