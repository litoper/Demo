package com.example.kadh.ui.main.presenter;

import android.support.v4.app.Fragment;

import com.example.kadh.base.BasePresenterImpl;
import com.example.kadh.ui.company.fragment.CompanyFragment;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.main.bean.WeatherBean;
import com.example.kadh.ui.main.contract.MainAtyContract;
import com.example.kadh.ui.main.fragment.MainFragmentView;
import com.example.kadh.ui.work.fragment.WorkFragment;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MainPresenter extends BasePresenterImpl<MainAtyContract.View> implements MainAtyContract.Presenter<MainAtyContract.View> {

    private RxApi mRxApi;

    @Inject
    public MainPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }


    @Override
    public void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        CompanyFragment frgCompany = new CompanyFragment();
        WorkFragment frgWork = new WorkFragment();
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
        mView.showBottomNavigationBar();
    }

    @Override
    public void initPush() {

    }

    @Override
    public void initSubListener() {
        Flowable useInfo = mRxApi.getUseInfo(new SubProtect<BaseResponse<List<UserInfoBean>>>(new SubNextImpl<BaseResponse<List<UserInfoBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UserInfoBean>> response) {
            }
        }), "");

        Flowable hasUnRead = mRxApi.isHasUnRead(new SubProtect<BaseResponse<IsHasUnReadBean>>(new SubNextImpl<BaseResponse<IsHasUnReadBean>>() {
            @Override
            public void onSubSuccess(BaseResponse<IsHasUnReadBean> response) {
                if (response.data != null) {
                    mView.showBadge(response.data);
                }
            }
        }));
        Flowable weather = mRxApi.getWeather(new SubProtect<BaseResponse<List<WeatherBean>>>(new SubNextImpl<BaseResponse<List<WeatherBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<WeatherBean>> response) {
                if (!NullUtils.isNull(response.data)) {
                    mView.showWeather(response.data.get(0));
                }
            }
        }), "温州");


        mRxApi.toConcatSub(new SubProtect<BaseResponse>(new SubNextImpl<BaseResponse>() {
            @Override
            public void onSubSuccess(BaseResponse response) {
                // TODO: 2018/6/4
            }
        }), useInfo, weather, hasUnRead);
    }

}
