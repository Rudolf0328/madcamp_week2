//package com.example.madcamp_week2.ui.chat;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageButton;
//
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.madcamp_week2.R;
//
//import java.util.ArrayList;
//
//public class ChatActivity extends Activity {
//    private ArrayList<Chat> dataList;
//    ImageButton btnSend;
//    EditText edtMessage;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat);
//
//        // TODO : convert dummy to db
//        initData();
//
//        RecyclerView rcvChat = findViewById(R.id.chat_rcv_item);
//        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
//        rcvChat.setLayoutManager(manager);
//        ChatRcvAdapter chatRcvAdapter = new ChatRcvAdapter(dataList);
//        rcvChat.setAdapter(chatRcvAdapter);
//
//        edtMessage = (EditText) findViewById(R.id.chat_edt_message);
//        btnSend = (ImageButton) findViewById(R.id.chat_btn_send);
//
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String msg = edtMessage.getText().toString();
//                Chat newChat = new Chat(msg, "사용자2", Code.ViewType.RIGHT_CONTENT);
//                dataList.add(newChat);
//                chatRcvAdapter.notifyDataSetChanged();
//                edtMessage.setText("");
//            }
//        });
//    }
//
//    private void initData(){
//        dataList = new ArrayList<>();
//        dataList.add(new Chat("시용자1님 입장했음",null,Code.ViewType.CENTER_CONTENT));
//        dataList.add(new Chat("사용자2님 입장했음",null,Code.ViewType.CENTER_CONTENT));
//        dataList.add(new Chat("안녕하세요11","사용자1",Code.ViewType.LEFT_CONTENT));
//        dataList.add(new Chat("안녕하세요22","사용자2",Code.ViewType.RIGHT_CONTENT));
//
//    }
//}