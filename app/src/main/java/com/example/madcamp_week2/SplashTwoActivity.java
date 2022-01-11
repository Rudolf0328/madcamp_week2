package com.example.madcamp_week2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashTwoActivity extends Activity {
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        try {
            Thread.sleep(200);
        } catch (Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
