package com.example.kadh.ui.work.adapter;

import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.work.bean.ProcessDetailedBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessDetailAdapter extends BaseProcessDetaiAdapter {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data                A new list is created out of this one to avoid mutable list
     * @param processDetailedBean
     */
    public ProcessDetailAdapter(List<ProcessDetailedBean.PthingModel> data, ProcessDetailedBean processDetailedBean) {
        super(data, processDetailedBean);
    }

    @Override
    public void setSteal(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {

    }
}
