package com.example.kadh.component;

import com.example.kadh.ui.main.activity.MainActivity;
import com.example.kadh.ui.login.fragment.LoginNormalFragment;
import com.example.kadh.ui.login.fragment.LoginVerifyFragment;

import dagger.Component;

//@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
@Component(dependencies = {AppComponent.class, ActivityComponent.class})
public interface MainComponent {

    void inject(MainActivity activity);

    void inject(LoginNormalFragment fragment);

    void inject(LoginVerifyFragment fragment);
}
