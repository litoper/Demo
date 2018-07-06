package com.example.kadh.ui.work.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.kadh.utils.NullUtils;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessDetailedBean {
    public static final int UNDEFINE = -1;//未定义
    public static final int NORMAL = 0;//普通
    public static final int TEXT = 1;//多文本
    public static final int PHOTO = 2;//图片
    public static final int ATT = 3;//附件
    public static final int URL_ = 4;//URL
    public static final int SPACE = 5;//间距
    public static final int NOTIFY = 6;//知会人
    public static final int TENSION = 7;//张力责任部门、责任人、完成时间
    public static final int EVALUATE = 8;//张力评分
    public static final int APPEAL = 9;//申诉
    public static final int UPGRADE = 10;//升级


    private String publishId;
    private String allowId;

    //张力相关
    private String isEvaluate;
    private String status;
    private String depId;
    private String depName;
    private String submit_man_role_id;
    private String proposal;
    private String tensionType;
    private String buttonShowType;
    private String isAppeal;
    private String tensionStatus;

    //会议相关
    private String meetStatus;
    private String pstatus;

    //价值观相关
    private String defaultscore;
    private String score;


    private List<PthingModel> pthing;


    public String getIsAppeal() {
        return isAppeal;
    }

    public void setIsAppeal(String isAppeal) {
        this.isAppeal = isAppeal;
    }

    public String getTensionStatus() {
        return tensionStatus;
    }

    public void setTensionStatus(String tensionStatus) {
        this.tensionStatus = tensionStatus;
    }

    public String getSubmit_man_role_id() {
        return submit_man_role_id;
    }

    public void setSubmit_man_role_id(String submit_man_role_id) {
        this.submit_man_role_id = submit_man_role_id;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public String getTensionType() {
        return tensionType;
    }

    public void setTensionType(String tensionType) {
        this.tensionType = tensionType;
    }

    public String getButtonShowType() {
        return buttonShowType;
    }

    public void setButtonShowType(String buttonShowType) {
        this.buttonShowType = buttonShowType;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(String isEvaluate) {
        this.isEvaluate = isEvaluate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeetStatus() {
        return meetStatus;
    }

    public void setMeetStatus(String meetStatus) {
        this.meetStatus = meetStatus;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getDefaultscore() {
        return defaultscore;
    }

    public void setDefaultscore(String defaultscore) {
        this.defaultscore = defaultscore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<PthingModel> getPthing() {
        return pthing;
    }

    public void setPthing(List<PthingModel> pthing) {
        this.pthing = pthing;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public String getAllowId() {
        return allowId;
    }

    public void setAllowId(String allowId) {
        this.allowId = allowId;
    }

    public static class PthingModel implements MultiItemEntity {

        private Object context;
        private String title;
        private String pstatus;
        private String pvalue;
        private String create_time;
        private String type;

        public Object getContext() {
            return context;
        }

        public void setContext(Object context) {
            this.context = context;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPstatus() {
            return pstatus;
        }

        public void setPstatus(String pstatus) {
            this.pstatus = pstatus;
        }

        public String getPvalue() {
            return pvalue;
        }

        public void setPvalue(String pvalue) {
            this.pvalue = pvalue;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int getItemType() {
            switch (NullUtils.filterEmpty(getType())) {
                case "1":
                    return NORMAL;
                case "2":
                    return NORMAL;
                case "3":
                    return NORMAL;
                case "5":
                    return NORMAL;
                case "4":
                    return TEXT;
                case "6":
                    return PHOTO;
                case "7":
                    return ATT;
                case "8":
                    return URL_;
                case "9":
                    return SPACE;
                case "10":
                    return NOTIFY;
                case "11":
                    return TENSION;
                case "12":
                    return EVALUATE;
                case "13":
                    return APPEAL;
                case "14":
                    return UPGRADE;
            }
            return 0;
        }
    }
}
