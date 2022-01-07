package com.example.madcamp_week2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.madcamp_week2.databinding.ActivityMainBinding;
import com.example.madcamp_week2.ui.chatroom.AddChatRoomActivity;
import com.example.madcamp_week2.ui.home.FragmentOne;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.madcamp_week2.databinding.ActivityMainBinding;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApi;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.kakao.sdk.common.KakaoSdk;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class MainLogin extends AppCompatActivity {
    Context context = getApplicationContext();

    private ActivityMainBinding  binding;
//    private ImageView profile_image;
//    long userid;
//    String userName;
//    String userThumbnail;
//
//    private void updateKakaoLoginUi() {
//        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
//            @Override
//            public Unit invoke(User user, Throwable throwable) {
//                if (user != null){
//                    userid = user.getId();
//                    userName = user.getKakaoAccount().getProfile().getNickname();
//                    userThumbnail = user.getKakaoAccount().getProfile().getThumbnailImageUrl();
//                    Glide.with(profile_image).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profile_image);
//                }else{
//                    Log.e("test", "fail");
//                }
//                return null;
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ImageView profile_image = (ImageView) findViewById(R.id.loadingImage);
//
//
//
//        ImageView kakao_login;
//        kakao_login = (ImageView) findViewById(R.id.kakao_login);
//        kakao_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainLogin.this)){
//                    UserApiClient.getInstance().loginWithKakaoTalk(MainLogin.this, new Function2<OAuthToken, Throwable, Unit>() {
//                        @Override
//                        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
//                            if (oAuthToken != null){
//                                Toast.makeText(MainLogin.this, "Login success", Toast.LENGTH_SHORT).show();
//                                updateKakaoLoginUi();
//
//                                Intent intent = new Intent( MainLogin.this, MainActivity.class);
//                                startActivity(intent);
//
//                            }
//                            if (throwable != null){
//                                Toast.makeText(MainLogin.this, "Login Fail", Toast.LENGTH_SHORT).show();
//                            }
//                            return null;
//                        }
//                    });
//                }else{
//                    UserApiClient.getInstance().loginWithKakaoAccount(MainLogin.this, new Function2<OAuthToken, Throwable, Unit>() {
//                        @Override
//                        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
//                            if (oAuthToken != null){
//                                Toast.makeText(MainLogin.this, "Login success", Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent( MainLogin.this, MainActivity.class);
//                                startActivity(intent);
//                            }
//                            if (throwable != null){
//                                Toast.makeText(MainLogin.this, "Login Fail", Toast.LENGTH_SHORT).show();
//                            }
//                            updateKakaoLoginUi();
//                            return null;
//                        }
//                    });
//
//                }
//            }
//        });


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_chatroom, R.id.navigation_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fl_container);


        Intent intent = getIntent();

        Log.e("id", String.valueOf(intent.getLongExtra("userid", 0)));
        Log.e("userName", intent.getStringExtra("userName"));
        Log.e("userThumnail", intent.getStringExtra("userThumbnail"));

        FragmentOne fragmentOne = new FragmentOne();
        Bundle bundle = new Bundle();
        bundle.putString("id", String.valueOf(intent.getLongExtra("userid", 0)));
        bundle.putString("userName", intent.getStringExtra("userName"));
        bundle.putString("userThumnail", intent.getStringExtra("userThumbnail"));
        fragmentOne.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragmentOne).commit();
//        navController.navigate(R.id.navigation_home, bundle);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
//
//        Log.e("id", String.valueOf(intent.getLongExtra("userid", 0)));
//        Log.e("userName", intent.getStringExtra("userName"));
//        Log.e("userThumnail", intent.getStringExtra("userThumbnail"));
//        Glide.with(profile_image).load(intent.getStringExtra("userThumbnail")).circleCrop().into(profile_image);


    }



}