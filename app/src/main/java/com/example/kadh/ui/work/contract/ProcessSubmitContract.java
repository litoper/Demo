package com.example.kadh.ui.work.contract;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessUserDetailBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/27
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public interface ProcessSubmitContract {

    interface View extends BaseContract.BaseView {
        void showProcessContent(List<ProcessContentBean> contentBeans);
    }

    interface Presenter extends BaseContract.BasePresenter {
        void getProcessContent(String processid, List<ProcessUserDetailBean> data);

        void processUserDetail(String pid, String processid);

        void processSubmit(List<ProcessContentBean> data, String processid, String pid, String pname);

        void checkSubmitData(List<ProcessContentBean> data);
    }

}
