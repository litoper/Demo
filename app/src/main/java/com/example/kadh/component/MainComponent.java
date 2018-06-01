package com.example.kadh.component;

import com.example.kadh.ui.home.MianActivity;
import com.example.kadh.ui.login.fragment.LoginNormalFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface MainComponent {
    MianActivity inject(MianActivity activity);

    LoginNormalFragment inject(LoginNormalFragment fragment);
}
