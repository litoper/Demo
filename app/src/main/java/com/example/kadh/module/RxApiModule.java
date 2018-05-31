package com.example.kadh.module;

import com.example.kadh.utils.RxJava.RxApi.RxApiManager;
import com.example.kadh.utils.RxJava.RxApi.RxApiUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class RxApiModule {
    private RxApiUtils mRxApiUtils;
    public RxApiModule() {
        mRxApiUtils = RxApiUtils.getRxApiUtils();
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
