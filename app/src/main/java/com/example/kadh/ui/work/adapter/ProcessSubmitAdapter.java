package com.example.kadh.ui.work.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ess.filepicker.FilePicker;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.util.Const;
import com.example.kadh.R;
import com.example.kadh.base.CommonViewHolder;
import com.example.kadh.bean.support.IsingleChoiceBean2;
import com.example.kadh.ui.work.activity.ProcessSubmitActivity;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessSingleChoiceBean;
import com.example.kadh.ui.work.presenter.ProcessSubmitPresenter;
import com.example.kadh.utils.DateUtils;
import com.example.kadh.utils.MdAlterHelper;
import com.example.kadh.utils.NullUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/27
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessSubmitAdapter extends BaseMultiItemQuickAdapter<ProcessContentBean, CommonViewHolder> implements BGASortableNinePhotoLayout.Delegate, BaseQuickAdapter.OnItemChildClickListener {

    private final String mCurrentTime;
    private RecyclerView mRvProcess;
    private ProcessSubmitActivity mActivity;
    private ProcessSubmitPresenter mPresenter;
    private ArrayList<String> mSelectedPhotos;
    private List<EssFile> mSelectedFiles;
    private BGASortableNinePhotoLayout mBgaLytImage;
    private RecyclerView mRvAttValue;
    private ProcessItemAttAdapter mItemAttAdapter;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data      A new list is created out of this one to avoid mutable list
     * @param rvProcess
     * @param presenter
     */
    public ProcessSubmitAdapter(List<ProcessContentBean> data, RecyclerView rvProcess, ProcessSubmitActivity activity, ProcessSubmitPresenter presenter) {
        super(data);
        mRvProcess = rvProcess;
        mActivity = activity;
        mPresenter = presenter;
        mSelectedPhotos = new ArrayList<>();
        mCurrentTime = DateUtils.getCurrentDate(DateUtils.DateFormat.FORMAT_MM);
        addItemType(ProcessContentBean.NORMAL, R.layout.item_process_submit_normal);
        addItemType(ProcessContentBean.TEXT, R.layout.item_process_submit_text);
        addItemType(ProcessContentBean.MTEXT, R.layout.item_process_submit_mtext);
        addItemType(ProcessContentBean.ATT, R.layout.item_process_submit_att);
        addItemType(ProcessContentBean.PHOTO, R.layout.item_process_submit_photo);
    }

    @Override
    protected void convert(CommonViewHolder helper, ProcessContentBean item) {

        switch (helper.getItemViewType()) {
            case ProcessContentBean.NORMAL:
                processItemNormal(helper, item);
                break;
            case ProcessContentBean.MTEXT:
                processItemMtext(helper, item);
                break;
            case ProcessContentBean.ATT:
                processItemAtt(helper, item);
                break;
            case ProcessContentBean.PHOTO:
                processItemPhoto(helper, item);
                break;
            case ProcessContentBean.TEXT:
                processItemText(helper, item);
                break;
            default:
                break;
        }
    }

    private void processItemAtt(CommonViewHolder helper, ProcessContentBean item) {
        helper.setText(R.id.item_process_submit_att_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        helper.addOnClickListener(R.id.item_process_submit_att_iv_add);
        mRvAttValue = helper.getView(R.id.item_process_submit_att_rv_value);
        if (mItemAttAdapter == null) {
            mItemAttAdapter = new ProcessItemAttAdapter(R.layout.item_process_submit_att_file, mSelectedFiles);
            mItemAttAdapter.setOnItemChildClickListener(mItemAttAdapter);
            mRvAttValue.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvAttValue.setAdapter(mItemAttAdapter);
            mRvAttValue.setHasFixedSize(true);
        } else {
            mItemAttAdapter.setNewData(mSelectedFiles);
        }
    }

    private void processItemText(CommonViewHolder helper, ProcessContentBean item) {
        // TODO: 2018/6/28 输入框文本监听 待添加
        helper.setText(R.id.item_process_submit_text_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        helper.setHint(R.id.item_process_submit_text_et_value, NullUtils.filterEmpty(item.getPlayer()));
        helper.setText(R.id.item_process_submit_text_et_value, NullUtils.filterEmpty(item.getContext()));
        item.setChooseValue(NullUtils.filterEmpty(item.getPtype_chooseValue(), "0"));
    }


    private void processItemMtext(CommonViewHolder helper, ProcessContentBean item) {
        // TODO: 2018/6/28 输入框文本变化监听待添加
        helper.setText(R.id.item_process_submit_mtext_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        helper.setHint(R.id.item_process_submit_mtext_et_value, NullUtils.filterEmpty(item.getPlayer()));
        helper.setText(R.id.item_process_submit_mtext_et_value, NullUtils.filterEmpty(item.getContext()));
        item.setChooseValue(NullUtils.filterEmpty(item.getPtype_chooseValue(), "0"));
    }

    private void processItemPhoto(CommonViewHolder helper, ProcessContentBean item) {
        helper.setText(R.id.item_process_submit_photo_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        mBgaLytImage = helper.getView(R.id.item_process_submit_photo_bgalyt_image);
        mBgaLytImage.setData(mSelectedPhotos);
        mBgaLytImage.setDelegate(this);
    }

    private void processItemNormal(CommonViewHolder helper, ProcessContentBean item) {
        // TODO: 2018/6/28 单选/多选/时间选择器 待完善
        helper.addOnClickListener(R.id.item_process_submit_normal_ll_root);
        helper.setText(R.id.item_process_submit_normal_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        switch (item.getPtype()) {
            case "1":
                if (NullUtils.isEmpty(item.getPtype_chooseValue())) {
                    item.setChooseValue("0");
                } else {
                    if (NullUtils.isEmpty(item.getChooseValue())) {
                        item.setChooseValue(item.getPtype_chooseValue().split(",")[0]);
                    }
                }
                if (item.getContext() == null) {
                    helper.setText(R.id.item_process_submit_normal_tv_value, item.getPtype_choose().split(",")[0]);
                    item.setContext(item.getPtype_choose().split(",")[0]);
                } else {
                    helper.setText(R.id.item_process_submit_normal_tv_value, NullUtils.filterEmpty(item.getContext()));
                }
                break;
            case "2":
            case "3":
            case "4":
                helper.setText(R.id.item_process_submit_normal_tv_value, NullUtils.filterEmpty(item.getContext(), item.getPtype()));
                break;
            case "5":
                if (item.getContext() == null) {
                    item.setContext(mCurrentTime);
                }
                helper.setText(R.id.item_process_submit_normal_tv_value, NullUtils.filterEmpty(item.getContext()));
                break;
            default:
                break;

        }


    }


    @SuppressLint("CheckResult")
    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        new RxPermissions(mActivity).request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (aBoolean) {
                    File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "picture");
                    Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(mContext).cameraFileDir(takePhotoDir).maxChooseCount(10).selectedPhotos(mSelectedPhotos).pauseOnScroll(false).build();
                    mActivity.startActivityForResult(photoPickerIntent, 888);
                } else {
                    Toast.makeText(mContext, "缺乏权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mBgaLytImage.removeItem(position);
        mBgaLytImage.requestLayout();
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(mActivity)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mBgaLytImage.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        mActivity.startActivityForResult(photoPickerPreviewIntent, 666);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }


    public void updateResultData(int requestCode, Intent data) {
        switch (requestCode) {
            case 888:
                mSelectedPhotos = data.getStringArrayListExtra("EXTRA_SELECTED_PHOTOS");
                mBgaLytImage.setData(BGAPhotoPickerActivity.getSelectedPhotos(data));
                break;
            case 666:
                mSelectedPhotos = data.getStringArrayListExtra("EXTRA_SELECTED_PHOTOS");
                mBgaLytImage.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
                break;
            case 168:
                mSelectedFiles = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
                mItemAttAdapter.setNewData(mSelectedFiles);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (adapter.getItemViewType(position)) {
            case ProcessContentBean.NORMAL:
                switch (getData().get(position).getPtype()) {
                    case "1"://单选
                        showSingleChoiceDialog(getData(), position, adapter);
                        break;
                    case "2"://多选
                        showMulitChoiceDialog(getData(), position, adapter);
                        break;
                    case "5"://日期选择
                        break;
                    default:
                        break;
                }
                break;
            case ProcessContentBean.ATT:
                FilePicker.from(mActivity).chooseForMimeType().setMaxCount(5).setFileTypes("png", "doc", "xls", "gif", "txt", "mp4", "zip").requestCode(168).start();
                break;
            default:
                break;
        }

    }

    private void showMulitChoiceDialog(List<ProcessContentBean> data, int pos, BaseQuickAdapter adapter) {
//        new MdAlterHelper(mActivity).displayMulitDialog2(true,title,);

    }

    private void showSingleChoiceDialog(List<ProcessContentBean> data, final int pos, final BaseQuickAdapter adapter) {
        final ProcessContentBean bean = data.get(pos);
        String title = NullUtils.filterEmpty(bean.getPtitle());
        String matchKey = NullUtils.filterEmpty(bean.getContext());
        final List<IsingleChoiceBean2> singleChoiceBeans = new ArrayList<IsingleChoiceBean2>();

        String[] name = bean.getPtype_choose().split(",");
        String[] value = null;

        if (NullUtils.isNull(bean.getPtype_chooseValue())) {
            for (int i = 0; i < name.length; i++) {
                ProcessSingleChoiceBean choiceBean = new ProcessSingleChoiceBean();
                choiceBean.setName(name[i]);
                choiceBean.setValue("0");
                singleChoiceBeans.add(choiceBean);
            }
        } else {
            value = bean.getPtype_chooseValue().split(",");
            for (int i = 0; i < name.length; i++) {
                ProcessSingleChoiceBean choiceBean = new ProcessSingleChoiceBean();
                choiceBean.setName(name[i]);
                choiceBean.setValue(value[i]);
                singleChoiceBeans.add(choiceBean);
            }
        }

        new MdAlterHelper(mActivity).displaySingleDialog2(true, title, singleChoiceBeans, matchKey, new MdAlterHelper.IdisplaySingleCallBack() {
            @Override
            public void onSelection(int position, String selectText) {
                TextView tvValue = (TextView) adapter.getViewByPosition(mRvProcess, pos, R.id.item_process_submit_normal_tv_value);
                tvValue.setText(selectText);
                bean.setChooseValue(NullUtils.filterEmpty(singleChoiceBeans.get(position).value()));
                bean.setContext(NullUtils.filterEmpty(singleChoiceBeans.get(position).displayText()));
                bean.setCheckPos(position);
            }
        });

    }
}


