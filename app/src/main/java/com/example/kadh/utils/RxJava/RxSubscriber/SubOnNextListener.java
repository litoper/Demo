package com.example.kadh.utils.RxJava.RxSubscriber;

/**
 * author: nicai
 * email : nicaicai
 * blog  : nicaicaicai
 * time  : 2017/9/12
 * desc  :
 */

public interface SubOnNextListener<T> {

    void onSubStart();

    void onSubCompleted();

    void onSubError(Throwable e);

    void onSubFailed(T t);

    void onSubNext(T t);

    void onMsgNext(T t);
}
