package com.example.madcamp_week2.ui;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "f729563e0418d89c42e611a6b86153d4");
    }
}
