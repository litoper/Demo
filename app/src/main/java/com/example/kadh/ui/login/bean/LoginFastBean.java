package com.example.kadh.ui.login.bean;

import com.example.kadh.bean.support.IsingleChoiceBean1;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginFastBean implements IsingleChoiceBean1 {

    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String displayText() {
        return getUserName();
    }
}
