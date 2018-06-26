package com.example.kadh.ui.person.presenter;

import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.person.bean.RoleManageBean;
import com.example.kadh.ui.person.bean.UpFieldBean;
import com.example.kadh.ui.person.contract.PersonInfoAtyContract;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/8
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PersonInfoPresenter extends BaseBindingImpl<PersonInfoAtyContract.View> implements PersonInfoAtyContract.Presenter<PersonInfoAtyContract.View> {

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
                if (!NullUtils.isNull(response.data)) {
                    mView.setRoleManageSingle(response.data);
                }
            }
        }), userId);
    }


    @Override
    public void upField(String cropFilePath) {
        File file = new File(cropFilePath);
        RequestBody body = RequestBody.create(MediaType.parse("form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), body);
        RxManager.getInstant().getRxApi().upField(new SubProtect<BaseResponse<List<UpFieldBean>>>(new SubNextImpl<BaseResponse<List<UpFieldBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UpFieldBean>> response) {
                mView.upFiledSuccess(response.data);
            }
        }), part, "");
    }
}
