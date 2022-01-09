//package com.example.madcamp_week2.ui.chat
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import android.widget.EditText
//import android.widget.ImageButton
//import android.widget.TextView
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.madcamp_week2.R
//import java.util.ArrayList
//
//class ChatActivity_kt : Activity() {
//    private var dataList: ArrayList<Chat_kt> = ArrayList()
//    var btnSend: ImageButton? = null
//    var edtMessage: EditText? = null
//    var tvChatRoomName: TextView? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chat)
//
//        // TODO : convert dummy to db
//        initData()
//        val rcvChat: RecyclerView = findViewById(R.id.chat_rcv_item)
//        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        rcvChat.layoutManager = manager
//        val chatRcvAdapter = ChatRcvAdapter(dataList)
//        rcvChat.adapter = chatRcvAdapter
//        edtMessage = findViewById<View>(R.id.chat_edt_message) as EditText
//        btnSend = findViewById<View>(R.id.chat_btn_send) as ImageButton
//        tvChatRoomName = findViewById<View>(R.id.chat_tv_name) as TextView
//
//        tvChatRoomName!!.text = intent.getStringExtra("chatRoomName")
//        btnSend!!.setOnClickListener {
//            val msg = edtMessage!!.text.toString()
//            val newChat =
//                Chat_kt(msg, "사용자2", Code_kt.ViewType.RIGHT_CONTENT)
//            dataList!!.add(newChat)
//            chatRcvAdapter.notifyDataSetChanged()
//            edtMessage!!.setText("")
//        }
//    }
//
//    private fun initData() {
//        dataList = ArrayList()
//        dataList!!.add(Chat_kt("시용자1님 입장했음", null, Code_kt.ViewType.CENTER_CONTENT))
//        dataList!!.add(Chat_kt("사용자2님 입장했음", null, Code_kt.ViewType.CENTER_CONTENT))
//        dataList!!.add(Chat_kt("안녕하세요11", "사용자1", Code_kt.ViewType.LEFT_CONTENT))
//        dataList!!.add(Chat_kt("안녕하세요22", "사용자2", Code_kt.ViewType.RIGHT_CONTENT))
//    }
//}