package com.example.kadh.utils.RxJava.RxSubscriber;

/**
 * @author: nicai
 * @email : nicaicai
 * @blog : nicaicaicai
 * @time : 2017/12/5
 * @desc :
 */

public abstract class SubNextImpl<T> implements SubNextListener<T> {
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
    public void onSubFalse(T response) {

    }

    @Override
    public abstract void onSubSuccess(T response);

    @Override
    public void onSubNext(T response) {

    }
}


interface SubNextListener<T> {

    void onSubStart();

    void onSubCompleted();

    void onSubError(Throwable e);

    void onSubFalse(T response);

    void onSubSuccess(T response);

    void onSubNext(T response);

}
