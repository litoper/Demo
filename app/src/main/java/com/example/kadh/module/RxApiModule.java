package com.example.kadh.module;

import com.example.kadh.utils.RxJava.RxApi.RxApi;
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
    public RxApi provideRxApi() {
        return mRxApiUtils.getRxApi();
    }
}
