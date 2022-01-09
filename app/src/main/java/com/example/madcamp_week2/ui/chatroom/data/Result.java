package com.example.madcamp_week2.ui.chatroom.data;

public class Result {
    private ChatRoom chatRoom;

    public Result() {}

    public Result(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }
}
