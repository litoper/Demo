package com.example.kadh.component;

import android.content.Context;

import com.example.kadh.module.AppModule;
import com.example.kadh.module.RxApiModule;
import com.example.kadh.utils.RxJava.RxApi.RxApiManager;
import com.example.kadh.utils.RxJava.RxApi.RxApiUtils;

import dagger.Component;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/31
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

@Component(modules = {AppModule.class, RxApiModule.class})
public interface AppComponent {
    Context getContext();

    RxApiUtils getRxApiUtils();

    RxApiManager getRxApi();
}
