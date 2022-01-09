package com.example.madcamp_week2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.madcamp_week2.databinding.ActivityMainBinding;
//import com.example.madcamp_week2.ui.chatroom.AddChatRoomActivity_kt;
import com.example.madcamp_week2.ui.home.RetrofitUser;
import com.example.madcamp_week2.ui.home.registrationresult;
import com.example.madcamp_week2.ui.home.testresult;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageView profile_image;

    String nickName = "temp";
    String profile = "temp";
    String id = "temp";

//    private Bitmap getBitmap(String url) {
//        URL imgUrl = null;
//        HttpURLConnection connection = null;
//        InputStream is = null;
//        Bitmap retBitmap = null;
//        System.out.println("reach here1");
//        try{
//            System.out.println("reach here3");
//            imgUrl = new URL(url);
//            connection = (HttpURLConnection) imgUrl.openConnection();
//            connection.setDoInput(true);
//            System.out.println("reach here4");
//            connection.connect();
//            System.out.println("reach here5");
//            is = connection.getInputStream();
//            retBitmap = BitmapFactory.decodeStream(is);
//            System.out.println("reach here2");
//            System.out.println(retBitmap.getHeight());
//        }catch(Exception e) {
//            e.printStackTrace();
//            return null;
//        }finally {
//            if(connection!=null) {
//                connection.disconnect();
//            } return retBitmap;
//        }
//    }



    void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null){
                    id = user.getKakaoAccount().getEmail();
                    nickName = user.getKakaoAccount().getProfile().getNickname();
                    profile = user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                    Log.e("test", id);
                    Log.e("test", nickName);
                    Log.e("test", profile);

                    Bitmap bitmap = null;

                    System.out.println("reach here1");
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(GsonConverterFactory.create()).build();
                    RetrofitUser jsonPlaceHolderApi = retrofit.create(RetrofitUser.class);
                    Call<registrationresult> reg = jsonPlaceHolderApi.postRequest(nickName, profile, id);
                    Call<testresult> call = jsonPlaceHolderApi.test(id);
                    System.out.println("reach here1");

                    //카카오톡은 계정이 db에 없어도 바로 들어가도록 등록
                    reg.enqueue(new Callback<registrationresult>() {
                        @Override
                        public void onResponse(Call<registrationresult> call, Response<registrationresult> response) {
                            Log.e("main1", "reach here");
                        }

                        @Override
                        public void onFailure(Call<registrationresult> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //로그인 후 Mainactivity2 이동
                    call.enqueue(new Callback<testresult>() {
                        @Override
                        public void onResponse(Call<testresult> call, Response<testresult> response) {
                            Log.e("main test", response.body().toString());
                            nickName = response.body().getNickName();
                            profile = response.body().getProfile();
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                            intent.putExtra("userName", nickName);
                            intent.putExtra("userThumbnail", profile);
                            intent.putExtra("userEmail", id);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<testresult> call, Throwable t) {

                        }
                    });

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

        Button Log_in;
        Log_in = (Button) findViewById(R.id.start_log_in);

        System.out.println("reach here");

        Log_in.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input_id = (EditText) findViewById(R.id.input_id);
                id = input_id.getText().toString();

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(GsonConverterFactory.create()).build();
                RetrofitUser jsonPlaceHolderApi = retrofit.create(RetrofitUser.class);
                Call<testresult> call = jsonPlaceHolderApi.test(id);

                // 카톡 말고 계정으로 로그인
                call.enqueue(new Callback<testresult>() {
                    @Override
                    public void onResponse(Call<testresult> call, Response<testresult> response) {
                        if(response.body() != null) {
                            nickName = response.body().getNickName();
                            profile = response.body().getProfile();
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                            intent.putExtra("userName", nickName);
                            intent.putExtra("userThumbnail", profile);
                            intent.putExtra("userEmail", id);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "계정이 없습니다", Toast.LENGTH_SHORT).show();
                            Log.e("Test", "no account");
                        }
                    }

                    @Override
                    public void onFailure(Call<testresult> call, Throwable t) {

                    }
                });
            }
        }) ;


        //등록하러 가는 버튼
        TextView buttonReg = (TextView) findViewById(R.id.new_account) ;
        buttonReg.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("degud", "test");

                Intent intent = new Intent(MainActivity.this, MainRegistration.class);


//                intent.putExtra("userid", userid);
//                intent.putExtra("userName", userName);
//                intent.putExtra("userThumbnail", userThumbnail);
                startActivity(intent);
            }
        });


        //카카오톡으로 로그인, 위에꺼는 카톡 깔려 있을때, 밑에꺼는 카카오톡 안깔렸을때
        kakao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(MainActivity.this, new Function2<OAuthToken, Throwable, Unit>() {
                        @Override
                        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                            if (oAuthToken != null){
                                Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                System.out.println("dddddddd");
                                updateKakaoLoginUi();


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
                                System.out.println("dsfasdfasdfadsffdsa");
                                updateKakaoLoginUi();

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



    }



}