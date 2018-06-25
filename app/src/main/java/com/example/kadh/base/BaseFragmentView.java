package com.example.kadh.base;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/1
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public abstract class BaseFragmentView<T extends BaseContract.BaseBinding> extends BaseFragment {

    @Inject
    protected T mPresenter;

    public BaseFragmentView() {
    }

    /**
     * 此方法不可再重写
     */
    @Override
    protected void attachView() {
        if (mPresenter != null) {
            mPresenter.atachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
