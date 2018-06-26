package com.example.kadh.ui.contacts.bean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactRecentBean {
    private String contactUpdate;
    private String updateTime;
    private List<ContactListBean> contactList;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getContactUpdate() {
        return contactUpdate;
    }

    public void setContactUpdate(String contactUpdate) {
        this.contactUpdate = contactUpdate;
    }

    public List<ContactListBean> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactListBean> contactList) {
        this.contactList = contactList;
    }

    public static class ContactListBean {
        /**
         * createtime : 2017-11-10 11:01:12.0
         * id : 1050
         * rname : Java开发工程师
         * uname : 王忠星
         * uphone : 15088967506
         * ushort_phone : 0
         */

        private String createtime;
        private String contactUserId;
        private String contactId;
        private String rname;
        private String uname;
        private String uphone;
        private String ushort_phone;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getRname() {
            return rname;
        }

        public void setRname(String rname) {
            this.rname = rname;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUphone() {
            return uphone;
        }

        public void setUphone(String uphone) {
            this.uphone = uphone;
        }

        public String getUshort_phone() {
            return ushort_phone;
        }

        public void setUshort_phone(String ushort_phone) {
            this.ushort_phone = ushort_phone;
        }

        public String getContactUserId() {
            return contactUserId;
        }

        public void setContactUserId(String contactUserId) {
            this.contactUserId = contactUserId;
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }
    }
}
