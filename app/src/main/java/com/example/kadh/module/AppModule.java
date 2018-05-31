package com.example.kadh.module;

import android.content.Context;

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


    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
