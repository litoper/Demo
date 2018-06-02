package com.example.kadh.module;

import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxApi.RxManager;

import dagger.Module;
import dagger.Provides;

@Module
public class RxApiModule {
    private RxManager mRxManager;

    public RxApiModule() {
        mRxManager = RxManager.getInstant();
    }

    @Provides
    public RxManager provideRxApiUtils() {
        return mRxManager;
    }

    @Provides
    public RxApi provideRxApi() {
        return mRxManager.getRxApi();
    }
}
