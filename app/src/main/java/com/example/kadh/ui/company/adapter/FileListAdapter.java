package com.example.kadh.ui.company.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.company.bean.PublishNoticeDetailBean;
import com.example.kadh.utils.FileUtils;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/14
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class FileListAdapter extends BaseQuickAdapter<PublishNoticeDetailBean.FileListBean, BaseViewHolder> {

    public FileListAdapter(int layoutResId, @Nullable List<PublishNoticeDetailBean.FileListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PublishNoticeDetailBean.FileListBean item) {
        helper.setText(R.id.item_att_tv_filename, item.getFile_name());
        helper.setText(R.id.item_att_tv_filesize, item.getFile_size());
        helper.setText(R.id.item_att_tv_time, item.getCreate_time());
        if (checkFileExist(item)) {
            helper.setText(R.id.item_att_tv_fileexist, "(点击查看)");
        } else {
            helper.setText(R.id.item_att_tv_fileexist, "(点击下载)");
        }
    }

    private boolean checkFileExist(PublishNoticeDetailBean.FileListBean item) {
        String filePath = FileUtils.PATH_USER + matchFileName(item.getFile_name(), item.getCreate_time(), item.getFile_name().lastIndexOf("."));
        item.setFile_path(filePath);
        return FileUtils.isFileExist(filePath);
    }

    /**
     * 向字符串里插入字符串
     */
    private String matchFileName(String filename, String time, int position) {
        StringBuffer stringBuffer = new StringBuffer(filename);
        return stringBuffer.insert(position, time).toString();
    }

}
