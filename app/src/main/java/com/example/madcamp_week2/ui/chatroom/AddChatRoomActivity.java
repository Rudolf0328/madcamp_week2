//package com.example.madcamp_week2.ui.chatroom;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.madcamp_week2.R;
//import com.example.madcamp_week2.RetrofitService;
//import com.example.madcamp_week2.ui.chatroom.data.ArrayResult;
//import com.example.madcamp_week2.ui.chatroom.data.ChatRoom;
//
//import java.io.IOException;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class AddChatRoomActivity extends AppCompatActivity {
//    ImageView imgImage;
//    EditText edtName, edtMaxUser;
//    Button btnAdd;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_chat_room);
//
//        imgImage = findViewById(R.id.add_chat_room_img);
//        edtName = findViewById(R.id.add_chat_room_edt_name);
//        edtMaxUser = findViewById(R.id.add_chat_room_edt_maxUser);
//        btnAdd = findViewById(R.id.add_chat_room_btn_add);
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = edtName.getText().toString();
//                String maxUser = edtMaxUser.getText().toString();
//                ChatRoom chatRoom = new ChatRoom();
//                chatRoom.setName(name);
//                chatRoom.setMaxUser(maxUser);
//                chatRoom.setOwner("ck07160@naver.com");
//                chatRoom.setStatus(true);
//                chatRoom.setCurrentUser(0);
////
////                Result result = new Result(chatRoom);
//
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://192.249.18.77:80/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//                Call<ArrayResult> res = retrofitService.addChatRoom(chatRoom);
//                res.enqueue(new Callback<ArrayResult>() {
//                    @Override
//                    public void onResponse(Call<ArrayResult> call, Response<ArrayResult> response) {
//                        ArrayResult result = response.body();
//                        if (response.isSuccessful()) {
//                            Log.e("ddddddddddddddd", String.valueOf(result.getResult()));
//                        }
//                        else {
//                            try {
//                                Log.e("err",response.errorBody().string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
////                        for(int i = 0; i<result.size(); i++) {
////                            ArrayList<String> people = result.get(i).getPeople();
////                            int currentUser = people.size();
////                            ChatRoom chatRoom = new ChatRoom(result.get(i).getImg(), result.get(i).getName(), result.get(i).getMaxUser(), currentUser, people);
////                            chatRoomArrayList.add(chatRoom);
////                    makeMarker(result.get(i).getId(),result.get(i).getLat(),result.get(i).getLng());
////                    Log.e("id", String.valueOf(result.get(i).getLat())+ " "+String.valueOf(result.get(i).getLng()));
//                    }
//
////                        System.out.println(chatRoomArrayList);
////                        chatRoomRcvAdapter.submitList(chatRoomArrayList);
////              }
//                    @Override
//                    public void onFailure(Call<ArrayResult> call, Throwable t) {
//                        Log.e("failed","failed");
//                        t.printStackTrace();
//                    }
//                });
//
//                finish();
//            }
//        });
//    }
//}
