package com.example.kadh.ui.work.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.adapter.ProcessListAdapter;
import com.example.kadh.ui.work.bean.ProcessListBean;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.view.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessListActivity extends BaseActivity {
    @BindView(R.id.activity_work_process_rv)
    RecyclerView mRv;
    @BindView(R.id.activity_work_process_load)
    LoadingLayout mLoadingLayout;
    private String mTitle;
    private String mProcesstype;
    private List<ProcessListBean> mProcessListBeans = new ArrayList<>();
    private ProcessListAdapter mProcessListAdapter;

    @Override
    public void configViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mProcessListAdapter = new ProcessListAdapter(R.layout.item_work_process_list, mProcessListBeans);
        mRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(mProcessListAdapter);
        mProcessListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("processid", mProcessListBeans.get(position).getId());
                bundle.putString("pname", mProcessListBeans.get(position).getPname());
                openActivity(ProcessSubmitActivity.class, bundle);
            }
        });

    }

    @Override
    public void initDatas() {
        mProcesstype = getIntent().getStringExtra("processtype");
        RxManager.getInstant().getRxApi().getProcessList(new SubProtect<BaseResponse<List<ProcessListBean>>>(new SubNextImpl<BaseResponse<List<ProcessListBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<ProcessListBean>> response) {
                mProcessListBeans = response.data;
                if (NullUtils.isNull(response.data)) {
                    mLoadingLayout.setEmptyText("还没有发起过任何流程");
                    mLoadingLayout.showEmpty();
                } else {
                    mProcessListAdapter.setNewData(response.data);
                    mLoadingLayout.showContent();
                }
            }
        }), mProcesstype);

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
        mCommonToolbar.setTitle(NullUtils.filterNull(getIntent().getStringExtra("title")));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_process;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
