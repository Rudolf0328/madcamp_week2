package com.example.madcamp_week2.ui;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "2506062233b27e2eac713a0404c091fd");
    }
}
