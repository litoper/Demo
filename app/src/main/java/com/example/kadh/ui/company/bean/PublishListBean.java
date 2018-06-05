package com.example.kadh.ui.company.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PublishListBean implements MultiItemEntity {

    /**
     * SpareA : 1
     * SpareB : 1
     * allowseTime : 2017-09-11 13:45:20.0
     * create_time : 49分钟前
     * picture : 1
     * proAllowSeeId : 12397
     * proPublishId : 4011
     * psponsor : 超级管理员
     * psponsorImage : img0/M00/05/3D/ChpbMFluqbSASE4rAAAojW3IhSw614.png
     * psponsorPhone : 13888888888
     * psponsorid : 81
     * pthing : {"pcreate_time":"2017-09-11 13:44:59","pendTime":"2017-09-11 13:54","pplace":"东京","psponsor":"超级管理员","pstartTime":"2017-09-11 13:46","pthing":"测试会议状态更改-进行中","ptitle":"测试会议状态更改-进行中"}
     * ptitle : 测试会议状态更改-进行中
     * ptype : 4
     */


    private String SpareA;
    private String SpareB;
    private String allowseTime;
    private String create_time;
    private String picture;
    private String proAllowSeeId;
    private String proPublishId;
    private String psponsor;
    private String psponsorImage;
    private String psponsorPhone;
    private String psponsorid;
    private Object pthing;
    private String ptitle;
    private String ptype;

    public String getSpareA() {
        return SpareA;
    }

    public void setSpareA(String spareA) {
        SpareA = spareA;
    }

    public String getSpareB() {
        return SpareB;
    }

    public void setSpareB(String spareB) {
        SpareB = spareB;
    }

    public String getAllowseTime() {
        return allowseTime;
    }

    public void setAllowseTime(String allowseTime) {
        this.allowseTime = allowseTime;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getProAllowSeeId() {
        return proAllowSeeId;
    }

    public void setProAllowSeeId(String proAllowSeeId) {
        this.proAllowSeeId = proAllowSeeId;
    }

    public String getProPublishId() {
        return proPublishId;
    }

    public void setProPublishId(String proPublishId) {
        this.proPublishId = proPublishId;
    }

    public String getPsponsor() {
        return psponsor;
    }

    public void setPsponsor(String psponsor) {
        this.psponsor = psponsor;
    }

    public String getPsponsorImage() {
        return psponsorImage;
    }

    public void setPsponsorImage(String psponsorImage) {
        this.psponsorImage = psponsorImage;
    }

    public String getPsponsorPhone() {
        return psponsorPhone;
    }

    public void setPsponsorPhone(String psponsorPhone) {
        this.psponsorPhone = psponsorPhone;
    }

    public String getPsponsorid() {
        return psponsorid;
    }

    public void setPsponsorid(String psponsorid) {
        this.psponsorid = psponsorid;
    }

    public Object getPthing() {
        return pthing;
    }

    public void setPthing(Object pthing) {
        this.pthing = pthing;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    @Override
    public String toString() {
        return "PublishListBean{" +
                "SpareA='" + SpareA + '\'' +
                ", SpareB='" + SpareB + '\'' +
                ", allowseTime='" + allowseTime + '\'' +
                ", create_time='" + create_time + '\'' +
                ", picture='" + picture + '\'' +
                ", proAllowSeeId='" + proAllowSeeId + '\'' +
                ", proPublishId='" + proPublishId + '\'' +
                ", psponsor='" + psponsor + '\'' +
                ", psponsorImage='" + psponsorImage + '\'' +
                ", psponsorPhone='" + psponsorPhone + '\'' +
                ", psponsorid='" + psponsorid + '\'' +
                ", pthing=" + pthing +
                ", ptitle='" + ptitle + '\'' +
                ", ptype='" + ptype + '\'' +
                '}';
    }

    public static final int NEWS = 1; //新闻
    public static final int NOTICE = 2; //公告
    public static final int PROCESS = 3; //流程
    public static final int INFROM = 4; //通知

    @Override
    public int getItemType() {
        return Integer.parseInt(getPtype());
    }
}
