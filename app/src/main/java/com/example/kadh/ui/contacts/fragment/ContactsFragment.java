package com.example.kadh.ui.contacts.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.contacts.activity.ContactsGroupActivity;
import com.example.kadh.ui.contacts.adapter.ContactDbAdapter;
import com.example.kadh.ui.contacts.adapter.ContactRecentAdapter;
import com.example.kadh.ui.contacts.bean.ContactRecentBean;
import com.example.kadh.ui.contacts.bean.ContractDbBean;
import com.example.kadh.ui.contacts.contract.ContactsFrgContract;
import com.example.kadh.ui.contacts.presenter.ContactsPresenter;

import java.util.List;

import butterknife.BindView;

public class ContactsFragment extends BaseFragmentView<ContactsPresenter> implements ContactsFrgContract.View {


    @BindView(R.id.fragment_contacts_rv_db)
    RecyclerView mRvDb;
    @BindView(R.id.fragment_contacts_rv_recent)
    RecyclerView mRvRecent;

    @BindView(R.id.common_toolbar)
    Toolbar mCommonToolbar;
    private ContactDbAdapter mDbAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_contracts;
    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initDatas() {
        mPresenter.queryDataFromDb();
        mPresenter.getRecentContact();
        initToolBar();
    }

    public void initToolBar() {
        mCommonToolbar.setTitle("东经科技");
        setHasOptionsMenu(true);
        ((AppCompatActivity) mActivity).setSupportActionBar(mCommonToolbar);
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
    public void showDataFromDb(final List<ContractDbBean> dbBeans) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mDbAdapter = new ContactDbAdapter(R.layout.item_contact_db, dbBeans);
        mRvDb.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRvDb.setLayoutManager(layoutManager);
        mRvDb.setAdapter(mDbAdapter);
        mDbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", dbBeans.get(position).getId());
                bundle.putString("title", dbBeans.get(position).getTitle());
                bundle.putString("level", dbBeans.get(position).getLevel());
                openActivity(ContactsGroupActivity.class, bundle);
            }
        });
    }

    @Override
    public void showRecentContact(ContactRecentBean data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRvRecent.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        ContactRecentAdapter recentAdapter = new ContactRecentAdapter(R.layout.item_contact_recent, data.getContactList());
        mRvRecent.setLayoutManager(layoutManager);
        mRvRecent.setAdapter(recentAdapter);
    }
}
