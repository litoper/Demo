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
    public void onSubFalse(T t) {

    }

    @Override
    public abstract void onSubSuccess(T t);

    @Override
    public void onSubNext(T t) {

    }
}


interface SubOnNextListener<T> {

    void onSubStart();

    void onSubCompleted();

    void onSubError(Throwable e);

    void onSubFalse(T t);

    void onSubSuccess(T t);

    void onSubNext(T t);
}
