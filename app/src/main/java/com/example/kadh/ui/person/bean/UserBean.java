package com.example.kadh.ui.person.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/8
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class UserBean {
    /**
     * ucode : 86b24b
     * uids : 1000016
     * uphone : 13587653526
     * uname : 蒋凡毅
     * uimage : timg.jpg
     * uinduction : 2017-04-04
     * ubirthday : 2017-04-04
     * ufdepartment : IT技术中心
     * uemail : 420640006@qq.com
     * usex : 1
     * rname : erp测试
     */

    private String id;
    private String uids;
    private String uphone;
    private String uname;
    private String uimage;
    private String uinduction;
    private String ubirthday;
    private String ufdepartment;
    private String uemail;
    private String usex;
    private String rname;

    public String getUposition_name() {
        return uposition_name;
    }

    public void setUposition_name(String uposition_name) {
        this.uposition_name = uposition_name;
    }

    private String uposition_name;
    private String ushort_phone;


    public String getUshort_phone() {
        return ushort_phone;
    }

    public void setUshort_phone(String ushort_phone) {
        this.ushort_phone = ushort_phone;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                ", uids='" + uids + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uname='" + uname + '\'' +
                ", uimage='" + uimage + '\'' +
                ", uinduction='" + uinduction + '\'' +
                ", ubirthday='" + ubirthday + '\'' +
                ", ufdepartment='" + ufdepartment + '\'' +
                ", uemail='" + uemail + '\'' +
                ", usex='" + usex + '\'' +
                ", rname='" + rname + '\'' +
                '}';
    }

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getUinduction() {
        return uinduction;
    }

    public void setUinduction(String uinduction) {
        this.uinduction = uinduction;
    }

    public String getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(String ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUfdepartment() {
        return ufdepartment;
    }

    public void setUfdepartment(String ufdepartment) {
        this.ufdepartment = ufdepartment;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
