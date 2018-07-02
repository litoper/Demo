package com.example.kadh.ui.work.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/2
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessAlReadyBean {
    private String id;
    private String ptitle;
    private String create_time;
    private String prejected;
    private String ptitle_remark;
    private String publishId;

    public String getPtitle_remark() {
        return ptitle_remark;
    }

    public void setPtitle_remark(String ptitle_remark) {
        this.ptitle_remark = ptitle_remark;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPcreate_time() {
        return create_time;
    }

    public void setPcreate_time(String pcreate_time) {
        this.create_time = pcreate_time;
    }

    public String getPrejected() {
        return prejected;
    }

    public void setPrejected(String prejected) {
        this.prejected = prejected;
    }

    @Override
    public String toString() {
        return "ProcessAlReadyBean{" +
                "id='" + id + '\'' +
                ", ptitle='" + ptitle + '\'' +
                ", create_time='" + create_time + '\'' +
                ", prejected='" + prejected + '\'' +
                ", ptitle_remark='" + ptitle_remark + '\'' +
                ", publishId='" + publishId + '\'' +
                '}';
    }
}
