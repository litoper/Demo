package com.example.kadh.ui.main.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.kadh.base.BasePresenterImpl;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.main.contract.MainAtyContract;
import com.example.kadh.ui.main.fragment.MainFragmentView;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubDialog;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProgress;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainAtyContract.View> implements MainAtyContract.Presenter<MainAtyContract.View> {

    private RxApi    mRxApi;
    private Activity mActivity;

    @Inject
    public MainPresenter(RxApi rxApi, Activity activity) {
        mRxApi = rxApi;
        mActivity = activity;
    }


    @Override
    public void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        MainFragmentView frgCompany = new MainFragmentView("公司");
        MainFragmentView frgWork = new MainFragmentView("工作");
        MainFragmentView frgTxl = new MainFragmentView("通讯录");
        MainFragmentView frgMy = new MainFragmentView("我的");

        fragmentList.add(frgCompany);
        fragmentList.add(frgWork);
        fragmentList.add(frgTxl);
        fragmentList.add(frgMy);

        mView.showViewPager(fragmentList);
    }

    @Override
    public void initBottomNavigationBar() {

    }

    @Override
    public void getUseInfo() {

        SubProgress<BaseResponse<List<UserInfoBean>>> subProgress = new SubProgress<>(mActivity, SubDialog.LOGINING, new
                SubNextImpl<BaseResponse<List<UserInfoBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UserInfoBean>> response) {

            }
        });
        mRxApi.getUseInfo(subProgress, "");
    }

    @Override
    public void getUserConfigInfo() {

    }
}
