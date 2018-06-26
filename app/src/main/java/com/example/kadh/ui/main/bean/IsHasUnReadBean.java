package com.example.kadh.ui.main.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/4
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class IsHasUnReadBean {
    /**
     * unReadMsgState : string
     * waitDealState : string
     */

    private String unReadMsgState;
    private String waitDealState;

    /**
     * 通讯录更新
     * nowTime:时间戳
     * updateStatus:0 不更新  1 更新
     */
    private String nowTime;
    private String updateStatus;


    public String getUnReadMsgState() {
        return unReadMsgState;
    }

    public void setUnReadMsgState(String unReadMsgState) {
        this.unReadMsgState = unReadMsgState;
    }

    public String getWaitDealState() {
        return waitDealState;
    }

    public void setWaitDealState(String waitDealState) {
        this.waitDealState = waitDealState;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    @Override
    public String toString() {
        return "IsHasUnReadBean{" +
                "unReadMsgState='" + unReadMsgState + '\'' +
                ", waitDealState='" + waitDealState + '\'' +
                ", nowTime='" + nowTime + '\'' +
                ", updateStatus='" + updateStatus + '\'' +
                '}';
    }
}
