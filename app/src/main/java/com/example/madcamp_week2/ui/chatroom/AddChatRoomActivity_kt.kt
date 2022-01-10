package com.example.madcamp_week2.ui.chatroom

//import com.example.madcamp_week2.ui.home.ChatRoom
import android.graphics.Color
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

        val edtName = findViewById<EditText>(R.id.add_chat_room_edt_name)
        val edtMaxUser = findViewById<EditText>(R.id.add_chat_room_edt_maxUser)
        val editTime = findViewById<EditText>(R.id.add_chat_room_edt_time)

        var imageSet = 0

        val image1 = findViewById<ImageView>(R.id.add_chat_img_one)
        val image2 = findViewById<ImageView>(R.id.add_chat_img_two)
        val image3 = findViewById<ImageView>(R.id.add_chat_img_three)
        val image4 = findViewById<ImageView>(R.id.add_chat_img_four)
        val image5 = findViewById<ImageView>(R.id.add_chat_img_five)
        val image6 = findViewById<ImageView>(R.id.add_chat_img_six)
        val image7 = findViewById<ImageView>(R.id.add_chat_img_seven)
        val image8 = findViewById<ImageView>(R.id.add_chat_img_eight)
        val image9 = findViewById<ImageView>(R.id.add_chat_img_nine)

        fun setAllWhite(){
            image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
            image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        image1.setOnClickListener {
            if (imageSet == 1) {
                imageSet = 0
                setAllWhite()
            } else {
                imageSet = 1
                image1.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image2.setOnClickListener {
            if (imageSet == 2) {
                imageSet = 0
                setAllWhite()
            } else {
                imageSet = 2
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image3.setOnClickListener {
            if(imageSet == 3){
                imageSet = 0
                setAllWhite()
            }else {
                imageSet = 3
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image4.setOnClickListener {
            if(imageSet == 4){
                imageSet = 0
                setAllWhite()
            }else {
                imageSet = 4
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image5.setOnClickListener {
            if(imageSet == 5){
                imageSet = 0
                setAllWhite()
            }else {
                imageSet = 5
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image6.setOnClickListener {
            if(imageSet == 6){
                imageSet = 0
                setAllWhite()
            }else {
                imageSet = 6
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image7.setOnClickListener {
            if(imageSet == 7){
                imageSet = 0
                setAllWhite()
            }else {
                imageSet = 7
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image8.setOnClickListener {
            if(imageSet == 8){
                imageSet = 0
                setAllWhite()
            }else {
                imageSet = 8
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFF3DA"))
                image9.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
        image9.setOnClickListener {
            if(imageSet == 9){
                imageSet = 0
                setAllWhite()
            }else {
                imageSet = 9
                image1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image5.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image6.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image7.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image8.setBackgroundColor(Color.parseColor("#FFFFFF"))
                image9.setBackgroundColor(Color.parseColor("#FFF3DA"))
            }
        }



        val btnAdd = findViewById<Button>(R.id.add_chat_room_btn_add)
        btnAdd.setOnClickListener{
            if(imageSet == 0) {
                Toast.makeText(baseContext, "이미지를 선택해주세요", Toast.LENGTH_SHORT).show()
            }else {

                val intent = intent

                val userEmail = intent.getStringExtra("userEmail")!!

                Log.e("frag3", userEmail)

                val name = edtName.text
                val maxUser = edtMaxUser.text
                val time = editTime.text.toString()
                val chatRoom = ChatRoom(
                    "?",
                    name.toString(),
                    userEmail,
                    ArrayList<kotlin.String>(),
                    maxUser.toString(),
                    1,
                    true,
                    imageSet,
                    time
                )
                Log.e("frag3", chatRoom.owner)

                //
                //                Result result = new Result(chatRoom);


                val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                val retrofitService = retrofit.create(RetrofitService_kt::class.java)


                val res: Call<ArrayResult> = retrofitService.addChatRoom(
                    chatRoom.name,
                    chatRoom.owner,
                    chatRoom.maxUser.toInt(),
                    imageSet,
                    chatRoom.time
                )
                res.enqueue(object : Callback<ArrayResult> {
                    override fun onResponse(
                        call: Call<ArrayResult>,
                        response: Response<ArrayResult>
                    ) {
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
}