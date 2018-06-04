package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.ui.login.bean.LoginBean;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.main.bean.WeatherBean;
import com.example.kadh.utils.RxJava.BaseResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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
    private String mVersion;

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
    public Flowable getUseInfo(FlowableSubscriber<BaseResponse<List<UserInfoBean>>> subscriber, String userid) {
        Flowable flowable = mRxApiService.getUseInfo(userid, mVersion);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * isHasUnRead  app端主页消息列表中是否存在未读消息
     *
     * @param subscriber
     */
    public Flowable isHasUnRead(FlowableSubscriber<BaseResponse<IsHasUnReadBean>> subscriber) {
        Flowable flowable = mRxApiService.isHasUnRead(mVersion);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getWeather 通过地点获取天气
     *
     * @param subscriber
     * @param place
     */
    public Flowable getWeather(FlowableSubscriber<BaseResponse<List<WeatherBean>>> subscriber, String place) {
        Flowable flowable = mRxApiService.getWeather(place, mVersion);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    // subscribeOn(): 指定 subscribe() 发生在 IO 线程
    // observeOn(): 指定 Subscriber 的回调发生在主线程
    private void toSubscribe(Flowable flowable, FlowableSubscriber subscriber) {
        if (subscriber != null) {
            flowable
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }

    public void toConcatSub(FlowableSubscriber subscriber, @NonNull Flowable... flowables) {
        Flowable flowable = Flowable.concatArray(flowables);
        toSubscribe(flowable, subscriber);
    }

    public void toMergeSub(FlowableSubscriber subscriber, @NonNull Flowable... flowables) {
        Flowable flowable = Flowable.mergeArray(flowables);
        toSubscribe(flowable, subscriber);
    }

}
