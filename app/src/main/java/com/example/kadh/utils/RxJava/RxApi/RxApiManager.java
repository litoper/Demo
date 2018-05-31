package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.ui.login.LoginModel;
import com.example.kadh.utils.RxJava.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */


public class RxApiManager {
    private RxApiService mRxApiService;
    private String mVersion;

    RxApiManager(RxApiService rxApiService, String version) {
        mRxApiService = rxApiService;
        mVersion = version;
    }

    public void getCode(Observer<BaseResponse<String>> observer) {
        Observable observable = mRxApiService.checkVersion(mVersion);
        toSubscribe(observable, observer);
    }


    public void login(Observer<BaseResponse<List<LoginModel>>> observer, String username, String password) {
        Observable observable = mRxApiService.login(username, password, mVersion);
        toSubscribe(observable, observer);
    }


    // subscribeOn(): 指定 subscribe() 发生在 IO 线程
    // observeOn(): 指定 Subscriber 的回调发生在主线程
    //添加线程管理并订阅
    private void toSubscribe(Observable observable, Observer observer) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
