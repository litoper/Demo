package com.example.kadh.module;

import android.content.Context;

import com.example.kadh.utils.RxJava.RxApi.RxApiManager;
import com.example.kadh.utils.RxJava.RxApi.RxApiUtils;

import dagger.Module;
import dagger.Provides;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/31
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

@Module
public class AppModule {
    private Context context;
    private RxApiUtils mRxApiUtils;

    public AppModule(Context context) {
        this.context = context;
        mRxApiUtils = RxApiUtils.getRxApiUtils();
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public RxApiUtils provideRxApiUtils() {
        return mRxApiUtils;
    }

    @Provides
    public RxApiManager provideRxApiManager() {
        return mRxApiUtils.getRxApiManager();
    }
}
