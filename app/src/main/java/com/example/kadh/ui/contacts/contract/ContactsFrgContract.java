package com.example.kadh.ui.contacts.contract;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.contacts.bean.ContactRecentBean;
import com.example.kadh.ui.contacts.bean.ContractDbBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public interface ContactsFrgContract {
    interface View extends BaseContract.BaseView {
        void showDataFromDb(List<ContractDbBean> dbBeans);

        void showRecentContact(ContactRecentBean data);

    }

    interface Presenter extends BaseContract.BasePresenter {
        void queryDataFromDb();

        void getRecentContact();
    }
}
