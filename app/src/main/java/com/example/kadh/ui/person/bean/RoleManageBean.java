package com.example.kadh.ui.person.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/8
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class RoleManageBean {
    /**
     * id : 102
     * rname : 管理员
     * rdesc : 东经科技有限公司管理员
     */

    private String id;
    private String rname;
    private String rdesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    @Override
    public String toString() {
        return "RoleManageBean{" +
                "id='" + id + '\'' +
                ", rname='" + rname + '\'' +
                ", rdesc='" + rdesc + '\'' +
                '}';
    }
}
