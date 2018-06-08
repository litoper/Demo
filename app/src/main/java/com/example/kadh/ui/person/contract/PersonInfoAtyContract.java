package com.example.kadh.ui.person.contract;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.person.activity.PersonInfoActivity;
import com.example.kadh.ui.person.bean.RoleManageBean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/8
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public interface PersonInfoAtyContract {
    interface View extends BaseContract.BaseView {
        void showUserInfo(UserInfoBean userInfoBean);

        void setRoleManageSingle(RoleManageBean roleManageBean);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getUseInfo();

        void getRoleManageSingle(String userId);

        void checkPermissions(PersonInfoActivity consumer);
    }
}
