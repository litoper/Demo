package com.example.kadh.bean;

import com.example.kadh.bean.base.BaseBean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginBean extends BaseBean {
    private String token;
    private String userName;
    private String userIco;
    private String pushID;
    private String userid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIco() {
        return userIco;
    }

    public void setUserIco(String userIco) {
        this.userIco = userIco;
    }

    public String getPushID() {
        return pushID;
    }

    public void setPushID(String pushID) {
        this.pushID = pushID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "token='" + token + '\'' +
                ", userName='" + userName + '\'' +
                ", userIco='" + userIco + '\'' +
                ", pushID='" + pushID + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
