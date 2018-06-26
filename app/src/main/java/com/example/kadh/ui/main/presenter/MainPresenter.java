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

import io.reactivex.Flowable;

public class MainPresenter extends BaseBindingImpl<MainAtyContract.View> implements MainAtyContract.Presenter<MainAtyContract.View> {

    private RxApi mRxApi;
    private String mLocalTime;


    @Inject
    public MainPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }


    @Override
    public void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        CompanyFragment frgCompany = new CompanyFragment();
        WorkFragment frgWork = new WorkFragment();
        ContactsFragment frgTxl = new ContactsFragment();
        PersonFragment frgPerson = new PersonFragment();

        fragmentList.add(frgCompany);
        fragmentList.add(frgWork);
        fragmentList.add(frgTxl);
        fragmentList.add(frgPerson);

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
        mLocalTime = SpUtil.getInstance().getString(SpUtil.DB_UPDATE_TIME);

        Flowable useInfo = mRxApi.getUseInfo(new SubProtect<BaseResponse<List<UserInfoBean>>>(new SubNextImpl<BaseResponse<List<UserInfoBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UserInfoBean>> response) {
            }
        }), "");

        Flowable hasUnRead = mRxApi.isHasUnRead(new SubProtect<BaseResponse<IsHasUnReadBean>>(new SubNextImpl<BaseResponse<IsHasUnReadBean>>() {
            @Override
            public void onSubSuccess(BaseResponse<IsHasUnReadBean> response) {
                if (response.data != null) {
                    mView.showBadge(response.data);
                }
            }
        }));
        Flowable weather = mRxApi.getWeather(new SubProtect<BaseResponse<List<WeatherBean>>>(new SubNextImpl<BaseResponse<List<WeatherBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<WeatherBean>> response) {
                if (!NullUtils.isNull(response.data)) {
                    mView.showWeather(response.data.get(0));
                }
            }
        }), "温州");

        Flowable contact = mRxApi.getContactUpdateData(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {
                saveDataToDb(response.data);
            }
        }), "");


        mRxApi.toConcatSub(new SubProtect<BaseResponse>(new SubNextImpl<BaseResponse>() {
            @Override
            public void onSubSuccess(BaseResponse response) {

            }
        }), useInfo, weather, hasUnRead, contact);
    }

    private void saveDataToDb(String data) {
        CmpDBHelper.getInstance().beginTransaction();
        if (NullUtils.isNull(mLocalTime) && !CmpDBHelper.getInstance().isDbLockedByCurrentThread()) {
            CmpDBHelper.getInstance().rebuildAllTable();
        }
        String[] sqlites = data.split(";");
        for (String sqlite : sqlites) {
            CmpDBHelper.getInstance().execSQL(sqlite);
        }
        CmpDBHelper.getInstance().setTransactionSuccessful();
        CmpDBHelper.getInstance().endTransaction();
    }

}
