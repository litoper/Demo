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
        /**
         * 初始化主页面vp容器
         */
        void initViewPager();

        /**
         * 初始化主页面底部栏
         */
        void initBottomNavigationBar();

        void initPush();

        void initSubListener();

        /**
         * 获取个人信息
         */
        void getUseInfo();

        /**
         * 获取底部及待办小红点,通讯录更新时间
         */
        void isHasUnRead();

        /**
         * 获取天气
         */
        void getWeather();


        /**
         * 通讯录更新
         *
         * @param nowTime
         */
        void getContactUpdateData(String nowTime);


    }
}
