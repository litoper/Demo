package com.example.kadh.ui.work.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class BacklogListBean {
    private String id;//待办id
    private String ptitle;// 事项名称
    private String ptitle_remark;// 事项备注
    private String psponsor;//事项发起人
    private String create_time;//事项时间
    private String allowid;//允许查看id(用于设置已阅和操作) ,
    private String preaded;//流程是否已阅(0-未阅 1-已阅) ,
    private String pstatus;// 状态 4(0-待确认、1-未签到、2-未签退) ,
    private String ptype;//: 类型 3-流程 4-会议 5-知会 6-价值观 7-张力 8-目标
    private String type;//:
    private String markNode;// markNode 0-正常张力审核 1-价值张力审核.


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

    public String getPsponsor() {
        return psponsor;
    }

    public void setPsponsor(String psponsor) {
        this.psponsor = psponsor;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getAllowid() {
        return allowid;
    }

    public void setAllowid(String allowid) {
        this.allowid = allowid;
    }

    public String getPreaded() {
        return preaded;
    }

    public void setPreaded(String preaded) {
        this.preaded = preaded;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPtitle_remark() {
        return ptitle_remark;
    }

    public void setPtitle_remark(String ptitle_remark) {
        this.ptitle_remark = ptitle_remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarkNode() {
        return markNode;
    }

    public void setMarkNode(String markNode) {
        this.markNode = markNode;
    }

    @Override
    public String toString() {
        return "BacklogListBean{" +
                "id='" + id + '\'' +
                ", ptitle='" + ptitle + '\'' +
                ", ptitle_remark='" + ptitle_remark + '\'' +
                ", psponsor='" + psponsor + '\'' +
                ", create_time='" + create_time + '\'' +
                ", allowid='" + allowid + '\'' +
                ", preaded='" + preaded + '\'' +
                ", pstatus='" + pstatus + '\'' +
                ", ptype='" + ptype + '\'' +
                ", type='" + type + '\'' +
                ", markNode='" + markNode + '\'' +
                '}';
    }
}
