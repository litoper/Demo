package com.example.kadh.ui.main.contract;

import android.support.v4.app.Fragment;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.WeatherBean;

import java.util.List;

public interface MainAtyContract {

    interface View extends BaseContract.BaseView {
        void showViewPager(List<Fragment> fragmentList);

        void showBottomNavigationBar();

        void showBadge(IsHasUnReadBean unReadBean);

        void showWeather(WeatherBean weatherBean);

        void checkPermission();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void initViewPager();

        void initBottomNavigationBar();

        void initPush();

        void initSubListener();

    }
}
