package com.example.kadh.ui.person.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/22
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class UpFieldBean {
    private String code;
    private String fileName;
    private String oa_file__id;
    private String localPath;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOa_file__id() {
        return oa_file__id;
    }

    public void setOa_file__id(String oa_file__id) {
        this.oa_file__id = oa_file__id;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    @Override
    public String toString() {
        return "UpFieldBean{" +
                "code='" + code + '\'' +
                ", fileName='" + fileName + '\'' +
                ", oa_file__id='" + oa_file__id + '\'' +
                '}';
    }
}
