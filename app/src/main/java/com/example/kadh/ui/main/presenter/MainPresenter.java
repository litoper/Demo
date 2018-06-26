package com.example.kadh.ui.main.presenter;

import android.support.v4.app.Fragment;

import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.company.fragment.CompanyFragment;
import com.example.kadh.ui.contacts.fragment.ContactsFragment;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.main.bean.WeatherBean;
import com.example.kadh.ui.main.contract.MainAtyContract;
import com.example.kadh.ui.person.fragment.PersonFragment;
import com.example.kadh.ui.work.fragment.WorkFragment;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.utils.SpUtil;
import com.example.kadh.utils.dbhelp.CmpDBHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BaseBindingImpl<MainAtyContract.View> implements MainAtyContract.Presenter<MainAtyContract.View> {

    private RxApi mRxApi;
    private String mLocalTime;
    private ContactsFragment mContactsFragment;
    private WorkFragment mWorkFragment;
    private CompanyFragment mCompanyFragment;
    private PersonFragment mPersonFragment;


    @Inject
    public MainPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }


    @Override
    public void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        mCompanyFragment = new CompanyFragment();
        mWorkFragment = new WorkFragment();
        mContactsFragment = new ContactsFragment();
        mPersonFragment = new PersonFragment();

        fragmentList.add(mCompanyFragment);
        fragmentList.add(mWorkFragment);
        fragmentList.add(mContactsFragment);
        fragmentList.add(mPersonFragment);

        mView.showViewPager(fragmentList);
    }

    @Override
    public void initBottomNavigationBar() {
        mView.showBottomNavigationBar();
    }

    @Override
    public void initPush() {

    }

    @Override
    public void initSubListener() {
    }

    @Override
    public void getContactUpdateData(final String nowTime) {
        mLocalTime = SpUtil.getInstance().getString(SpUtil.DB_UPDATE_TIME);
        mRxApi.getContactUpdateData(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {
                saveDataToDb(response.data, nowTime);
            }
        }), NullUtils.filterNull(mLocalTime));

    }

    @Override
    public void getUseInfo() {
        mRxApi.getUseInfo(new SubProtect<BaseResponse<List<UserInfoBean>>>(new SubNextImpl<BaseResponse<List<UserInfoBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UserInfoBean>> response) {
            }
        }), "");
    }

    @Override
    public void isHasUnRead() {
        mRxApi.isHasUnRead(new SubProtect<BaseResponse<IsHasUnReadBean>>(new SubNextImpl<BaseResponse<IsHasUnReadBean>>() {
            @Override
            public void onSubSuccess(BaseResponse<IsHasUnReadBean> response) {
                if (response.data != null) {

                    mView.showBadge(response.data);
                    mCompanyFragment.upMessageState(NullUtils.filterNull(response.data.getUnReadMsgState()));

                    switch (response.data.getUpdateStatus()) {
                        case "1":
                            mContactsFragment.updating();
                            getContactUpdateData(response.data.getNowTime());
                            break;
                        default:
                            break;
                    }
                }
            }
        }));
    }

    @Override
    public void getWeather() {
        mRxApi.getWeather(new SubProtect<BaseResponse<List<WeatherBean>>>(new SubNextImpl<BaseResponse<List<WeatherBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<WeatherBean>> response) {
                if (!NullUtils.isNull(response.data)) {
                    mView.showWeather(response.data.get(0));
                }
            }
        }), "温州");

    }

    private void saveDataToDb(String data, String nowTime) {
        if (NullUtils.isNull(data)) {
            SpUtil.getInstance().putString(SpUtil.DB_UPDATE_TIME, nowTime);
        } else {
            try {
                CmpDBHelper.getInstance().beginTransaction();
                if (NullUtils.isNull(mLocalTime) && !CmpDBHelper.getInstance().isDbLockedByCurrentThread()) {
                    CmpDBHelper.getInstance().rebuildAllTable();
                }
                String[] sqlites = data.split(";");
                for (String sqlite : sqlites) {
                    CmpDBHelper.getInstance().execSQL(sqlite);
                }
                CmpDBHelper.getInstance().setTransactionSuccessful();
                SpUtil.getInstance().putString(SpUtil.DB_UPDATE_TIME, nowTime);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                CmpDBHelper.getInstance().endTransaction();
            }
        }
        mContactsFragment.complete();
    }

}
