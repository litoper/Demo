package com.example.kadh.ui.company.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class CommentListBean {
    /**
     * id : string
     * remarkman_id : string
     * remarkman_name : string
     * remarkman_image : string
     * remark_comment : string
     * remark_time : string
     */

    private String id;
    private String remarkman_id;
    private String remarkman_name;
    private String remarkman_image;
    private String remark_comment;
    private String update_time;

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRemark_comment() {
        return remark_comment;
    }

    public void setRemark_comment(String remark_comment) {
        this.remark_comment = remark_comment;
    }

}
