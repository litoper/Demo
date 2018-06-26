package com.example.kadh.ui.work.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessListBean {
    private String pico;//图标
    private String pdetail;//流程描述
    private String pdepartmentName;//流程创建部门中文名
    private String pname;//流程名称
    private String pdepartment;//流程创建部门
    private String id;//唯一id


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPico() {
        return pico;
    }

    public void setPico(String pico) {
        this.pico = pico;
    }

    public String getPdetail() {
        return pdetail;
    }

    public void setPdetail(String pdetail) {
        this.pdetail = pdetail;
    }

    public String getPdepartment() {
        return pdepartment;
    }

    public void setPdepartment(String pdepartment) {
        this.pdepartment = pdepartment;
    }

    public String getPdepartmentName() {
        return pdepartmentName;
    }

    public void setPdepartmentName(String pdepartmentName) {
        this.pdepartmentName = pdepartmentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProcessListBean{" +
                "pico='" + pico + '\'' +
                ", pdetail='" + pdetail + '\'' +
                ", pdepartmentName='" + pdepartmentName + '\'' +
                ", pname='" + pname + '\'' +
                ", pdepartment='" + pdepartment + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
