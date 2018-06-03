package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.ui.login.bean.LoginBean;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.utils.RxJava.BaseResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */


public class RxApi {
    private RxApiService mRxApiService;
    private String       mVersion;

    RxApi(RxApiService rxApiService, String version) {
        mRxApiService = rxApiService;
        mVersion = version;
    }

    /**
     * login登陆
     *
     * @param subscriber
     * @param username
     * @param password
     */
    public void login(FlowableSubscriber<BaseResponse<List<LoginBean>>> subscriber, String username, String password) {
        Flowable flowable = mRxApiService.login(username, password, mVersion);
        toSubscribe(flowable, subscriber);
    }

    /**
     * getUseInfo获取个人信息
     *
     * @param subscriber
     * @param userid
     */
    public void getUseInfo(FlowableSubscriber<BaseResponse<List<UserInfoBean>>> subscriber, String userid) {
        Flowable flowable = mRxApiService.getUseInfo(userid, mVersion);
        toSubscribe(flowable, subscriber);
    }


    // subscribeOn(): 指定 subscribe() 发生在 IO 线程
    // observeOn(): 指定 Subscriber 的回调发生在主线程
    //添加线程管理并订阅
    private void toSubscribe(Flowable flowable, FlowableSubscriber subscriber) {
        flowable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
