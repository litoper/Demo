package com.example.kadh.ui.work.presenter;

import android.util.Log;
import android.widget.Toast;

import com.ess.filepicker.model.EssFile;
import com.example.kadh.app.App;
import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.person.bean.UpFieldBean;
import com.example.kadh.ui.work.adapter.ProcessItemAttAdapter;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessUserDetailBean;
import com.example.kadh.ui.work.contract.ProcessSubmitContract;
import com.example.kadh.utils.CheckUtils;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

        /*
         * Ptype    1:单选 2:多选 3:单文本 4:多文本 5:日期 6:图片 7:附件
         * Pcheck （0-不需要校验、1-整型 2-浮点型 3-手机号 4-邮箱 5-身份证 6-座机）
         */
        for (ProcessContentBean bean : data) {
            switch (NullUtils.filterNull(bean.getPtype())) {
                case "1"://单选
                    checkSingle(bean);
                    break;
                case "2"://多选
                    if (checkMulit(bean)) return;
                    break;
                case "3"://单文本
                case "4"://多文本
                    if (checkText(bean)) return;
                    break;
                case "5"://日期
                    checkDate(bean);
                    break;
                case "6"://图片
                    checkPic(bean);
                    break;
                case "7"://附件
                    checkAtt(bean);
                    break;
                default:
                    break;
            }
            bean.setContext(NullUtils.filterNull(bean.getContext(), "未填写"));
        }


        JSONArray jsonArray = new JSONArray();
        for (ProcessContentBean contentBean : data) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("title", NullUtils.filterEmpty(contentBean.getPtitle()));
                jsonObject.put("context", NullUtils.filterEmpty(contentBean.getContext()));
                jsonObject.put("type", NullUtils.filterEmpty(contentBean.getPtype()));
                jsonObject.put("pvalue", NullUtils.filterEmpty(contentBean.getPtype_chooseValue()));
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String json = jsonArray.toString();

        mRxApi.submitProcess(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {
                // TODO: 2018/7/2 流程提交成功处理
                Log.d("ProcessSubmitPresenter", "response:" + response);

            }
        }), processid, pid, pname, json);
    }

    @Override
    public void upLoadField(ArrayList<String> selectedPhotos, final LinkedHashMap<String, UpFieldBean> savePhotos, final BGASortableNinePhotoLayout bgaLytImage) {
        List<Flowable> flowableList = new ArrayList<>();
        for (final String selectedPhoto : selectedPhotos) {
            File file = new File(selectedPhoto);
            RequestBody requestBody = RequestBody.create(MediaType.parse("form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            Flowable flowable = mRxApi.upLoadField(part, file.getName()).map(new Function<BaseResponse<List<UpFieldBean>>,BaseResponse<List<UpFieldBean>>>() {
                @Override
                public BaseResponse<List<UpFieldBean>> apply(BaseResponse<List<UpFieldBean>> response) throws Exception {
                    response.data.get(0).setLocalPath(selectedPhoto);
                    return response;
                }
            });
            flowableList.add(flowable);
        }

        Flowable[] flowables = new Flowable[flowableList.size()];
        flowableList.toArray(flowables);
        mRxApi.toConcatSub(new SubProtect<BaseResponse<List<UpFieldBean>>>(new SubNextImpl<BaseResponse<List<UpFieldBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UpFieldBean>> response) {
                UpFieldBean upFieldBean = response.data.get(0);
                bgaLytImage.addLastItem(upFieldBean.getLocalPath());
                savePhotos.put(upFieldBean.getLocalPath(), upFieldBean);
            }
        }), flowables);
    }


    @Override
    public void upLoadField(ArrayList<String> selectedPhotos, LinkedHashMap<String, EssFile> savaPhoto, ProcessItemAttAdapter itemAttAdapter) {
        // TODO: 2018/7/2  
    }

    private boolean checkMulit(ProcessContentBean bean) {
        String[] split = NullUtils.filterEmpty(bean.getChooseValue()).split(",");
        if (split.length > Integer.parseInt(bean.getPmaxLength())) {
            Toast.makeText(App.getApp(), bean.getPtitle() + "->应小于等于" + bean.getPmaxLength(), Toast.LENGTH_SHORT).show();
            return true;
        }
        if (split.length < Integer.parseInt(bean.getPminLength())) {
            Toast.makeText(App.getApp(), bean.getPtitle() + "->应大于等于" + bean.getPminLength(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void checkSingle(ProcessContentBean bean) {
        //do nothing
    }

    private void checkAtt(ProcessContentBean bean) {
        // TODO: 2018/6/29 流程提交附件处理
    }

    private void checkPic(ProcessContentBean bean) {
        // TODO: 2018/6/29 流程提交图片处理
    }

    private void checkDate(ProcessContentBean bean) {
        //do nothing
    }

    /**
     * Pcheck （0-不需要校验、1-整型 2-浮点型 3-手机号 4-邮箱 5-身份证 6-座机）
     *
     * @param bean
     */
    private boolean checkText(ProcessContentBean bean) {
        switch (bean.getPcheck()) {
            case "0":
                if (NullUtils.filterEmpty(bean.getContext().trim()).length() > Integer.parseInt(bean.getPmaxLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->长度应小于" + (Integer.parseInt(bean.getPmaxLength()) + 1) + "个", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (NullUtils.filterEmpty(bean.getContext().trim()).length() < Integer.parseInt(bean.getPminLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->长度应大于" + (Integer.parseInt(bean.getPminLength()) - 1) + "个", Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
            case "1":
                if (!CheckUtils.isInt(bean.getContext())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->为空或格式错误(整数)", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (Integer.parseInt(bean.getContext()) > Integer.parseInt(bean.getPmaxLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->应小于等于" + bean.getPmaxLength(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (Integer.parseInt(bean.getContext()) < Integer.parseInt(bean.getPminLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->应大于等于" + bean.getPminLength(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
            case "2":
                if (!CheckUtils.isDouble(bean.getContext())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->为空或格式错误(数字)", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (Double.parseDouble(bean.getContext()) > Double.parseDouble(bean.getPmaxLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->应小于等于" + bean.getPmaxLength(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (Double.parseDouble(bean.getContext()) < Double.parseDouble(bean.getPminLength())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->应大于等于" + bean.getPminLength(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
            case "3":
                if (!CheckUtils.isMobile(bean.getContext())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->为空或格式错误(手机号码)", Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
            case "4":
                if (!CheckUtils.isEmail(bean.getContext())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->为空或格式错误(邮箱)", Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
            case "5":
                if (!CheckUtils.isIdCard(bean.getContext())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->为空或格式错误(身份证)", Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
            case "6":
                if (!CheckUtils.isPhone(bean.getContext())) {
                    Toast.makeText(App.getApp(), bean.getPtitle() + "->为空或格式错误(座机)", Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }


}
