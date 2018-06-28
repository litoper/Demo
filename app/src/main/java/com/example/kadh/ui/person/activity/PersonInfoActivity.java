package com.example.kadh.ui.person.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.kadh.R;
import com.example.kadh.base.BaseActivityView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.person.bean.RoleManageBean;
import com.example.kadh.ui.person.bean.UpFieldBean;
import com.example.kadh.ui.person.contract.PersonInfoAtyContract;
import com.example.kadh.ui.person.presenter.PersonInfoPresenter;
import com.example.kadh.utils.GlideUtils;
import com.example.kadh.utils.MdAlterHelper;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RegularUtils;
import com.example.kadh.utils.RxJava.RxApi.RxUrl;
import com.example.kadh.utils.SpUtil;
import com.example.kadh.view.CircleImageView.CircleImageView;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.util.BGAPhotoHelper;
import cn.bingoogolapple.photopicker.util.BGAPhotoPickerUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/8
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PersonInfoActivity extends BaseActivityView<PersonInfoPresenter> implements PersonInfoAtyContract.View {

    @BindView(R.id.activity_personal_civ_icon)
    CircleImageView mCivIcon;
    @BindView(R.id.activity_personal_tv_name)
    TextView mTvName;
    @BindView(R.id.activity_personal_iv_sex)
    ImageView mIvSex;
    @BindView(R.id.activity_personal_tv_position_header)
    TextView mTvPositionHeader;
    @BindView(R.id.activity_personal_tv_uids)
    TextView mTvUids;
    @BindView(R.id.activity_personal_tv_induction)
    TextView mTvInduction;
    @BindView(R.id.activity_personal_iv_call)
    ImageView mIvCall;
    @BindView(R.id.activity_personal_tv_group)
    TextView mTvGroup;
    @BindView(R.id.activity_personal_ll_group)
    LinearLayout mLlGroup;
    @BindView(R.id.activity_personal_tv_dep)
    TextView mTvDep;
    @BindView(R.id.activity_personal_ll_dep)
    LinearLayout mLlDep;
    @BindView(R.id.activity_personal_tv_position)
    TextView mTvPosition;
    @BindView(R.id.activity_personal_iv_position)
    ImageView mIvPosition;
    @BindView(R.id.activity_personal_ll_position)
    LinearLayout mLlPosition;
    @BindView(R.id.activity_personal_tv_phone)
    TextView mTvPhone;
    @BindView(R.id.activity_personal_ll_phone)
    LinearLayout mLlPhone;
    @BindView(R.id.activity_personal_tv_short)
    TextView mTvShort;
    @BindView(R.id.activity_personal_ll_short)
    LinearLayout mLlShort;
    @BindView(R.id.activity_personal_tv_email)
    TextView mTvEmail;
    @BindView(R.id.activity_personal_ll_email)
    LinearLayout mLlEmail;
    private AlertDialog mDialog;
    private BGAPhotoHelper mPhotoHelper;

    public static final int CODE_TAKE_PHOTO = 888;
    public static final int CODE_CHOOSE_PHOTO = 666;
    public static final int CODE_CLIP_PHOTO = 168;
    private MenuItem mItemSave;
    private List<RoleManageBean> mRoleManageBeans;
    private MdAlterHelper mAlterHelper;
    private String mModify_UpFiledCode;
    private String mModify_RoleId;
    private String mModify_Email;

    @SuppressLint("CheckResult")
    @Override
    public void configViews() {
        RxView.clicks(mLlGroup).throttleFirst(300, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (!NullUtils.isNull(mRoleManageBeans)) {
                    showRoleDialog();
                }
            }
        });

        RxView.clicks(mLlEmail).throttleFirst(300, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mAlterHelper.showInputDialog("邮箱修改", null, "确定", "请输入邮箱", "", 4, 99,
                        new MdAlterHelper.IclickCallBack() {
                            @Override
                            public void onClick(MaterialDialog dialog, String inputText, DialogAction which) {
                                if (RegularUtils.isEmail(inputText)) {
                                    mModify_Email = inputText;
                                    mTvEmail.setText(inputText);
                                    mItemSave.setVisible(true);
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(mContext, "请正确填写邮箱!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        null);
            }
        });
    }

    @Override
    public void initDatas() {
        mPresenter.getUseInfo();
        mPresenter.getRoleManageSingle("");
        mAlterHelper = new MdAlterHelper(this);

        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "picture");
        mPhotoHelper = new BGAPhotoHelper(takePhotoDir);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_person_info;
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
    public void showUserInfo(UserInfoBean infoBean) {
        GlideUtils.loadImageViewForHead(mContext, RxUrl.Url.BASE + infoBean.getUimage(), mCivIcon);
        mTvName.setText(NullUtils.filterEmpty(infoBean.getUname()));
        mTvPositionHeader.setText(NullUtils.filterEmpty(infoBean.getUposition_name()));
        mTvUids.setText(NullUtils.filterEmpty(infoBean.getUids()));
        mTvInduction.setText(NullUtils.filterEmpty(infoBean.getUinduction()));
        mTvGroup.setText(NullUtils.filterEmpty(infoBean.getRname()));
        mTvDep.setText(NullUtils.filterEmpty(infoBean.getUfdepartment()));
        mTvPosition.setText(NullUtils.filterEmpty(infoBean.getUposition_name()));
        mTvPhone.setText(NullUtils.filterEmpty(infoBean.getUphone()));
        mTvShort.setText(NullUtils.filterEmpty(infoBean.getUshort_phone()));
        mTvEmail.setText(NullUtils.filterNull(infoBean.getUemail(), "暂无"));
        switch (infoBean.getUsex()) {
            case "1":
                mIvSex.setBackgroundResource(R.mipmap.ic_person_info_man);
                break;
            case "2":
                mIvSex.setBackgroundResource(R.mipmap.ic_person_info_woman);
                break;
            default:
                break;
        }

        if ("0".equals(infoBean.getUshort_phone()) || NullUtils.isNull(infoBean.getUshort_phone())) {
            mTvShort.setText("暂未加入集团短号");
        } else {
            mTvShort.setText(infoBean.getUshort_phone());
        }

        if (!NullUtils.isEmpty(infoBean.getUphone())) {
            if (infoBean.getUphone().contains("审核")) {
                mLlPhone.setClickable(true);
            }
        }
        mLlShort.setClickable(true);
        mLlEmail.setClickable(true);
        mCivIcon.setClickable(true);

        mPresenter.getRoleManageSingle(infoBean.getId());
    }

    @Override
    public void setRoleManageSingle(List<RoleManageBean> roleManageBean) {
        mRoleManageBeans = roleManageBean;
    }

    @Override
    public void upFiledSuccess(List<UpFieldBean> data) {
        mItemSave.setVisible(true);
        mModify_UpFiledCode = data.get(0).getCode();
        GlideUtils.loadImageViewForHead(mContext, mPhotoHelper.getCropFilePath(), mCivIcon);
    }

    @Override
    public void postUserInfoSuccess(String data, String flag) {
        Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
        mItemSave.setVisible(false);
        SpUtil.getInstance().putString(SpUtil.LOGIN_INFO_USERICO, mModify_UpFiledCode);
        setResult(RESULT_OK);
        if ("finish".equals(flag)) {
            finish();
        }
    }

    @OnClick({R.id.activity_personal_civ_icon, R.id.activity_personal_ll_dep, R.id.activity_personal_ll_position, R.id.activity_personal_ll_phone, R.id.activity_personal_ll_short, R.id.activity_personal_ll_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_personal_ll_dep:
                break;
            case R.id.activity_personal_ll_position:
                break;
            case R.id.activity_personal_ll_phone:
                break;
            case R.id.activity_personal_ll_short:
                break;
            case R.id.activity_personal_ll_email:
                break;
            case R.id.activity_personal_civ_icon:
                openChoiceDialog();
                break;
            default:
                break;
        }
    }

    private void showRoleDialog() {
        mAlterHelper.displaySingleDialog1(true
                , "请设置默认角色"
                , mRoleManageBeans
                , NullUtils.filterEmpty(mTvGroup.getText().toString())
                , new MdAlterHelper.IdisplaySingleCallBack() {
                    @Override
                    public void onSelection(int position, String selectText) {
                        mTvGroup.setText(selectText);
                        mModify_RoleId = mRoleManageBeans.get(position).getId();
                        mItemSave.setVisible(true);
                    }
                });
    }


    @SuppressLint("CheckResult")
    private void openChoiceDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.CallPhoneStyle);

        View v = View.inflate(this, R.layout.dialog_person_info_head, null);
        TextView mPhoto = v.findViewById(R.id.dialog_tv_photo);
        TextView mAlbum = v.findViewById(R.id.dialog_tv_album);

        //拍照
        RxView
                .clicks(mPhoto)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mDialog.dismiss();
                        new RxPermissions(PersonInfoActivity.this)
                                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            startActivityForResult(mPhotoHelper.getTakePhotoIntent(), CODE_TAKE_PHOTO);
                                        }
                                    }
                                });
                    }
                });

        //相册
        RxView
                .clicks(mAlbum)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mDialog.dismiss();
                        new RxPermissions(PersonInfoActivity.this)
                                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            startActivityForResult(mPhotoHelper.getChooseSystemGalleryIntent(), CODE_CHOOSE_PHOTO);
                                        }
                                    }
                                });
                    }
                });

        mDialog = alertDialogBuilder.create();
        mDialog.setView(v);
        mDialog.show();
        WindowManager.LayoutParams wlp = mDialog.getWindow().getAttributes();
        wlp.gravity = Gravity.CENTER;
        mDialog.getWindow().setAttributes(wlp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_TAKE_PHOTO:
                    try {
                        startActivityForResult(mPhotoHelper.getCropIntent(mPhotoHelper.getCameraFilePath(), 200, 200), CODE_CLIP_PHOTO);
                    } catch (Exception e) {
                        mPhotoHelper.deleteCameraFile();
                        mPhotoHelper.deleteCropFile();
                        BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                        e.printStackTrace();
                    }
                    break;
                case CODE_CHOOSE_PHOTO:
                    try {
                        startActivityForResult(mPhotoHelper.getCropIntent(mPhotoHelper.getFilePathFromUri(data.getData()), 200, 200), CODE_CLIP_PHOTO);
                    } catch (IOException e) {
                        mPhotoHelper.deleteCropFile();
                        BGAPhotoPickerUtil.show(R.string.bga_pp_not_support_crop);
                        e.printStackTrace();
                    }
                    break;
                case CODE_CLIP_PHOTO:
                    mPresenter.upField(mPhotoHelper.getCropFilePath());
                    break;
                default:
                    break;
            }
        } else {
            if (requestCode == CODE_CLIP_PHOTO) {
                mPhotoHelper.deleteCameraFile();
                mPhotoHelper.deleteCropFile();
            }
        }
    }

    private void showSaveDialog() {
        mAlterHelper.showNormalDialog("您还没有保存",
                null,
                "保存",
                "取消",
                new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.postUserInfo(NullUtils.filterEmpty(mModify_UpFiledCode), NullUtils.filterEmpty(mModify_RoleId), NullUtils.filterEmpty(mModify_Email), "finish");
                    }
                },
                new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mItemSave.isVisible() && keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            showSaveDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        mItemSave = menu.findItem(R.id.action_save);
        mItemSave.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                mPresenter.postUserInfo(NullUtils.filterEmpty(mModify_UpFiledCode), NullUtils.filterEmpty(mModify_RoleId), NullUtils.filterEmpty(mModify_Email), "");
                return true;
            case android.R.id.home:
                if (mItemSave.isVisible()) {
                    showSaveDialog();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BGAPhotoHelper.onSaveInstanceState(mPhotoHelper, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        BGAPhotoHelper.onRestoreInstanceState(mPhotoHelper, savedInstanceState);
    }

}
