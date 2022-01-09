package com.example.madcamp_week2.ui.chatroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madcamp_week2.R;
import com.example.madcamp_week2.ui.chat.ChatActivity;
import com.example.madcamp_week2.ui.chatroom.data.ChatRoom;

import java.util.List;

public class ChatRoomRcvAdapter extends RecyclerView.Adapter<ChatRoomRcvAdapter.ViewHolder> {
    private List<ChatRoom> chatRoomList;

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        System.out.println("on create view holder");
        return viewHolder;
    }

    public void submitList(List<ChatRoom> list) {
        chatRoomList = list;
        System.out.println("submit list");
        System.out.println(chatRoomList);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (chatRoomList == null)
            return 0;
        return chatRoomList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgvRoom;
        TextView tvName, tvCount;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            System.out.println("view holder");
            imgvRoom = (ImageView) itemView.findViewById(R.id.room_imgv_img);
            tvName = (TextView) itemView.findViewById(R.id.room_tv_name);
            tvCount = (TextView) itemView.findViewById(R.id.room_tv_count);
            context = itemView.getContext();
        }

        void onBind(ChatRoom chatRoom) {
            System.out.println("on bind");
            Glide.with(itemView)
                    .load(chatRoom.getImg())
                    .into(imgvRoom);
            tvName.setText(chatRoom.getName());
            String count = chatRoom.getPresent() + "/" + chatRoom.getMaxUser();
            tvCount.setText(count);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO : connect to chatting activity
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("name", chatRoom.getName());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        System.out.println("adapter");
        chatRoomList.get(position).toStringChatRoom();
        holder.onBind(chatRoomList.get(position));
    }

    private void removeItemView(int position) {
        chatRoomList.remove(position);

        notifyDataSetChanged();
    }
}
