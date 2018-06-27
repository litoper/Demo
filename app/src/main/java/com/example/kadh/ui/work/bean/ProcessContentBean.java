package com.example.kadh.ui.work.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/27
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessContentBean implements MultiItemEntity {
    /**
     * ptype_choose : 单选1,单选2,单选3
     * ptitle : 请假类型
     * ptype : 1
     * id : 1
     * isdel : 0
     * pro_process__id : 1
     * player : ""
     * isdel_per : null
     */

    private String ptype_choose;
    private String ptitle;
    private String ptype;
    private String id;
    private String player;
    private String context;
    private String code;
    private String ptype_chooseValue;
    private String pminLength;
    private String pmaxLength;
    private String pcheck;
    private String chooseValue;

    public int getCheckPos() {
        return CheckPos;
    }

    public void setCheckPos(int checkPos) {
        CheckPos = checkPos;
    }

    private int CheckPos;

    public String getChooseValue() {
        return chooseValue;
    }

    public void setChooseValue(String chooseValue) {
        this.chooseValue = chooseValue;
    }

    public String getPtype_chooseValue() {
        return ptype_chooseValue;
    }

    public void setPtype_chooseValue(String ptype_chooseValue) {
        this.ptype_chooseValue = ptype_chooseValue;
    }

    public String getPminLength() {
        return pminLength;
    }

    public void setPminLength(String pminLength) {
        this.pminLength = pminLength;
    }

    public String getPmaxLength() {
        return pmaxLength;
    }

    public void setPmaxLength(String pmaxLength) {
        this.pmaxLength = pmaxLength;
    }

    public String getPcheck() {
        return pcheck;
    }

    public void setPcheck(String pcheck) {
        this.pcheck = pcheck;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPtype_choose() {
        return ptype_choose;
    }

    public void setPtype_choose(String ptype_choose) {
        this.ptype_choose = ptype_choose;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }


    @Override
    public String toString() {
        return "ProcessContentBean{" +
                "ptype_choose='" + ptype_choose + '\'' +
                ", ptitle='" + ptitle + '\'' +
                ", ptype='" + ptype + '\'' +
                ", id='" + id + '\'' +
                ", player='" + player + '\'' +
                ", context='" + context + '\'' +
                ", code='" + code + '\'' +
                ", ptype_chooseValue='" + ptype_chooseValue + '\'' +
                ", pminLength='" + pminLength + '\'' +
                ", pmaxLength='" + pmaxLength + '\'' +
                ", pcheck='" + pcheck + '\'' +
                ", chooseValue='" + chooseValue + '\'' +
                ", CheckPos=" + CheckPos +
                '}';
    }


    public static final int TEXT = 0; //单行文本
    public static final int MTEXT = 1;  //多行文本
    public static final int PHOTO = 2; //图片
    public static final int ATT = 3; //附件
    public static final int NORMAL = 4; //普通

    @Override
    public int getItemType() {
        switch (Integer.parseInt(getPtype())) {
            case 3:
                return TEXT;
            case 4:
                return MTEXT;
            case 6:
                return PHOTO;
            case 7:
                return ATT;
            default:
                return NORMAL;
        }
    }
}
