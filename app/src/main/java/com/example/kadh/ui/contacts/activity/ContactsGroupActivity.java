package com.example.kadh.ui.contacts.activity;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactsGroupActivity extends BaseActivity {

    private String mTitle;
    private String mLevel;
    private String mId;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("title");
        mLevel = getIntent().getStringExtra("level");

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contacts_group;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
