package com.example.kadh.ui.contacts.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContractDbBean {
    private String id;//公司 部门 小组的id
    private String level;//用于判断加载哪个页面 0公司 1部门 2小组
    private String title;//显示用的名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ContractDbBean{" +
                "id='" + id + '\'' +
                ", level='" + level + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
