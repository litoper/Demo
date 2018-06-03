package com.example.kadh.ui.main.contract;

import android.support.v4.app.Fragment;

import com.example.kadh.base.BaseContract;

import java.util.List;

public interface MainAtyContract {

    interface View extends BaseContract.BaseView {
        void showViewPager(List<Fragment> fragmentList);

        void showBottomNavigationBar();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void initViewPager();

        void initBottomNavigationBar();

        void getUseInfo();

        void getUserConfigInfo();
    }
}
