package com.example.kadh.ui.tension.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivityView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.tension.contract.TensionSubmitAtvContract;
import com.example.kadh.ui.tension.presenter.TensionSubmitBinding;
import com.example.kadh.view.SwitchButton;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/21
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class TensionSubmitActivity extends BaseActivityView<TensionSubmitBinding> implements TensionSubmitAtvContract.View, BGASortableNinePhotoLayout.Delegate {
    @BindView(R.id.tension_submit_tv_role_content)
    TextView mTvRoleContent;
    @BindView(R.id.tension_submit_ll_role)
    LinearLayout mLlRole;
    @BindView(R.id.tension_submit_tv_theme_content)
    TextView mTvThemeContent;
    @BindView(R.id.tension_submit_ll_theme)
    LinearLayout mLlTheme;
    @BindView(R.id.tension_submit_et_explains)
    EditText mEtExplains;
    @BindView(R.id.tension_submit_tv_explains_hint)
    TextView mTvExplainsHint;
    @BindView(R.id.tension_submit_et_proposal)
    EditText mEtProposal;
    @BindView(R.id.tension_submit_bgalyt_image)
    BGASortableNinePhotoLayout mBgaLytImage;
    @BindView(R.id.tension_submit_tv_file)
    TextView mTvFile;
    @BindView(R.id.tension_submit_iv_addfile)
    ImageView mIvAddfile;
    @BindView(R.id.tension_submit_rv_file)
    RecyclerView mRvFile;
    @BindView(R.id.tension_submit_tv_is_anonymous)
    TextView mTvIsAnonymous;
    @BindView(R.id.tension_submit_iv_shudong_help)
    ImageView mIvShudongHelp;
    @BindView(R.id.tension_submit_ll_is_anonymous)
    LinearLayout mLlIsAnonymous;
    @BindView(R.id.tension_submit_sb_shudong)
    SwitchButton mSbShudong;
    @BindView(R.id.tension_submit_rl_is_anonymous)
    RelativeLayout mRlIsAnonymous;
    @BindView(R.id.tension_submit_cb_state_autonym)
    CheckBox mCbStateAutonym;
    @BindView(R.id.tension_submit_cb_state_anonym)
    CheckBox mCbStateAnonym;
    @BindView(R.id.tension_submit_ll_autonym_hint)
    LinearLayout mLlAutonymHint;
    @BindView(R.id.tension_submit_ll_shudong_state)
    LinearLayout mLlShudongState;
    @BindView(R.id.tension_submit_iv_duty_dep_name)
    ImageView mIvDutyDepName;
    @BindView(R.id.tension_submit_tv_duty_dep_name)
    TextView mTvDutyDepName;
    @BindView(R.id.tension_submit_rl_duty_dep_name)
    RelativeLayout mRlDutyDepName;
    @BindView(R.id.tension_submit_tv_notification)
    TextView mTvNotification;
    @BindView(R.id.tension_submit_sb_notification)
    SwitchButton mSbNotification;
    @BindView(R.id.tension_submit_rl_notification)
    RelativeLayout mRlNotification;
    @BindView(R.id.tension_submit_tv_notice_dep_name)
    TextView mTvNoticeDepName;
    @BindView(R.id.tension_submit_iv_notice_dep_name)
    ImageView mIvNoticeDepName;
    @BindView(R.id.tension_submit_ll_notice_dep_name)
    LinearLayout mLlNoticeDepName;
    @BindView(R.id.tension_submit_tv_notify_person)
    TextView mTvNotifyPerson;
    @BindView(R.id.tension_submit_gv_notify_person)
    GridView mGvNotifyPerson;
    @BindView(R.id.tension_submit_ll_notify_person)
    LinearLayout mLlNotifyPerson;
    @BindView(R.id.tension_submit_ll_root)
    LinearLayout mLlRoot;
    @BindView(R.id.tension_submit_btn_submit)
    Button mBtnSubmit;
    private ArrayList<String> mSelectedPhotos;

    @Override
    public void configViews() {
        mBgaLytImage.setDelegate(this);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
        mCommonToolbar.setTitle("张力提报");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tension_submit;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @OnClick({R.id.tension_submit_ll_role, R.id.tension_submit_ll_theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tension_submit_ll_role:
                break;
            case R.id.tension_submit_ll_theme:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mBgaLytImage.removeItem(position);
        mBgaLytImage.requestLayout();
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mBgaLytImage.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, 666);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }


    @SuppressLint("CheckResult")
    private void choicePhotoWrapper() {
        new RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                if (aBoolean) {
                    // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
                    File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "picture");
                    Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(mContext)
                            .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                            //                .maxChooseCount(mBgaLytImage.getMaxItemCount() - mBgaLytImage.getItemCount()) // 图片选择张数的最大值
                            .maxChooseCount(10) // 图片选择张数的最大值
                            .selectedPhotos(mSelectedPhotos) // 当前已选中的图片路径集合
                            .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                            .build();
                    startActivityForResult(photoPickerIntent, 888);
                } else {
                    Toast.makeText(mContext, "缺乏权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mSelectedPhotos = data.getStringArrayListExtra("EXTRA_SELECTED_PHOTOS");
            if (requestCode == 888) {
                mBgaLytImage.setData(BGAPhotoPickerActivity.getSelectedPhotos(data));
                //            mBgaLytImage.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
            } else if (requestCode == 666) {
                mBgaLytImage.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
            }
        }
    }
}
