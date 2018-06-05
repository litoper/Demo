package com.example.kadh.ui.company.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PublishNoticeDetailBean implements Parcelable {


    /**
     * create_time : 2017-11-29 15:32:26
     * fileList : [{"create_time":"2017-11-29 15:32:20","file_name":"IT技术中心价值观提报与审核规范-2017.11.23.xlsx","file_size":"0.02","file_uuid":"downloadFileCommon.do?fileUuid=26a528bb-0f92-4cb7-b02b-0ba07d202728"},{"create_time":"2017-11-29 15:32:23","file_name":"IT技术中心价值观提报与审核规范-2017.11.23.xlsx","file_size":"0.02","file_uuid":"downloadFileCommon.do?fileUuid=4514b4c0-f125-40f0-90bf-76feb1f392a0"},{"create_time":"2017-11-29 15:32:25","file_name":"IT技术中心价值观提报与审核规范-2017.11.23.xlsx","file_size":"0.02","file_uuid":"downloadFileCommon.do?fileUuid=2c466ad3-7a2c-4824-b459-be12516959c9"}]
     * nnews_content : <p>有下载信息</p>
     * not_notice__id : 98
     * npraise_num : 0
     * npublish_type : 40
     * nremark_num : 0
     * ntitle : 有下载信息
     * proAllowSeeId : 44398
     * proPublishId : 26534
     * psponsor : 超级管理员
     * psponsorImage : img0/M00/05/3D/ChpbMFluqbSASE4rAAAojW3IhSw614.png
     * psponsorPhone : 13888888888
     * psponsorid : 81
     * ptype : 2
     * uped : 0
     * yourUserId : 746
     */

    private String create_time;
    private String nnews_content;
    private String not_notice__id;
    private String npraise_num;
    private String npublish_type;
    private String nremark_num;
    private String ntitle;
    private String proAllowSeeId;
    private String proPublishId;
    private String psponsor;
    private String psponsorImage;
    private String psponsorPhone;
    private String psponsorid;
    private String ptype;
    private String uped;
    private String yourUserId;
    private List<FileListModel> fileList;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getNnews_content() {
        return nnews_content;
    }

    public void setNnews_content(String nnews_content) {
        this.nnews_content = nnews_content;
    }

    public String getNot_notice__id() {
        return not_notice__id;
    }

    public void setNot_notice__id(String not_notice__id) {
        this.not_notice__id = not_notice__id;
    }

    public String getNpraise_num() {
        return npraise_num;
    }

    public void setNpraise_num(String npraise_num) {
        this.npraise_num = npraise_num;
    }

    public String getNpublish_type() {
        return npublish_type;
    }

    public void setNpublish_type(String npublish_type) {
        this.npublish_type = npublish_type;
    }

    public String getNremark_num() {
        return nremark_num;
    }

    public void setNremark_num(String nremark_num) {
        this.nremark_num = nremark_num;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
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

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getUped() {
        return uped;
    }

    public void setUped(String uped) {
        this.uped = uped;
    }

    public String getYourUserId() {
        return yourUserId;
    }

    public void setYourUserId(String yourUserId) {
        this.yourUserId = yourUserId;
    }

    public List<FileListModel> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileListModel> fileList) {
        this.fileList = fileList;
    }

    public static class FileListModel implements Parcelable {
        /**
         * create_time : 2017-11-29 15:32:20
         * file_name : IT技术中心价值观提报与审核规范-2017.11.23.xlsx
         * file_size : 0.02
         * file_uuid : downloadFileCommon.do?fileUuid=26a528bb-0f92-4cb7-b02b-0ba07d202728
         */

        private String create_time;
        private String file_name;
        private String file_size;
        private String file_uuid;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
        }

        public String getFile_uuid() {
            return file_uuid;
        }

        public void setFile_uuid(String file_uuid) {
            this.file_uuid = file_uuid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.create_time);
            dest.writeString(this.file_name);
            dest.writeString(this.file_size);
            dest.writeString(this.file_uuid);
        }

        public FileListModel() {
        }

        protected FileListModel(Parcel in) {
            this.create_time = in.readString();
            this.file_name = in.readString();
            this.file_size = in.readString();
            this.file_uuid = in.readString();
        }

        public static final Parcelable.Creator<FileListModel> CREATOR = new Parcelable.Creator<FileListModel>() {
            @Override
            public FileListModel createFromParcel(Parcel source) {
                return new FileListModel(source);
            }

            @Override
            public FileListModel[] newArray(int size) {
                return new FileListModel[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.create_time);
        dest.writeString(this.nnews_content);
        dest.writeString(this.not_notice__id);
        dest.writeString(this.npraise_num);
        dest.writeString(this.npublish_type);
        dest.writeString(this.nremark_num);
        dest.writeString(this.ntitle);
        dest.writeString(this.proAllowSeeId);
        dest.writeString(this.proPublishId);
        dest.writeString(this.psponsor);
        dest.writeString(this.psponsorImage);
        dest.writeString(this.psponsorPhone);
        dest.writeString(this.psponsorid);
        dest.writeString(this.ptype);
        dest.writeString(this.uped);
        dest.writeString(this.yourUserId);
        dest.writeTypedList(this.fileList);
    }

    public PublishNoticeDetailBean() {
    }

    protected PublishNoticeDetailBean(Parcel in) {
        this.create_time = in.readString();
        this.nnews_content = in.readString();
        this.not_notice__id = in.readString();
        this.npraise_num = in.readString();
        this.npublish_type = in.readString();
        this.nremark_num = in.readString();
        this.ntitle = in.readString();
        this.proAllowSeeId = in.readString();
        this.proPublishId = in.readString();
        this.psponsor = in.readString();
        this.psponsorImage = in.readString();
        this.psponsorPhone = in.readString();
        this.psponsorid = in.readString();
        this.ptype = in.readString();
        this.uped = in.readString();
        this.yourUserId = in.readString();
        this.fileList = in.createTypedArrayList(FileListModel.CREATOR);
    }

    public static final Parcelable.Creator<PublishNoticeDetailBean> CREATOR = new Parcelable.Creator<PublishNoticeDetailBean>() {
        @Override
        public PublishNoticeDetailBean createFromParcel(Parcel source) {
            return new PublishNoticeDetailBean(source);
        }

        @Override
        public PublishNoticeDetailBean[] newArray(int size) {
            return new PublishNoticeDetailBean[size];
        }
    };
}
