package com.example.kadh.ui.work.bean;

import com.example.kadh.bean.support.IsingleChoiceBean2;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessSingleChoiceBean implements IsingleChoiceBean2 {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProcessSingleChoiceBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public String displayText() {
        return getName();
    }

    @Override
    public String value() {
        return getValue();
    }
}
