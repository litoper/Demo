package com.example.kadh.base;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/1
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class BasePresenterImpl<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected T mView;

    public BasePresenterImpl() {
    }

    @Override
    public void atachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}