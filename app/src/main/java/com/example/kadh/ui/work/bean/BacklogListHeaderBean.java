package com.example.kadh.ui.work.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.kadh.utils.NullUtils;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class BacklogListHeaderBean extends AbstractExpandableItem<BacklogListBean> implements MultiItemEntity {

    private String mTitle;

    public BacklogListHeaderBean(String title) {
        mTitle = title;
    }


    public String getTitle() {
        return mTitle;
    }

    public int getSize() {
        return NullUtils.isNull(mSubItems) ? 0 : mSubItems.size();
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
