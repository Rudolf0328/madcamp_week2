package com.example.madcamp_week2.ui.chatroom

//import com.example.madcamp_week2.ui.home.ChatRoom
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week2.R
import com.example.madcamp_week2.RetrofitService_kt
import com.example.madcamp_week2.ui.chatroom.data.ChatRoom
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class FragmentThree : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_three, container, false) as ViewGroup
        val chatRoomArrayList = ArrayList<ChatRoom>()
        val chatRoomRcvAdapter = FragmentThreeAdapter(view.context)
        val rcvChatRoom = view.findViewById<RecyclerView>(R.id.chat_rcv_list2)
        rcvChatRoom.adapter = chatRoomRcvAdapter
        chatRoomRcvAdapter!!.notifyDataSetChanged()
        val btnAdd = view.findViewById<ImageButton>(R.id.chat_room_add)


        val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitService = retrofit.create(RetrofitService_kt::class.java)

        retrofitService.getAllChatRoom().enqueue(object : Callback<ArrayList<ChatRoom>> {
            override fun onResponse(call: Call<ArrayList<ChatRoom>>, response: Response<ArrayList<ChatRoom>>) {
                val result: ArrayList<ChatRoom> = response.body()!!
                for (i in result.indices) {
                    chatRoomArrayList.add(result[i])
                    //                    makeMarker(result.get(i).getId(),result.get(i).getLat(),result.get(i).getLng());
//                    Log.e("id", String.valueOf(result.get(i).getLat())+ " "+String.valueOf(result.get(i).getLng()));
                }
                chatRoomRcvAdapter!!.submitList(chatRoomArrayList)
            }
            override fun onFailure(call: Call<ArrayList<ChatRoom>>, t: Throwable) {
                Log.e("failed", "failed")
                t.printStackTrace()
            }
        })


        btnAdd.setOnClickListener {
            val intent = Intent(context, AddChatRoomActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}