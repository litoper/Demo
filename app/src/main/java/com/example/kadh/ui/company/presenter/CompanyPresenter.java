package com.example.kadh.ui.company.presenter;

import com.example.kadh.base.BasePresenterImpl;
import com.example.kadh.ui.company.contract.CompanyFragContract;
import com.example.kadh.utils.RxJava.RxApi.RxApi;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/4
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class CompanyPresenter extends BasePresenterImpl<CompanyFragContract.View> implements CompanyFragContract.Presenter<CompanyFragContract.View> {

    RxApi mRxApi;

    @Inject
    public CompanyPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }
}
