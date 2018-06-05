package com.example.kadh.ui.work.presenter;

import com.example.kadh.base.BasePresenterImpl;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.ui.work.bean.ProcessStatusBean;
import com.example.kadh.ui.work.contract.WorkFragContract;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class WorkPresenter extends BasePresenterImpl<WorkFragContract.View> implements WorkFragContract.Presenter<WorkFragContract.View> {


    private RxApi mRxApi;

    @Inject
    public WorkPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }

    @Override
    public void getProcessModuletList() {

        mRxApi.getProcessModuletList(new SubProtect<BaseResponse<List<ProcessModuleBean>>>(new SubNextImpl<BaseResponse<List<ProcessModuleBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<ProcessModuleBean>> response) {
                mView.showProcessModuletList(response.data);
            }
        }));
    }

    @Override
    public void getProcessStatus() {
        mRxApi.getProcessStatus(new SubProtect<BaseResponse<List<ProcessStatusBean>>>(new SubNextImpl<BaseResponse<List<ProcessStatusBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<ProcessStatusBean>> response) {
                mView.showProcessStatus(response.data);
            }
        }));
    }
}
