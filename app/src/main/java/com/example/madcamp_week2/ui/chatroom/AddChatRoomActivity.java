package com.example.madcamp_week2.ui.chatroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.madcamp_week2.R;
import com.example.madcamp_week2.RetrofitService;
import com.example.madcamp_week2.ui.chatroom.data.ChatRoom;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddChatRoomActivity extends AppCompatActivity {
    ImageView imgImage;
    EditText edtName, edtMaxUser;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat_room);

        imgImage = findViewById(R.id.add_chat_room_img);
        edtName = findViewById(R.id.add_chat_room_edt_name);
        edtMaxUser = findViewById(R.id.add_chat_room_edt_maxUser);
        btnAdd = findViewById(R.id.add_chat_room_btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                int ownerId = Integer.parseInt(edtName.getText().toString());


                finish();
            }
        });
    }
}