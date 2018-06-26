package com.example.kadh.ui.contacts.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.contacts.bean.ContactRecentBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactRecentAdapter extends BaseQuickAdapter<ContactRecentBean.ContactListBean, BaseViewHolder> {
    public ContactRecentAdapter(int layoutResId, @Nullable List<ContactRecentBean.ContactListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactRecentBean.ContactListBean item) {
        helper.setText(R.id.item_contact_recent_tv_name, item.getUname());
        helper.setText(R.id.item_contact_recent_tv_positional, item.getRname());
        helper.setText(R.id.item_contact_recent_tv_phone, item.getUphone());
//                         viewHolder.setText(R.id.general_contact_tv_name, item.getUname());
//                            viewHolder.setText(R.id.general_contact_tv_positional, item.getRname());
//                            viewHolder.setText(R.id.general_contact_tv_phone, item.getUphone());
//
//
    }


}
