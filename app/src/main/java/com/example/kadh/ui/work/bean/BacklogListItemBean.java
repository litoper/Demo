package com.example.kadh.ui.work.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class BacklogListItemBean extends AbstractExpandableItem<BacklogListBean> implements MultiItemEntity {

    public BacklogListItemBean(BacklogListBean bean) {
        addSubItem(bean);
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
