package com.example.kadh.ui.work.presenter;

import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessUserDetailBean;
import com.example.kadh.ui.work.contract.ProcessSubmitContract;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/27
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessSubmitPresenter extends BaseBindingImpl<ProcessSubmitContract.View> implements ProcessSubmitContract.Presenter {
    private RxApi mRxApi;

    @Inject
    public ProcessSubmitPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }

    @Override
    public void getProcessContent(String processid, final List<ProcessUserDetailBean> userDetailBeans) {
        mRxApi.getProcessContent(new SubProtect<BaseResponse<List<ProcessContentBean>>>(new SubNextImpl<BaseResponse<List<ProcessContentBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<ProcessContentBean>> response) {
                List<ProcessContentBean> contentBeans = response.data;
                if (!NullUtils.isNull(userDetailBeans) && !NullUtils.isNull(contentBeans)) {
                    for (ProcessContentBean contentBean : contentBeans) {
                        for (ProcessUserDetailBean userDetailBean : userDetailBeans) {
                            if (contentBean.getPtitle().equals(userDetailBean.getTitle())) {
                                contentBean.setContext(NullUtils.filterEmpty(userDetailBean.getContext()));
                                contentBean.setChooseValue(NullUtils.filterEmpty(userDetailBean.getPvalue()));
                                contentBean.setPtype(NullUtils.filterEmpty(userDetailBean.getType()));
                            }
                        }
                    }
                }
                mView.showProcessContent(contentBeans);
            }
        }), processid);

    }

    @Override
    public void processUserDetail(String pid, final String processid) {
        mRxApi.processUserDetail(new SubProtect<BaseResponse<List<ProcessUserDetailBean>>>(new SubNextImpl<BaseResponse<List<ProcessUserDetailBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<ProcessUserDetailBean>> response) {
                if (!NullUtils.isNull(response.data)) {
                    getProcessContent(processid, response.data);
                }
            }
        }), pid);

    }
}
