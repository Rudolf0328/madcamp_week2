package com.example.madcamp_week2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashOneActivity extends Activity {
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        try {
            System.out.println("splash");
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, SplashTwoActivity.class);
        startActivity(intent);
        finish();
    }
}
