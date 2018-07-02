package com.example.kadh.ui.work.contract;

import com.ess.filepicker.model.EssFile;
import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.person.bean.UpFieldBean;
import com.example.kadh.ui.work.adapter.ProcessItemAttAdapter;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessUserDetailBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

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

        /*
         *
         */
        void processUserDetail(String pid, String processid);

        /*
         *  流程提交
         */
        void processSubmit(List<ProcessContentBean> data, String processid, String pid, String pname);

        /*
         *  图片上传
         */
        void upLoadField(ArrayList<String> selectedPhotos, LinkedHashMap<String, UpFieldBean> savePhotos, BGASortableNinePhotoLayout bgaLytImage);

        /*
         *  附件上传
         */
        void upLoadField(ArrayList<String> selectedPhotos, LinkedHashMap<String, EssFile>  saveFile, ProcessItemAttAdapter itemAttAdapter);

    }

}
