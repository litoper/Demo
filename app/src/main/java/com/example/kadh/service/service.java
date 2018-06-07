package com.example.kadh.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/7
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
