package com.example.kadh.base;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public abstract class BaseActivityView<T extends BaseContract.BaseBinding> extends BaseActivity {


    @Inject
    protected T mPresenter;

    public BaseActivityView() {
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
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
