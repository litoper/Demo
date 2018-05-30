package com.example.kadh.ui;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.demo.MianActivity;
import com.example.kadh.ui.login.LoginActivity;
import com.example.kadh.utils.RxJava.RxApi.RxApiManager;

import java.util.List;

import okhttp3.Cookie;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        checkCookie();
//        Observable
//                .timer(3000, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver<Long>() {
//                    @Override
//                    protected void onStart() {
//                        super.onStart();
//                    }
//
//                    @Override
//                    public void onSubNext(Long aLong) {
//                        Log.d(TAG, "onSubNext() called with: aLong = 000000000000000");
//                        checkCookie();
//                        Log.d(TAG, "onSubNext() called with: aLong = 111111111111111");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete() called");
//                        cancel();
//                    }
//                });
    }

    private void checkCookie() {
        List<Cookie> cookies = RxApiManager.getRxApi().getCookiePersistor().loadAll();
        if (cookies.isEmpty()) {
            openActivity(LoginActivity.class);
        } else {
            openActivity(MianActivity.class);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public int getLayoutId() {
        hideStatusBar();
        return R.layout.activity_welcome;
    }
}
