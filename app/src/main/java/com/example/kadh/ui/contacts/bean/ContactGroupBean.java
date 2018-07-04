package com.example.kadh.ui.contacts.bean;

import com.example.kadh.bean.support.IsingleChoiceBean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/4
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactGroupBean implements IsingleChoiceBean {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String displayText() {
        return name;
    }
}
