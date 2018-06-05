package com.example.kadh.ui.work.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessStatusBean {
    private String incompletedNumber;
    private String dealNumber;
    private String completedNumber;

    public String getIncompletedNumber() {
        return incompletedNumber;
    }

    public void setIncompletedNumber(String incompletedNumber) {
        this.incompletedNumber = incompletedNumber;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    public String getCompletedNumber() {
        return completedNumber;
    }

    public void setCompletedNumber(String completedNumber) {
        this.completedNumber = completedNumber;
    }

    @Override
    public String toString() {
        return "ProcessStatusBean{" +
                "incompletedNumber='" + incompletedNumber + '\'' +
                ", dealNumber='" + dealNumber + '\'' +
                ", completedNumber='" + completedNumber + '\'' +
                '}';
    }
}
