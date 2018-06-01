package com.example.kadh.ui.login.contract;

import com.example.kadh.base.BaseContract;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/1
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public interface LoginFragContract {

    interface View extends BaseContract.BaseView {
        void setSpData(String username, String passwrod);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getSpData();

        void login(String username, String password);
    }
}
