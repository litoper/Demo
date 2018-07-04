package com.example.kadh.ui.contacts.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

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
public class ContactRecentAdapter extends BaseQuickAdapter<ContactRecentBean.ContactListBean, BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener {
    public ContactRecentAdapter(int layoutResId, @Nullable List<ContactRecentBean.ContactListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactRecentBean.ContactListBean item) {
        helper.setText(R.id.item_contact_recent_tv_name, item.getUname());
        helper.setText(R.id.item_contact_recent_tv_positional, item.getRname());
        helper.setText(R.id.item_contact_recent_tv_phone, item.getUphone());
        helper.addOnClickListener(R.id.swipe_btn_Delete);
//                         viewHolder.setText(R.id.general_contact_tv_name, item.getUname());
//                            viewHolder.setText(R.id.general_contact_tv_positional, item.getRname());
//                            viewHolder.setText(R.id.general_contact_tv_phone, item.getUphone());
//
//
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.swipe_btn_Delete:
                Toast.makeText(mContext, "删除待添加", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
