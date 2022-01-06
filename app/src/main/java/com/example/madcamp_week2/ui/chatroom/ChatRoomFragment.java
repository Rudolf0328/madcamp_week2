package com.example.madcamp_week2.ui.chatroom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp_week2.R;
import com.example.madcamp_week2.databinding.FragmentChatroomBinding;
import com.example.madcamp_week2.ui.chatroom.data.Dummy;

public class ChatRoomFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
//    private FragmentChatroomBinding binding;

    ChatRoomRcvAdapter chatRoomRcvAdapter;
    RecyclerView rcvChatRoom;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_chatroom, container, false);

        chatRoomRcvAdapter = new ChatRoomRcvAdapter();
        rcvChatRoom = (RecyclerView) view.findViewById(R.id.chat_rcv_list);
        rcvChatRoom.setAdapter(chatRoomRcvAdapter);

        for (int i = 0; i < 3; i++) {
            Dummy.chatRoomList.get(i).toStringChatRoom();
        }

        chatRoomRcvAdapter.submitList(Dummy.chatRoomList);

//        chatRcvAdapter.notifyDataSetChanged();

        return view;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}