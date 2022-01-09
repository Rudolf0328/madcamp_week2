package com.example.madcamp_week2.ui.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp_week2.R;
import com.example.madcamp_week2.databinding.FragmentChatroomBinding;
import com.example.madcamp_week2.ui.chatroom.data.Dummy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatRoomFragment extends Fragment {

    ChatRoomRcvAdapter chatRoomRcvAdapter;
    RecyclerView rcvChatRoom;
    Button btnAdd;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_chatroom, container, false);

        chatRoomRcvAdapter = new ChatRoomRcvAdapter();
        rcvChatRoom = (RecyclerView) view.findViewById(R.id.chat_rcv_list);
        rcvChatRoom.setAdapter(chatRoomRcvAdapter);

        btnAdd = (Button) view.findViewById(R.id.chat_btn_add);

//        for (int i = 0; i < 3; i++) {
//            Dummy.chatRoomList.get(i).toStringChatRoom();
//        }

        chatRoomRcvAdapter.submitList(Dummy.chatRoomList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddChatRoomActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}