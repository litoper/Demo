package com.example.kadh.utils.RxJava.RxSubscriber;

/**
 * @author: nicai
 * @email : nicaicai
 * @blog : nicaicaicai
 * @time : 2017/12/5
 * @desc :
 */

public abstract class SubOnNextImpl<T> implements SubOnNextListener<T> {
    @Override
    public void onSubStart() {

    }

    @Override
    public void onSubCompleted() {

    }

    @Override
    public void onSubError(Throwable e) {

    }

    @Override
    public void onSubFailed(T t) {

    }

    @Override
    public abstract void onSubNext(T t);

    @Override
    public void onMsgNext(T t) {

    }
}
