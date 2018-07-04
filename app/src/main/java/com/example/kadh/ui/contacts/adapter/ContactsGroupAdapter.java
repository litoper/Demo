package com.example.kadh.ui.contacts.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.contacts.bean.ContactsBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/3
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactsGroupAdapter extends BaseQuickAdapter<ContactsBean, BaseViewHolderImpl> implements BaseQuickAdapter.OnItemClickListener {
    private String mLevel;

    public ContactsGroupAdapter(int layoutResId, @Nullable List data, String level) {
        super(layoutResId, data);
        mLevel = level;
    }

    @Override
    protected void convert(BaseViewHolderImpl helper, ContactsBean item) {
        helper.setText(R.id.item_contacts_group_tv_name, item.getUname());
        // TODO: 2018/7/3
        helper.setText(R.id.item_contacts_group_tv_positional, "2".equals(mLevel) ? item.getRname() : item.getUposition());
        helper.setText(R.id.item_contacts_group_tv_phone, item.getUphone());
        if ("0".equals(item.getUshort_phone())) {
            helper.setText(R.id.item_contacts_group_tv_short, "");
        } else {
            helper.setText(R.id.item_contacts_group_tv_short, "(" + item.getUshort_phone() + ")");
        }
        helper.addOnClickListener(R.id.item_contacts_group_btn_call);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_contacts_group_btn_call:

                break;
            default:
                break;
        }
    }
}
