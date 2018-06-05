package com.example.kadh.ui.work.contract;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.ui.work.bean.ProcessStatusBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public interface WorkFragContract {
    interface View extends BaseContract.BaseView {
        void showProcessModuletList(List<ProcessModuleBean> moduleBeans);

        void showProcessStatus(List<ProcessStatusBean> statusBeans);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getProcessModuletList();

        void getProcessStatus();
    }
}
