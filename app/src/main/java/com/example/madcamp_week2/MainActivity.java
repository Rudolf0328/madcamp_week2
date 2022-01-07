package com.example.madcamp_week2;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageView profile_image;
    long userid = 0;
    String userName = "temp";
    String userThumbnail = "temp";

    void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null){
                    userid = user.getId();
                    userName = user.getKakaoAccount().getProfile().getNickname();
                    userThumbnail = user.getKakaoAccount().getProfile().getThumbnailImageUrl();
//                    Log.e("test", String.valueOf(userid));
//                    Log.e("test", userName);
//                    Log.e("test", userThumbnail);
                    Glide.with(profile_image).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profile_image);

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                    Log.e("test1", String.valueOf(userid));
//                    Log.e("test2", userName);
//                    Log.e("test3", userThumbnail);

                    intent.putExtra("userid", userid);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userThumbnail", userThumbnail);
                    startActivity(intent);
                }else{
                    Log.e("test", "fail");
                }
                return null;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        profile_image = findViewById(R.id.profile_image);



        ImageView kakao_login;
        kakao_login = (ImageView) findViewById(R.id.kakao_login);
        kakao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(MainActivity.this, new Function2<OAuthToken, Throwable, Unit>() {
                        @Override
                        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                            if (oAuthToken != null){
                                Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                updateKakaoLoginUi();

                                Intent intent = new Intent( MainActivity.this, MainActivity2.class);
                                startActivity(intent);

                            }
                            if (throwable != null){
                                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                            }
                            return null;
                        }
                    });
                }else{
                    UserApiClient.getInstance().loginWithKakaoAccount(MainActivity.this, new Function2<OAuthToken, Throwable, Unit>() {
                        @Override
                        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                            if (oAuthToken != null){
                                Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                updateKakaoLoginUi();

//                                    Intent intent = new Intent(MainActivity.this, MainLogin.class);
//                                    Log.e("test1", String.valueOf(userid));
//                                    Log.e("test2", userName);
//                                    Log.e("test3", userThumbnail);
//
//                                    intent.putExtra("userid", userid);
//                                    intent.putExtra("userName", userName);
//                                    intent.putExtra("userThumbnail", userThumbnail);
//                                    startActivity(intent);
                            }
                            if (throwable != null){
                                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                            }
                            return null;
                        }
                    });

                }
            }
        });


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
////         Passing each menu ID as a set of Ids because each
////         menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_chatroom, R.id.navigation_setting)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
////        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
    }



}