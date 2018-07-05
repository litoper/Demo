package com.example.kadh.ui.work.activity;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.bean.ProcessScheuleBean;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProgress;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/3
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessStartedScheuleActivity extends BaseActivity {

    private String mPublishId;
    private String mType;
    private ProcessScheuleBean mProcessScheuleBean;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        mPublishId = getIntent().getStringExtra("publishId");
        mType = getIntent().getStringExtra("type");
        getProcessSchedule();
    }

    private void getProcessSchedule() {
        RxManager.getInstant().getRxApi().getProcessSchedule(new SubProgress<BaseResponse<List<ProcessScheuleBean>>>(new SubNextImpl<BaseResponse<List<ProcessScheuleBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<ProcessScheuleBean>> response) {
                mProcessScheuleBean = response.data.get(0);
                mCommonToolbar.setTitle(NullUtils.filterEmpty(mProcessScheuleBean.getPtitle()));
                // TODO: 2018/7/3 页面设置
            }
        }, this, 1), mPublishId);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_process_progress;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
