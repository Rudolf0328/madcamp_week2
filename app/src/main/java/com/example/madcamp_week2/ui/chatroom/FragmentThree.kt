//package com.example.madcamp_week2.ui.chatroom
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.ImageButton
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.RecyclerView
////import com.example.madcamp_week2.ui.chatroom.data.Dummy
//
//import com.example.madcamp_week2.R
//import com.example.madcamp_week2.databinding.FragmentChatroomBinding
//import com.example.madcamp_week2.ui.chatroom.data.Dummy_kt
//import com.example.madcamp_week2.ui.chatroom.data.RetrofitChatRoom
//import com.example.madcamp_week2.ui.chatroom.data.chatroomresult
//import com.example.madcamp_week2.ui.chatroom.data.chatroomsresult
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class FragmentThree : Fragment() {
//    lateinit var chatRoomRcvAdapter: FragmentThreeAdapter
//    var rcvChatRoom: RecyclerView? = null
//    var btnAdd: ImageButton? = null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        super.onCreate(savedInstanceState)
//        val view = inflater.inflate(R.layout.fragment_three, container, false) as ViewGroup
//        chatRoomRcvAdapter = FragmentThreeAdapter()
//        rcvChatRoom = view.findViewById<View>(R.id.chat_rcv_list2) as RecyclerView
//        rcvChatRoom!!.adapter = chatRoomRcvAdapter
//        btnAdd = view.findViewById<View>(R.id.chat_room_add) as ImageButton
//
//
//        for (i in 0..2) {
//            Dummy_kt.chatRoomList[i].toStringChatRoom()
//        }
//
//        val retrofit = Retrofit.Builder().baseUrl("http://172.10.18.77:80").addConverterFactory(
//            GsonConverterFactory.create()).build()
//        val server = retrofit.create(RetrofitChatRoom::class.java)
//        server.getChatRooms().enqueue((object: Callback<chatroomsresult> {
//            override fun onFailure(call: Call<chatroomsresult>, t: Throwable) {
//                Log.e("get chat rooms error", "error")
//                t.printStackTrace()
//            }
//
//            override fun onResponse(
//                call: Call<chatroomsresult>,
//                response: Response<chatroomsresult>
//            ) {
//                print("check here!!!")
//                print(response)
//            }
//        }))
//
//        chatRoomRcvAdapter!!.submitList(Dummy_kt.chatRoomList)
//        btnAdd!!.setOnClickListener {
//            val intent = Intent(context, AddChatRoomActivity_kt::class.java)
//            startActivity(intent)
//        }
//        return view
//    }
//}