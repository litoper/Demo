package com.example.kadh.ui.person.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class QueryProTotalInfoBean {
    /**
     * pro_qingjia : 2.0
     * pro_jiaban : 0.0
     */

    private String pro_qingjia;
    private String pro_jiaban;

    public String getPro_qingjia() {
        return pro_qingjia;
    }

    public void setPro_qingjia(String pro_qingjia) {
        this.pro_qingjia = pro_qingjia;
    }

    public String getPro_jiaban() {
        return pro_jiaban;
    }

    public void setPro_jiaban(String pro_jiaban) {
        this.pro_jiaban = pro_jiaban;
    }

    @Override
    public String toString() {
        return "QueryProTotalInfoBean{" +
                "pro_qingjia='" + pro_qingjia + '\'' +
                ", pro_jiaban='" + pro_jiaban + '\'' +
                '}';
    }
}
