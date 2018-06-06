package com.example.kadh.ui.company.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class UpManListBean {
    /**
     * remarkman_id : string
     * remarkman_name : string
     * remarkman_image : string
     * remark_time : string
     */

    private String remarkman_id;
    private String remarkman_name;
    private String remarkman_image;

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    private String update_time;

    public String getRemarkman_id() {
        return remarkman_id;
    }

    public void setRemarkman_id(String remarkman_id) {
        this.remarkman_id = remarkman_id;
    }

    public String getRemarkman_name() {
        return remarkman_name;
    }

    public void setRemarkman_name(String remarkman_name) {
        this.remarkman_name = remarkman_name;
    }

    public String getRemarkman_image() {
        return remarkman_image;
    }

    public void setRemarkman_image(String remarkman_image) {
        this.remarkman_image = remarkman_image;
    }

    @Override
    public String toString() {
        return "UpManListBean{" +
                "remarkman_id='" + remarkman_id + '\'' +
                ", remarkman_name='" + remarkman_name + '\'' +
                ", remarkman_image='" + remarkman_image + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
