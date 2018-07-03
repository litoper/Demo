package com.example.kadh.ui.work.bean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/3
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessScheuleBean {

    /**
     * processlist : [{"uname":"蒋凡毅","pstatus":"0","uimage":"img0/M00/01/6C/wKgXJlsxnnyAOoknAAAv6aqg27w380.jpg","allowid":"0ec0db11ee194c68b3b255a071d5b974","pnumber":"8719274e029c4630b668a62e5645e39b","pconsent":"0","premark_time":"2018-07-02 16:11:18","pushState":"0","premark":"暂无","pro_publish__id":"d50e3338f7a742638f1d2535cc0b4c4a","pcolorType":"0","id":"381","ufdepartment":"研发中心"},{"pconsent":"-1","premark_time":"","uname":"杜小军","premark":"暂无","pstatus":"0","roleid":"286","uimage":"img0/M00/08/A1/ChpbMFpctZaAFY8TAAHjJtKG4qg634.jpg","pcolorType":"0","allowid":"","id":"514","pnumber":"c2dbf5df275c460ab0ab2c1745379ef2","ufdepartment":"研发中心"},{"pconsent":"0","premark_time":"","uname":"郑星妹","premark":"暂无","pstatus":"0","uimage":"img0/M00/09/1B/ChpbMFqFqXWAC9e0AAKaP8-xbZs119.jpg","pcolorType":"0","allowid":"","id":"207","pnumber":"b270c1f08d4a4a43915225804b1618c8","ufdepartment":"人资中心"}]
     * create_time : 2018-07-02 16:12:06
     * processid : 1
     * prejected : 0
     * pid : dfd639bc47744d788dfdc2841ea3b593
     * ptitle : 请假申请
     */

    private String create_time;
    private String processid;
    private String prejected;
    private String pid;
    private String ptitle;
    private List<ProcesslistBean> processlist;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getPrejected() {
        return prejected;
    }

    public void setPrejected(String prejected) {
        this.prejected = prejected;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public List<ProcesslistBean> getProcesslist() {
        return processlist;
    }

    public void setProcesslist(List<ProcesslistBean> processlist) {
        this.processlist = processlist;
    }

    public static class ProcesslistBean {
        /**
         * uname : 蒋凡毅
         * pstatus : 0
         * uimage : img0/M00/01/6C/wKgXJlsxnnyAOoknAAAv6aqg27w380.jpg
         * allowid : 0ec0db11ee194c68b3b255a071d5b974
         * pnumber : 8719274e029c4630b668a62e5645e39b
         * pconsent : 0
         * premark_time : 2018-07-02 16:11:18
         * pushState : 0
         * premark : 暂无
         * pro_publish__id : d50e3338f7a742638f1d2535cc0b4c4a
         * pcolorType : 0
         * id : 381
         * ufdepartment : 研发中心
         * roleid : 286
         */

        private String uname;
        private String pstatus;
        private String uimage;
        private String allowid;
        private String pnumber;
        private String pconsent;
        private String premark_time;
        private String pushState;
        private String premark;
        private String pro_publish__id;
        private String pcolorType;
        private String id;
        private String ufdepartment;
        private String roleid;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getPstatus() {
            return pstatus;
        }

        public void setPstatus(String pstatus) {
            this.pstatus = pstatus;
        }

        public String getUimage() {
            return uimage;
        }

        public void setUimage(String uimage) {
            this.uimage = uimage;
        }

        public String getAllowid() {
            return allowid;
        }

        public void setAllowid(String allowid) {
            this.allowid = allowid;
        }

        public String getPnumber() {
            return pnumber;
        }

        public void setPnumber(String pnumber) {
            this.pnumber = pnumber;
        }

        public String getPconsent() {
            return pconsent;
        }

        public void setPconsent(String pconsent) {
            this.pconsent = pconsent;
        }

        public String getPremark_time() {
            return premark_time;
        }

        public void setPremark_time(String premark_time) {
            this.premark_time = premark_time;
        }

        public String getPushState() {
            return pushState;
        }

        public void setPushState(String pushState) {
            this.pushState = pushState;
        }

        public String getPremark() {
            return premark;
        }

        public void setPremark(String premark) {
            this.premark = premark;
        }

        public String getPro_publish__id() {
            return pro_publish__id;
        }

        public void setPro_publish__id(String pro_publish__id) {
            this.pro_publish__id = pro_publish__id;
        }

        public String getPcolorType() {
            return pcolorType;
        }

        public void setPcolorType(String pcolorType) {
            this.pcolorType = pcolorType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUfdepartment() {
            return ufdepartment;
        }

        public void setUfdepartment(String ufdepartment) {
            this.ufdepartment = ufdepartment;
        }

        public String getRoleid() {
            return roleid;
        }

        public void setRoleid(String roleid) {
            this.roleid = roleid;
        }
    }
}
