package com.example.kadh.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/1
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class BaseBindingImpl<T extends BaseContract.BaseView> implements BaseContract.BaseBinding<T> {
    protected T mView;
    protected CompositeDisposable mDisposable;

    public BaseBindingImpl() {
    }

    protected void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    protected void addSubscrebe(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);
    }

    @Override
    public void atachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }
}
