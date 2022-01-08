package com.example.madcamp_week2.ui.chatroom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
//import com.example.madcamp_week2.ui.chatroom.data.Dummy

import com.example.madcamp_week2.R
import com.example.madcamp_week2.databinding.FragmentChatroomBinding
import com.example.madcamp_week2.ui.chatroom.data.Dummy_kt

class FragmentThree : Fragment() {
    lateinit var chatRoomRcvAdapter: FragmentThreeAdapter
    var rcvChatRoom: RecyclerView? = null
    var btnAdd: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_three, container, false) as ViewGroup
        chatRoomRcvAdapter = FragmentThreeAdapter()
        rcvChatRoom = view.findViewById<View>(R.id.chat_rcv_list2) as RecyclerView
        rcvChatRoom!!.adapter = chatRoomRcvAdapter
        btnAdd = view.findViewById<View>(R.id.chat_btn_add2) as Button
        for (i in 0..2) {
            Dummy_kt.chatRoomList[i].toStringChatRoom()
        }
        chatRoomRcvAdapter!!.submitList(Dummy_kt.chatRoomList)
        btnAdd!!.setOnClickListener {
            val intent = Intent(context, AddChatRoomActivity_kt::class.java)
            startActivity(intent)
        }
        return view
    }
}