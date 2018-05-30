package com.example.kadh.ui;

import android.util.Log;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        Flowable
                .timer(2000, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                    }
                });
        Observable.timer(2000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d("WelcomeActivity", "aLong:" + aLong);
            }
        });
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
