package com.example.kadh.component;

import android.app.Activity;

import com.example.kadh.base.BaseActivity;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.module.ActivityModule;

import dagger.Component;

@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    BaseActivity inject(BaseActivity activity);

    BaseFragment inject(BaseFragment fragment);
}
