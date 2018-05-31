package com.example.kadh.component;

import com.example.kadh.demo.MianActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface MainComponent {
    MianActivity inject(MianActivity activity);

}
