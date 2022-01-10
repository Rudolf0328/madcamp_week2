//package com.example.madcamp_week2.ui.chatroom;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.madcamp_week2.R;
//import com.example.madcamp_week2.RetrofitService;
//import com.example.madcamp_week2.ui.chatroom.data.ChatRoom;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ChatRoomFragment extends Fragment {
//
//    ChatRoomRcvAdapter chatRoomRcvAdapter;
//    RecyclerView rcvChatRoom;
//    ImageButton btnAdd;
//
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_three, container, false);
//
//        ArrayList<ChatRoom> chatRoomArrayList = new ArrayList<>();
//
//        chatRoomRcvAdapter = new ChatRoomRcvAdapter();
//        rcvChatRoom = (RecyclerView) view.findViewById(R.id.chat_rcv_list2);
//        rcvChatRoom.setAdapter(chatRoomRcvAdapter);
//
//        chatRoomRcvAdapter.notifyDataSetChanged();
//
//        btnAdd = (ImageButton) view.findViewById(R.id.chat_room_add);
//
////        for (int i = 0; i < 3; i++) {
////            Dummy.chatRoomList.get(i).toStringChatRoom();
////        }
//
////        chatRoomArrayList = connectingServer();
////        chatRoomRcvAdapter.submitList(chatRoomArrayList);
////        chatRoomRcvAdapter.submitList(Dummy.chatRoomList);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.249.18.77:80/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        Call<ArrayList<ChatRoom>> res = retrofitService.getAllChatRoom();
//        res.enqueue(new Callback<ArrayList<ChatRoom>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ChatRoom>> call, Response<ArrayList<ChatRoom>> response) {
//                ArrayList<ChatRoom> result = response.body();
//                if (response.isSuccessful()) {
//                    Log.e("test", String.valueOf(result.get(0).getName()));
//                }
//                else {
//                    try {
//                        Log.e("err",response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                for(int i = 0; i<result.size(); i++) {
//                    ArrayList<String> people = result.get(i).getPeople();
//                    System.out.println(people);
//                    int currentUser = people.size();
//                    ChatRoom chatRoom = new ChatRoom(result.get(i).get_id(), result.get(i).getImage(), result.get(i).getName(), result.get(i).getMaxUser(), currentUser, people);
//                    chatRoomArrayList.add(chatRoom);
////                    makeMarker(result.get(i).getId(),result.get(i).getLat(),result.get(i).getLng());
////                    Log.e("id", String.valueOf(result.get(i).getLat())+ " "+String.valueOf(result.get(i).getLng()));
//                }
//
//                System.out.println(chatRoomArrayList);
//                chatRoomRcvAdapter.submitList(chatRoomArrayList);
//            }
//            @Override
//            public void onFailure(Call<ArrayList<ChatRoom>> call, Throwable t) {
//                Log.e("failed","failed");
//                t.printStackTrace();
//            }
//        });
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), AddChatRoomActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        return view;
//    }
//}
