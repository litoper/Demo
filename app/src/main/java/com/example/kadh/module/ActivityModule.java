package com.example.kadh.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }
}
