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
import com.example.kadh.base.Constant;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.company.adapter.FileListAdapter;
import com.example.kadh.ui.company.bean.PublishNoticeDetailBean;
import com.example.kadh.utils.FileUtils;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxApi.RxUrl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubDownload;

import java.io.File;
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
                if (FileUtils.isFileExist(mFileList.get(position).getFile_path())) {
                    FileUtils.openFile(mContext, mFileList.get(position).getFile_path());
                } else {
                    downLoadFile(position);
                }
            }
        });
    }

    private void downLoadFile(final int position) {
        RxManager.getInstant().getRxApi().downloadFile(
                RxUrl.Url.BASE + mFileList.get(position).getFile_uuid()
                , Constant.PATH_USER
                , mFileList.get(position).getFile_name()
                , new SubDownload<File>() {
                    @Override
                    public void onSuccess(File file) {
                        Toast.makeText(mContext, "下载成功", Toast.LENGTH_SHORT).show();
                        mFileListAdapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                    }

                    @Override
                    public void onProgress(int progress, long total) {
                    }
                });
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
