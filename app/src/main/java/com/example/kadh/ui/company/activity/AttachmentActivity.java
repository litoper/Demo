package com.example.kadh.ui.company.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.company.adapter.FileListAdapter;
import com.example.kadh.ui.company.bean.PublishNoticeDetailBean;
import com.example.kadh.utils.FileUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/14
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class AttachmentActivity extends BaseActivity {
    @BindView(R.id.activity_att_rv)
    RecyclerView mRv;
    private List<PublishNoticeDetailBean.FileListBean> mFileList;
    private FileListAdapter mFileListAdapter;

    @Override
    public void configViews() {
        mFileListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // TODO: 2018/6/14 holder的tag获取
                if (FileUtils.isFileExist(mFileList.get(position).getFile_path())) {
                    openFile();
                } else {
                    downLoadFile();
                }
            }
        });
    }

    private void openFile() {
        Toast.makeText(mContext, "打开文件", Toast.LENGTH_SHORT).show();
    }

    private void downLoadFile() {
        Toast.makeText(mContext, "下载文件", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initDatas() {
        mFileList = getIntent().getParcelableArrayListExtra("fileList");
        mFileListAdapter = new FileListAdapter(R.layout.item_att, mFileList);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRv.setAdapter(mFileListAdapter);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("附件列表");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attachment;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


}
