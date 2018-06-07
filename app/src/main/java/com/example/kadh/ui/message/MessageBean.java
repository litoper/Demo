package com.example.kadh.ui.message;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/7
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class MessageBean {
    /**
     * userid : string
     * state : string
     * createtime : string
     * msg : string
     * title : string
     * extraField : string
     */

    private String userid;
    private String state;
    private String createtime;
    private String msg;
    private String title;

    public Object getExtraField() {
        return extraField;
    }

    public void setExtraField(Object extraField) {
        this.extraField = extraField;
    }

    private Object extraField;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "userid='" + userid + '\'' +
                ", state='" + state + '\'' +
                ", createtime='" + createtime + '\'' +
                ", msg='" + msg + '\'' +
                ", title='" + title + '\'' +
                ", extraField=" + extraField +
                '}';
    }
}
