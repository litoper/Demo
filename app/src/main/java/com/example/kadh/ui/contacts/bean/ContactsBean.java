package com.example.kadh.ui.contacts.bean;

import com.example.kadh.view.indexbar.bean.BaseIndexPinyinBean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/3
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactsBean extends BaseIndexPinyinBean {
    private String id;
    private String contactId;
    private String uids;
    private String uname;
    private String rname;
    private String ushort_phone;
    private String uposition;
    private String ujob;
    private String ufirstspell;
    private String uwholespell;
    private String uphone;
    private String uimage;
    private String ufdepartment_id;//tag
    private String ufdepartment;
    private String create_time;
    private String update_time;
    private boolean isCheck;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUshort_phone() {
        return ushort_phone;
    }

    public void setUshort_phone(String ushort_phone) {
        this.ushort_phone = ushort_phone;
    }

    public String getUposition() {
        return uposition;
    }

    public void setUposition(String uposition) {
        this.uposition = uposition;
    }

    public String getUjob() {
        return ujob;
    }

    public void setUjob(String ujob) {
        this.ujob = ujob;
    }

    public String getUfirstspell() {
        return ufirstspell;
    }

    public void setUfirstspell(String ufirstspell) {
        this.ufirstspell = ufirstspell;
    }

    public String getUwholespell() {
        return uwholespell;
    }

    public void setUwholespell(String uwholespell) {
        this.uwholespell = uwholespell;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getUfdepartment_id() {
        return ufdepartment_id;
    }

    public void setUfdepartment_id(String ufdepartment_id) {
        this.ufdepartment_id = ufdepartment_id;
    }

    public String getUfdepartment() {
        return ufdepartment;
    }

    public void setUfdepartment(String ufdepartment) {
        this.ufdepartment = ufdepartment;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        return "ContactsBean{" +
                "id='" + id + '\'' +
                ", contactId='" + contactId + '\'' +
                ", uids='" + uids + '\'' +
                ", uname='" + uname + '\'' +
                ", rname='" + rname + '\'' +
                ", ushort_phone='" + ushort_phone + '\'' +
                ", uposition='" + uposition + '\'' +
                ", ujob='" + ujob + '\'' +
                ", ufirstspell='" + ufirstspell + '\'' +
                ", uwholespell='" + uwholespell + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uimage='" + uimage + '\'' +
                ", ufdepartment_id='" + ufdepartment_id + '\'' +
                ", ufdepartment='" + ufdepartment + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }

    @Override
    public String getTarget() {
        return uname;
    }
}
