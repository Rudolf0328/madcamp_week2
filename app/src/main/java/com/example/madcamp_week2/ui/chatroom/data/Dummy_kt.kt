package com.example.madcamp_week2.ui.chatroom.data

import com.example.madcamp_week2.R
import java.util.*

class Dummy {
    var rice = R.drawable.img_rice
    var susi = R.drawable.img_susi
    var salad = R.drawable.img_salad

    companion object {
        var chatRoomList: ArrayList<ChatRoom> = object : ArrayList<ChatRoom>() {
            var people: ArrayList<String> = object : ArrayList<String>() {
                init {
                    add("dk")
                }
            }
        }
    }
}