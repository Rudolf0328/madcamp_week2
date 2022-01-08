package com.example.madcamp_week2.ui.chatroom.data

import com.example.madcamp_week2.R
import java.util.ArrayList

class Dummy_kt {
    var rice = R.drawable.img_rice
    var susi = R.drawable.img_susi
    var salad = R.drawable.img_salad

    companion object {
        var chatRoomList: ArrayList<ChatRoomKT> = object : ArrayList<ChatRoomKT>() {
            init {
                add(ChatRoomKT(R.drawable.img_rice, "rice", 4, 2))
                add(ChatRoomKT(R.drawable.img_susi, "susi", 5, 1))
                add(ChatRoomKT(R.drawable.img_salad, "salad", 10, 3))
            }
        }
    }
}