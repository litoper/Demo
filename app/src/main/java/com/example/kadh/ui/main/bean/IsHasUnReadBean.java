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

    @Override
    public String toString() {
        return "IsHasUnReadBean{" +
                "unReadMsgState='" + unReadMsgState + '\'' +
                ", waitDealState='" + waitDealState + '\'' +
                '}';
    }
}
