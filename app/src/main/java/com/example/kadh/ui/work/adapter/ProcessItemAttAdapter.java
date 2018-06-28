package com.example.kadh.ui.work.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ess.filepicker.model.EssFile;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessItemAttAdapter extends BaseQuickAdapter<EssFile, BaseViewHolderImpl> implements BaseQuickAdapter.OnItemChildClickListener {
    public ProcessItemAttAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolderImpl helper, EssFile item) {
        helper.setText(R.id.item_process_submit_att_tv_fileName, item.getName());
        helper.addOnClickListener(R.id.item_process_submit_att_iv_delete);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        getData().remove(position);
        adapter.notifyDataSetChanged();
    }
}
