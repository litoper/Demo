package com.example.kadh.ui.contacts.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.contacts.bean.ContractDbBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactDbAdapter extends BaseQuickAdapter<ContractDbBean, BaseViewHolder> {
    public ContactDbAdapter(int layoutResId, @Nullable List<ContractDbBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContractDbBean item) {
        helper.setText(R.id.item_contacts_tv_title, item.getTitle());
        switch (item.getLevel()) {
            case "0":
                helper.setText(R.id.item_contacts_tv_group, "公司");
                break;
            case "1":
                helper.setText(R.id.item_contacts_tv_group, "部门");
                break;
            case "2":
                helper.setText(R.id.item_contacts_tv_group, "小组");
                break;
            default:
                break;
        }
    }
}
