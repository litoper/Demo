package com.example.kadh.component;

import com.example.kadh.ui.company.activity.NewsDetailActivity;
import com.example.kadh.ui.company.fragment.CompanyFragment;
import com.example.kadh.ui.contacts.fragment.ContactsFragment;
import com.example.kadh.ui.login.fragment.LoginNormalFragment;
import com.example.kadh.ui.login.fragment.LoginVerifyFragment;
import com.example.kadh.ui.main.activity.MainActivity;
import com.example.kadh.ui.person.activity.PersonInfoActivity;
import com.example.kadh.ui.work.fragment.WorkFragment;

import dagger.Component;

//@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
//@Component(dependencies = {AppComponent.class, ActivityComponent.class})
@Component(dependencies = {AppComponent.class})
public interface MainComponent {

    void inject(MainActivity activity);

    void inject(LoginNormalFragment fragment);

    void inject(LoginVerifyFragment fragment);

    void inject(CompanyFragment fragment);

    void inject(NewsDetailActivity activity);


    void inject(WorkFragment fragment);

    void inject(PersonInfoActivity activity);

    void inject(ContactsFragment fragment);

}
