package com.example.kadh.ui.work.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivityView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.work.adapter.ProcessSubmitAdapter;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.contract.ProcessSubmitContract;
import com.example.kadh.ui.work.presenter.ProcessSubmitPresenter;
import com.example.kadh.utils.NullUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessSubmitActivity extends BaseActivityView<ProcessSubmitPresenter> implements ProcessSubmitContract.View {
    @BindView(R.id.activity_work_process_submit_rv_process)
    RecyclerView mRvProcess;
    @BindView(R.id.activity_work_process_submit_btn_commit)
    Button mBtnCommit;
    private String mProcessid;
    private String mPid;
    private String mResubmit;
    private List<ProcessContentBean> mProcessContentBeans;
    private ProcessSubmitAdapter mProcessSubmitAdapter;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        if (!NullUtils.isNull(mPid)) {
            //重新发起
            mPresenter.processUserDetail(mPid, mProcessid);
        } else {
            mPresenter.getProcessContent(mProcessid, null);
        }
    }

    @Override
    public void initToolBar() {
        mProcessid = getIntent().getStringExtra("processid");
        String pname = getIntent().getStringExtra("pname");
        mPid = getIntent().getStringExtra("pid");
        mResubmit = getIntent().getStringExtra("resubmit");

        mCommonToolbar.setTitle(NullUtils.filterEmpty(pname));
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_process_submit;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showProcessContent(List<ProcessContentBean> contentBeans) {
        mProcessContentBeans = contentBeans;
        if (!NullUtils.isNull(mResubmit)) {
            for (ProcessContentBean bean : contentBeans) {
                if ("照片附件".equals(bean.getPtitle()) || "文件附件".equals(bean.getPtitle())) {
                    bean.setContext("");
                    bean.setChooseValue("");
                }
            }
        }

        mProcessSubmitAdapter = new ProcessSubmitAdapter(mProcessContentBeans, mRvProcess, this, mPresenter);
        mProcessSubmitAdapter.setOnItemChildClickListener(mProcessSubmitAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRvProcess.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRvProcess.setLayoutManager(layoutManager);
        mRvProcess.setAdapter(mProcessSubmitAdapter);
        // TODO: 2018/6/27  

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                mProcessSubmitAdapter.updateResultData(requestCode, data);
            }
        }
    }
}
