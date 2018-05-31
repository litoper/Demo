package com.example.kadh.app;

import android.app.Application;
import android.content.Context;

import com.example.kadh.common.AppComponent;
import com.example.kadh.common.DaggerAppComponent;
import com.example.kadh.module.AppModule;
import com.example.kadh.utils.AppUtils;
import com.example.kadh.utils.SpUtil;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class App extends Application {
    private static App sApp;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        initCompoent();
        initUtils();
    }

    private void initCompoent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initUtils() {
        AppUtils.init(this);
        SpUtil.init(this, getPackageName() + SpUtil.PREFS, Context.MODE_MULTI_PROCESS);
    }

    public static synchronized App getApp() {
        if (sApp == null) {
            sApp = new App();
        }
        return sApp;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}


