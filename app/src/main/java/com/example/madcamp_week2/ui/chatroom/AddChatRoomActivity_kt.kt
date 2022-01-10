package com.example.madcamp_week2.ui.chatroom

//import com.example.madcamp_week2.ui.home.ChatRoom
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp_week2.R
import com.example.madcamp_week2.RetrofitService_kt
import com.example.madcamp_week2.ui.chatroom.data.ArrayResult
import com.example.madcamp_week2.ui.chatroom.data.ChatRoom
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class AddChatRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_chat_room)

//        val imgImage = findViewById<ImageView>(R.id.add_)
        val edtName = findViewById<EditText>(R.id.add_chat_room_edt_name)
        val edtMaxUser = findViewById<EditText>(R.id.add_chat_room_edt_maxUser)
        val btnAdd = findViewById<Button>(R.id.add_chat_room_btn_add)
        btnAdd.setOnClickListener{
            val name = edtName.text
            val maxUser = edtMaxUser.text
            val chatRoom = ChatRoom("?",name.toString(), "ck07160@naver.com", ArrayList<kotlin.String>(), maxUser.toString(), 1, true)

            //
            //                Result result = new Result(chatRoom);
            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80/")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val retrofitService = retrofit.create(RetrofitService_kt::class.java)


            val res: Call<ArrayResult> = retrofitService.addChatRoom(chatRoom.name, chatRoom.owner, chatRoom.maxUser.toInt(), "null")
            res.enqueue(object : Callback<ArrayResult> {
                override fun onResponse(call: Call<ArrayResult>, response: Response<ArrayResult>) {
                    val result = response.body()
                    Log.e("chat room add", result.toString())
                    Toast.makeText(baseContext, "채팅방 생성 완료", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<ArrayResult>, t: Throwable) {
                    Log.e("failed", "failed")
                    t.printStackTrace()
                }
            })
        }
    }
}