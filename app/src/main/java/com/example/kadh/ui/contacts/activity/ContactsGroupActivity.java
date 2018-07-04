package com.example.kadh.ui.contacts.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.contacts.adapter.ContactsGroupAdapter;
import com.example.kadh.ui.contacts.bean.ContactsBean;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.dbhelp.CmpDBHelper;
import com.example.kadh.view.LoadingLayout;
import com.example.kadh.view.indexbar.suspension.DividerItemDecoration;
import com.example.kadh.view.indexbar.suspension.SuspensionDecoration;
import com.example.kadh.view.indexbar.widget.IndexBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactsGroupActivity extends BaseActivity {

    @BindView(R.id.activity_contacts_group_rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.activity_contacts_group_loading)
    LoadingLayout mLoading;
    @BindView(R.id.activity_contacts_group_indexbar)
    IndexBar mIndexbar;
    @BindView(R.id.activity_contacts_group_tv_barhint)
    TextView mTvBarhint;
    private String mTitle;
    private String mLevel;
    private String mId;
    private LinearLayoutManager mLayoutManager;
    private SuspensionDecoration mSuspensionDecoration;
    private DividerItemDecoration mDividerItemDecoration;
    private CmpDBHelper mDbHelper;
    private List<ContactsBean> mDisplayModels;
    private ContactsGroupAdapter mGroupAdapter;
    private List<Map> mContactLists;//查询出的人员列表数据
    private List<Map> mGroupList; //查询出筛选用的部门及小组的数据

    @Override
    public void configViews() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mSuspensionDecoration = new SuspensionDecoration(this, null);
        mDividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL_LIST, 1, Color.parseColor("#ececec"));
        mRvContent.setLayoutManager(mLayoutManager);
        mRvContent.addItemDecoration(mSuspensionDecoration);
        mRvContent.addItemDecoration(mDividerItemDecoration);
        mIndexbar.setmPressedShowTextView(mTvBarhint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mLayoutManager);//设置RecyclerView的LayoutManager
        mGroupAdapter = new ContactsGroupAdapter(R.layout.item_contacts_group, mDisplayModels, mLevel);
        mGroupAdapter.setOnItemChildClickListener(mGroupAdapter);
        mRvContent.setAdapter(mGroupAdapter);
    }

    @SuppressLint("CheckResult")
    @Override
    public void initDatas() {
        mDisplayModels = new ArrayList<>();
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("title");
        mLevel = getIntent().getStringExtra("level");
        mCommonToolbar.setTitle(mTitle);


        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                        mDbHelper = CmpDBHelper.getInstance();
                        switch (NullUtils.filterEmpty(mLevel)) {
                            case "0"://公司
                                mContactLists = mDbHelper.queryListMap("select id,uname,uphone,ushort_phone,ufirstspell,uwholespell,uposition from cmp_user where isdel='0'  order by uwholespell asc"
                                        , new String[]{});
                                break;
                            case "1"://部门
                                mContactLists = mDbHelper.queryListMap("select id,uname,uphone,ushort_phone,ufirstspell,uwholespell,uposition from cmp_user where ufdepartment_id=? and isdel='0'  order by uwholespell asc"
                                        , new String[]{mId});
                                mGroupList = mDbHelper.queryListMap("select id,oname from cmp_org where otype = 2"
                                        , new String[]{});
                                break;
                            case "2"://小组
                                mContactLists = mDbHelper.queryListMap("select cmp_user.id,cmp_user.uname,cmp_user.uphone, cmp_user.ushort_phone,cmp_user.ufirstspell,cmp_user.uwholespell,cmp_role.rname from cmp_user_role_ass JOIN cmp_role ON cmp_user_role_ass.cmp_role_id = cmp_role.id and cmp_role.isdel='0' JOIN cmp_user ON cmp_user_role_ass.cmp_user_id = cmp_user.id and cmp_user.isdel='0' where (cmp_role.cmp_org_id IN (select id from cmp_org where cmp_org.ofather = ? and cmp_org.isdel='0') OR cmp_role.cmp_org_id = ?) and cmp_user_role_ass.isdel='0'  order by cmp_user.uwholespell asc"
                                        , new String[]{mId, mId});
                                mGroupList = mDbHelper.queryListMap("select id,oname from cmp_org where otype in( '1', '3' )"
                                        , new String[]{});
                                break;
                            default:
                                break;
                        }
                        processContactModel(mContactLists);
                        emitter.onNext("");
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        switch (mLevel) {
                            case "0":
                                break;
                            case "1":
                                break;
                            case "2":
                                break;
                            default:
                                break;
                        }
                        mGroupAdapter.notifyDataSetChanged();
                        mLoading.showContent();
                    }
                });

    }

    private void processContactModel(List<Map> groupLists) {
        mDisplayModels.clear();
        for (Map map : groupLists) {
            ContactsBean modelNew = new ContactsBean();
            modelNew.setId(String.valueOf(map.get("id") == null ? "" : map.get("id")));
            modelNew.setUids(String.valueOf(map.get("uids") == null ? "" : map.get("uids")));
            modelNew.setUname(String.valueOf(map.get("uname") == null ? "" : map.get("uname")));
            modelNew.setRname(String.valueOf(map.get("rname") == null ? "" : map.get("rname")));
            modelNew.setUshort_phone(String.valueOf(map.get("ushort_phone") == null ? "" : map.get("ushort_phone")));
            modelNew.setUposition(String.valueOf(map.get("uposition") == null ? "" : map.get("uposition")));
            modelNew.setUjob(String.valueOf(map.get("ujob") == null ? "" : map.get("ujob")));
            modelNew.setUfirstspell(String.valueOf(map.get("ufirstspell") == null ? "" : map.get("ufirstspell")));
            modelNew.setUwholespell(String.valueOf(map.get("uwholespell") == null ? "" : map.get("uwholespell")));
            modelNew.setUphone(String.valueOf(map.get("uphone") == null ? "" : map.get("uphone")));
            modelNew.setUimage(String.valueOf(map.get("uimage") == null ? "" : map.get("uimage")));
            modelNew.setUfdepartment_id(String.valueOf(map.get("ufdepartment_id") == null ? "" : map.get("ufdepartment_id")));
            modelNew.setUfdepartment(String.valueOf(map.get("ufdepartment") == null ? "" : map.get("ufdepartment")));
            modelNew.setCreate_time(String.valueOf(map.get("create_time") == null ? "" : map.get("create_time")));
            modelNew.setUpdate_time(String.valueOf(map.get("update_time") == null ? "" : map.get("update_time")));
            modelNew.setBaseIndexPinyin("");
            modelNew.setBaseIndexTag("");
            mDisplayModels.add(modelNew);
        }

        mIndexbar.setmSourceDatas(mDisplayModels)//设置数据
//                    .setHeaderViewCount(7)//设置HeaderView数量
                .invalidate();
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contacts_group;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
