package com.example.kadh.ui.work.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/27
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessUserDetailBean {
    /**
     * title : 选择发起角色
     * pvalue : 409
     * type : 1
     * context : OA组成员
     */

    private String title;
    private String pvalue;
    private String type;
    private String context;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "ProcessUserDetailBean{" +
                "title='" + title + '\'' +
                ", pvalue='" + pvalue + '\'' +
                ", type='" + type + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
