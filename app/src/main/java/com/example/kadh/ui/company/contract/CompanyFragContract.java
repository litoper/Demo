package com.example.kadh.ui.company.contract;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.company.bean.PublishListBean;

import java.util.List;

public interface CompanyFragContract {

    interface View extends BaseContract.BaseView {
        void showPublishList(List<PublishListBean> beanList, String total);

        void upMessageState(String state);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getPublishList(int page);
    }
}
