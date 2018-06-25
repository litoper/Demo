package com.example.kadh.ui.tension.presenter;

import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.tension.contract.TensionAtyContract;
import com.example.kadh.utils.RxJava.RxApi.RxApi;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/21
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class TensionBinding extends BaseBindingImpl<TensionAtyContract.View> implements TensionAtyContract.Presenter<TensionAtyContract.View> {

    private RxApi mRxApi;

    @Inject
    public TensionBinding(RxApi rxApi) {
        mRxApi = rxApi;
    }
}
