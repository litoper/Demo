package com.example.kadh.ui.work.presenter;

import android.widget.Toast;

import com.example.kadh.app.App;
import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessUserDetailBean;
import com.example.kadh.ui.work.contract.ProcessSubmitContract;
import com.example.kadh.utils.CheckUtils;
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

    @Override
    public void processSubmit(List<ProcessContentBean> data, String processid, String pid, String pname) {
        this.checkSubmitData(data);
    }


    /**
     * Ptype    1:单选 2:多选 3:单文本 4:多文本 5:日期 6:图片 7:附件
     * Pcheck （0-不需要校验、1-整型 2-浮点型 3-手机号 4-邮箱 5-身份证 6-座机）
     *
     * @param data
     */
    @Override
    public void checkSubmitData(List<ProcessContentBean> data) {
        for (ProcessContentBean bean : data) {
            switch (NullUtils.filterNull(bean.getPtype())) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":
                case "4":
                    checkText(bean);
                    break;
                case "5":

                    break;
                case "6":

                    break;
                case "7":

                    break;
                default:
                    break;
            }


        }
    }

    /**
     * Pcheck （0-不需要校验、1-整型 2-浮点型 3-手机号 4-邮箱 5-身份证 6-座机）
     *
     * @param bean
     */
    private void checkText(ProcessContentBean bean) {
        switch (bean.getPcheck()) {
            case "0":
                if (NullUtils.filterEmpty(bean.getContext()).length() > Integer.parseInt(bean.getPmaxLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->长度应小于" + (Integer.parseInt(bean.getPmaxLength()) + 1) + "个", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (NullUtils.filterEmpty(bean.getContext()).length() > Integer.parseInt(bean.getPminLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->长度应大于" + (Integer.parseInt(bean.getPmaxLength()) + 1) + "个", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case "1":

                if (!CheckUtils.isInt(bean.getContext())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->为空或格式错误(整数)", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(bean.getContext()) > Integer.parseInt(bean.getPmaxLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->应小于等于" + bean.getPmaxLength(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(bean.getContext()) < Integer.parseInt(bean.getPminLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->应大于等于" + bean.getPminLength(), Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case "2":
                // TODO: 2018/6/29  
                break;
            case "3":

                break;
            case "4":

                break;
            case "5":

                break;
            case "6":

                break;
            default:
                break;
        }
    }


}
