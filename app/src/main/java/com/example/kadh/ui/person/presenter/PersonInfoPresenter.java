package com.example.kadh.ui.person.presenter;

import android.Manifest;
import android.annotation.SuppressLint;

import com.example.kadh.base.BasePresenterImpl;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.person.activity.PersonInfoActivity;
import com.example.kadh.ui.person.bean.RoleManageBean;
import com.example.kadh.ui.person.contract.PersonInfoAtyContract;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/8
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PersonInfoPresenter extends BasePresenterImpl<PersonInfoAtyContract.View> implements PersonInfoAtyContract.Presenter<PersonInfoAtyContract.View> {

    private RxApi mRxApi;

    @Inject
    public PersonInfoPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }

    @Override
    public void getUseInfo() {
        mRxApi.getUseInfo(new SubProtect<BaseResponse<List<UserInfoBean>>>(new SubNextImpl<BaseResponse<List<UserInfoBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UserInfoBean>> response) {
                if (!NullUtils.isNull(response.data)) {
                    mView.showUserInfo(response.data.get(0));
                }
            }
        }), "");
    }

    @Override
    public void getRoleManageSingle(String userId) {

        mRxApi.getRoleManageSingle(new SubProtect<BaseResponse<List<RoleManageBean>>>(new SubNextImpl<BaseResponse<List<RoleManageBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<RoleManageBean>> response) {
                if (NullUtils.isNull(response.data)) {
                    mView.setRoleManageSingle(response.data.get(0));
                }
            }
        }), userId);
    }

    @SuppressLint("CheckResult")
    @Override
    public void checkPermissions(final PersonInfoActivity activity) {
        new RxPermissions(activity).requestEach(Manifest.permission.CAMERA).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {

                } else if (permission.shouldShowRequestPermissionRationale) {

                } else {

                }
            }
        });
    }
}
