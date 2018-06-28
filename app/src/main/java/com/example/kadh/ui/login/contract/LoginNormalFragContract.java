package com.example.kadh.ui.login.contract;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.login.bean.LoginBean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/1
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public interface LoginNormalFragContract {

    interface View extends BaseContract.BaseView {
        void setLoginData(String username, String passwrod);

        void checkInput(String username, String passwrod);

        void loginSuccess();

        void loginFail();

        void showFastDialog();
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void getLoginData();

        void saveLoginData(String username, String passwrod, LoginBean data);

        void login(String username, String password);
    }
}
