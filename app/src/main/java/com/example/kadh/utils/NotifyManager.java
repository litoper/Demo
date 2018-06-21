package com.example.kadh.utils;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/21
 * @blog : http://www.nicaicaicai.com
 * @desc :观察者
 */
public class NotifyManager {
    /**
     * 单例模式
     */
    private static NotifyManager sNotifyManager;

    /**
     * 注册的接口集合，发送广播的时候都能收到
     */
    private List<INotifyListener> mNotifyListeners = new CopyOnWriteArrayList<INotifyListener>();

    /**
     * 获得单例对象
     */
    public static NotifyManager getInstance() {
        if (sNotifyManager == null) {
            sNotifyManager = new NotifyManager();
        }
        return sNotifyManager;
    }

    /**
     * 注册监听
     */
    public void registerNotify(INotifyListener iNotifyListener) {
        mNotifyListeners.add(iNotifyListener);
    }

    /**
     * 注销监听
     */
    public void unRegisterNotify(INotifyListener iNotifyListener) {
        if (mNotifyListeners.contains(iNotifyListener)) {
            mNotifyListeners.remove(iNotifyListener);
        }
    }

    /**
     * 发送广播
     */
    public void sendNotifyAction(@NonNull String type, @NonNull String value) {
        for (INotifyListener iNotifyListener : mNotifyListeners) {
            iNotifyListener.notifyAction(type, value);
        }
    }

    public interface INotifyListener {
        void notifyAction(String type, String value);
    }
}
