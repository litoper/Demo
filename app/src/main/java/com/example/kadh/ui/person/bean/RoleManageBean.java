package com.example.kadh.ui.person.bean;

import com.example.kadh.bean.support.IsingleChoiceBean1;
import com.example.kadh.utils.NullUtils;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/8
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class RoleManageBean implements IsingleChoiceBean1 {
    /**
     * id : 102
     * rname : 管理员
     * rdesc : 东经科技有限公司管理员
     */

    private String id;
    private String rname;
    private String rdesc;
    private String rtype;
    private String rdefault;

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

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getRdefault() {
        return rdefault;
    }

    public void setRdefault(String rdefault) {
        this.rdefault = rdefault;
    }

    @Override
    public String toString() {
        return "RoleManageBean{" +
                "id='" + id + '\'' +
                ", rname='" + rname + '\'' +
                ", rdesc='" + rdesc + '\'' +
                ", rtype='" + rtype + '\'' +
                ", rdefault='" + rdefault + '\'' +
                '}';
    }

    @Override
    public String displayText() {
        return NullUtils.filterEmpty(getRname());
    }
}
