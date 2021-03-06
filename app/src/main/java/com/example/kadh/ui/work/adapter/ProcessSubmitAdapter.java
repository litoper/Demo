package com.example.kadh.ui.work.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ess.filepicker.FilePicker;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.util.Const;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.work.activity.ProcessSubmitActivity;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessSingleChoiceBean;
import com.example.kadh.ui.work.presenter.ProcessSubmitPresenter;
import com.example.kadh.utils.DateUtils;
import com.example.kadh.utils.MdAlterHelper;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.view.TimeSelector.TimeSelector;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
public class ProcessSubmitAdapter extends BaseMultiItemQuickAdapter<ProcessContentBean, BaseViewHolderImpl> implements BGASortableNinePhotoLayout.Delegate, BaseQuickAdapter.OnItemChildClickListener {

    private String mCurrentTime;
    private TimeSelector mTimeSelector;
    private RecyclerView mRvProcess;
    private RecyclerView mRvAttValue;
    private ProcessItemAttAdapter mItemAttAdapter;
    private ProcessSubmitActivity mActivity;
    private ProcessSubmitPresenter mPresenter;
    private BGASortableNinePhotoLayout mBgaLytImage;
    private ArrayList<String> mSelectedPhotos;
    private List<EssFile> mSelectedFiles;


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
    protected void convert(BaseViewHolderImpl helper, ProcessContentBean item) {

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

    private void processItemAtt(BaseViewHolderImpl helper, ProcessContentBean item) {
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

    private void processItemText(BaseViewHolderImpl helper, final ProcessContentBean item) {
        addTextChangeListener(helper, R.id.item_process_submit_text_et_value, item);
        helper.setText(R.id.item_process_submit_text_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        helper.setHint(R.id.item_process_submit_text_et_value, NullUtils.filterEmpty(item.getPlayer()));
        helper.setText(R.id.item_process_submit_text_et_value, NullUtils.filterEmpty(item.getContext()));
        item.setChooseValue(NullUtils.filterEmpty(item.getPtype_chooseValue(), "0"));
    }


    private void processItemMtext(BaseViewHolderImpl helper, ProcessContentBean item) {
        addTextChangeListener(helper, R.id.item_process_submit_mtext_et_value, item);
        helper.setText(R.id.item_process_submit_mtext_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        helper.setHint(R.id.item_process_submit_mtext_et_value, NullUtils.filterEmpty(item.getPlayer()));
        helper.setText(R.id.item_process_submit_mtext_et_value, NullUtils.filterEmpty(item.getContext()));
        item.setChooseValue(NullUtils.filterEmpty(item.getPtype_chooseValue(), "0"));
    }

    private void processItemPhoto(BaseViewHolderImpl helper, ProcessContentBean item) {
        helper.setText(R.id.item_process_submit_photo_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        mBgaLytImage = helper.getView(R.id.item_process_submit_photo_bgalyt_image);
        mBgaLytImage.setData(mSelectedPhotos);
        mBgaLytImage.setDelegate(this);
    }

    private void processItemNormal(BaseViewHolderImpl helper, ProcessContentBean item) {
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
                item.setContext(NullUtils.filterNull(item.getContext(), mCurrentTime));
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

    private void addTextChangeListener(BaseViewHolderImpl helper, @IdRes int idRes, final ProcessContentBean item) {
        EditText etValue = helper.getView(idRes);
        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setContext(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void updateResultData(int requestCode, Intent data) {
        // TODO: 2018/6/29 图片及附件上传待添加完善
        switch (requestCode) {
            case 888:
                mSelectedPhotos = data.getStringArrayListExtra("EXTRA_SELECTED_PHOTOS");
                mPresenter.upLoadField(mSelectedPhotos, mBgaLytImage);
                break;
            case 666:
                mSelectedPhotos = data.getStringArrayListExtra("EXTRA_SELECTED_PHOTOS");
                mBgaLytImage.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
                mPresenter.upLoadField(mSelectedPhotos, mBgaLytImage);
                break;
            case 168:
                mSelectedFiles = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
                mPresenter.upLoadField(mSelectedFiles, mItemAttAdapter);
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
                        showTimeSelectorDialog(getData(), position, adapter);
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

    private void showTimeSelectorDialog(List<ProcessContentBean> data, int position, BaseQuickAdapter adapter) {
        final TextView tvValue = (TextView) adapter.getViewByPosition(mRvProcess, position, R.id.item_process_submit_normal_tv_value);
        final ProcessContentBean contentBean = data.get(position);
        mTimeSelector = new TimeSelector(mContext, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time, Calendar calender) {
                contentBean.setContext(time);
                tvValue.setText(time);

            }
        }, "2000-1-1 00:00", "2030-12-31 00:00");

        if (NullUtils.isNull(contentBean.getContext())) {
            mTimeSelector.show(mCurrentTime);
        } else {
            mTimeSelector.show(contentBean.getContext());
        }

    }

    private void showMulitChoiceDialog(List<ProcessContentBean> data, final int pos, final BaseQuickAdapter adapter) {
        final ProcessContentBean bean = data.get(pos);
        final String title = bean.getPtitle();
        final int pminLength = Integer.parseInt(bean.getPminLength());
        final int pmaxLength = Integer.parseInt(bean.getPmaxLength());

        String[] items = bean.getPtype_choose().split(",");
        int[] itemsIds = new int[items.length];
        for (int i = 0; i < items.length; i++) {
            itemsIds[i] = i;
        }

        Integer[] selectIndices = null;
        if (!NullUtils.isNull(bean.getChooseValue())) {
            String[] split = bean.getChooseValue().split(",");
            selectIndices = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                selectIndices[i] = Integer.parseInt(split[i]);
            }
        }

        new MaterialDialog.Builder(mActivity)
                .title(title)
                .items(Arrays.asList(items))
                .itemsIds(itemsIds)
                .autoDismiss(false)
                .positiveText("确定")
                .itemsCallbackMultiChoice(selectIndices, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        if (which.length > pmaxLength) {
                            Toast.makeText(mContext, title + "最多能选择" + pmaxLength + "个", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        if (which.length < pminLength) {
                            Toast.makeText(mContext, title + "最少需选择" + pminLength + "个", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        String index = "";
                        String value = "";
                        for (int i = 0; i < which.length; i++) {
                            index = which[i] + "," + index;
                            value = text[i] + "," + value;
                        }

                        bean.setChooseValue(index.substring(0, index.length() - 1));
                        bean.setContext(value.substring(0, value.length() - 1));

                        TextView tvValue = (TextView) adapter.getViewByPosition(mRvProcess, pos, R.id.item_process_submit_normal_tv_value);
                        tvValue.setText(NullUtils.filterEmpty(bean.getContext()));
                        dialog.dismiss();
                        return false;
                    }
                })
                .show();
    }

    private void showSingleChoiceDialog(List<ProcessContentBean> data, final int pos, final BaseQuickAdapter adapter) {
        final ProcessContentBean bean = data.get(pos);
        String title = NullUtils.filterEmpty(bean.getPtitle());
        String matchKey = NullUtils.filterEmpty(bean.getContext());
        final List<ProcessSingleChoiceBean> singleChoiceBeans = new ArrayList<ProcessSingleChoiceBean>();

        String[] items = bean.getPtype_choose().split(",");
        String[] value = null;

        if (NullUtils.isNull(bean.getPtype_chooseValue())) {
            for (int i = 0; i < items.length; i++) {
                ProcessSingleChoiceBean choiceBean = new ProcessSingleChoiceBean();
                choiceBean.setName(items[i]);
                choiceBean.setValue("0");
                singleChoiceBeans.add(choiceBean);
            }
        } else {
            value = bean.getPtype_chooseValue().split(",");
            for (int i = 0; i < items.length; i++) {
                ProcessSingleChoiceBean choiceBean = new ProcessSingleChoiceBean();
                choiceBean.setName(items[i]);
                choiceBean.setValue(value[i]);
                singleChoiceBeans.add(choiceBean);
            }
        }

        new MdAlterHelper(mActivity).displaySingleDialog(true, title, singleChoiceBeans, matchKey, new MdAlterHelper.IdisplaySingleCallBack() {
            @Override
            public void onSelection(int position, String selectText) {
                TextView tvValue = (TextView) adapter.getViewByPosition(mRvProcess, pos, R.id.item_process_submit_normal_tv_value);
                tvValue.setText(selectText);
                bean.setChooseValue(NullUtils.filterEmpty(singleChoiceBeans.get(position).getValue()));
                bean.setContext(NullUtils.filterEmpty(singleChoiceBeans.get(position).getName()));
                //单选点击后根据下标修改数据源相应位置的CheckPos
                bean.setCheckPos(position);
            }
        });

    }
}


