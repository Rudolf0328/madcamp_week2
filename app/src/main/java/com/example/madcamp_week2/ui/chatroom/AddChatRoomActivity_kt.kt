//package com.example.madcamp_week2.ui.chatroom
//
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.drawable.BitmapDrawable
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import androidx.appcompat.app.AppCompatActivity
//import com.example.madcamp_week2.R
//import com.example.madcamp_week2.ui.chatroom.data.RetrofitChatRoom
//import com.example.madcamp_week2.ui.home.RetrofitUser
//import com.example.madcamp_week2.ui.home.User
//import com.example.madcamp_week2.ui.chatroom.data.chatroomresult
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class AddChatRoomActivity_kt : AppCompatActivity() {
//    var imgImage: ImageView? = null
//    var edtName: EditText? = null
//    var edtMaxUser: EditText? = null
//    var btnAdd: Button? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_chat_room)
//
//        imgImage = findViewById<View>(R.id.add_chat_room_img) as ImageView;
//        edtName = findViewById<View>(R.id.add_chat_room_edt_name) as EditText;
//        edtMaxUser = findViewById<View>(R.id.add_chat_room_edt_maxUser) as EditText;
//        btnAdd = findViewById<View>(R.id.add_chat_room_btn_add) as Button;
//
//        btnAdd!!.setOnClickListener {
//            var name = edtName!!.text.toString()
//            var maxUser = edtMaxUser!!.text.toString()
//            // TODO : img 받기
//            var img = "none"
//            var ownerId = "ck071608000"
////            val data: ChatRoom = User(id, userName, userThumnail)
////            Bitmap img = ((BitmapDrawable)imgImage.getDrawable()).getBi
//            val retrofit = Retrofit.Builder().baseUrl("http://172.10.18.77:80").addConverterFactory(
//                GsonConverterFactory.create()).build()
//            val server = retrofit.create(RetrofitChatRoom::class.java)
//            server.postChatRoom(name, ownerId, maxUser, img).enqueue((object: Callback<chatroomresult> {
//                override fun onFailure(call: Call<chatroomresult>, t: Throwable) {
//                    Log.e("response1111", "error")
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(
//                    call: Call<chatroomresult>,
//                    response: Response<chatroomresult>
//                ) {
//                    Log.d("response : ", response?.body().toString())
//                }
//            }))
//            finish();
//        }
//    }
//}