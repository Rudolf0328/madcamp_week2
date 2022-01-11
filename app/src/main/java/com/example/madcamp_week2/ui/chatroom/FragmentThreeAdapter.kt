package com.example.madcamp_week2.ui.chatroom

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcamp_week2.R
import com.example.madcamp_week2.ui.chatroom.data.ChatRoom
import com.example.madcamp_week2.ui.home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentThreeAdapter(private val context:Context, val userEmail: String) : RecyclerView.Adapter<FragmentThreeAdapter.ViewHolder>() {
    private var chatRoomList = ArrayList<ChatRoom>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_room_item, parent, false)
        val viewHolder: ViewHolder = ViewHolder(view)
        println("on create view holder")
        return viewHolder
    }

    fun submitList(list: ArrayList<ChatRoom>) {
        chatRoomList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (chatRoomList == null) 0 else chatRoomList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgvRoom = itemView.findViewById<ImageView>(R.id.room_imgv_img)
        val tvName = itemView.findViewById<TextView>(R.id.room_tv_name)
        val tvCount = itemView.findViewById<TextView>(R.id.room_tv_count)
        val tvTime = itemView.findViewById<TextView>(R.id.chat_tv_time)

        val room_tv_one = itemView.findViewById<ImageView>(R.id.room_tv_one)
        val room_tv_two = itemView.findViewById<ImageView>(R.id.room_tv_two)
        val room_tv_three = itemView.findViewById<ImageView>(R.id.room_tv_three)
        val room_tv_four = itemView.findViewById<ImageView>(R.id.room_tv_four)
        val room_tv_five = itemView.findViewById<ImageView>(R.id.room_tv_five)
        val room_tv_six = itemView.findViewById<ImageView>(R.id.room_tv_six)
        val room_tv_seven = itemView.findViewById<ImageView>(R.id.room_tv_seven)
        val room_tv_eight = itemView.findViewById<ImageView>(R.id.room_tv_eight)


        fun onBind(chatRoom: ChatRoom) {
            tvName.setText(chatRoom.name)
            val count: String = chatRoom.people.size.toString() + "/" + chatRoom.maxUser
            tvCount.text = count
            tvTime.text = chatRoom.time

            val usernum = chatRoom.people.size


            if(chatRoom.maxUser.toInt() >= 1) {
                room_tv_one.visibility = View.VISIBLE
            }
            if(chatRoom.maxUser.toInt() >= 2) {
                room_tv_two.visibility = View.VISIBLE
            }
            if(chatRoom.maxUser.toInt() >= 3) {
                room_tv_three.visibility = View.VISIBLE
            }
            if(chatRoom.maxUser.toInt() >= 4) {
                room_tv_four.visibility = View.VISIBLE
            }
            if(chatRoom.maxUser.toInt() >= 5) {
                room_tv_five.visibility = View.VISIBLE
            }
            if(chatRoom.maxUser.toInt() >= 6) {
                room_tv_six.visibility = View.VISIBLE
            }
            if(chatRoom.maxUser.toInt() >= 7) {
                room_tv_seven.visibility = View.VISIBLE
            }
            if(chatRoom.maxUser.toInt() >= 8) {
                room_tv_eight.visibility = View.VISIBLE
            }


            Log.e("room id", chatRoom._id)

            val roomImage = chatRoom.image
            Log.e("roomImage", roomImage.toString())
            if(roomImage == 1){
                Glide.with(imgvRoom).load(R.drawable.ic_steak).into(imgvRoom)
            }else if(roomImage == 2){
                Glide.with(imgvRoom).load(R.drawable.ic_chadayun).into(imgvRoom)
            }else if(roomImage == 3){
                Glide.with(imgvRoom).load(R.drawable.ic_chadayunbabo).into(imgvRoom)
            }else if(roomImage == 4){
                Glide.with(imgvRoom).load(R.drawable.ic_chicken).into(imgvRoom)
            }else if(roomImage == 5){
                Glide.with(imgvRoom).load(R.drawable.ic_pizza).into(imgvRoom)
            }else if(roomImage == 6){
                Glide.with(imgvRoom).load(R.drawable.ic_hanburger).into(imgvRoom)
            }else if(roomImage == 7){
                Glide.with(imgvRoom).load(R.drawable.ic_soup).into(imgvRoom)
            }else if(roomImage == 8){
                Glide.with(imgvRoom).load(R.drawable.ic_tteokbokki).into(imgvRoom)
            }else if(roomImage == 9){
                Glide.with(imgvRoom).load(R.drawable.ic_spaguetti).into(imgvRoom)
            }

            for(i: Int in 0..chatRoom.people.size-1){
                val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                    GsonConverterFactory.create()).build()
                var server = retrofit.create(RetrofitUser::class.java)

                //피드 모두 불러오기
                server.getUser(chatRoom.people[i]).enqueue((object: Callback<UserInfo> {
                    override fun onFailure(call: Call<UserInfo>, t: Throwable) {


                    }
                    override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                        if(response.body() == null){

                        }else{
                            Log.e("whi??", response.body()!!.profile)

                            if(i == 0){
                                Glide.with(room_tv_one).load(response.body()!!.profile).circleCrop().into(room_tv_one)
                            }
                            if(i == 1){
                                Glide.with(room_tv_two).load(response.body()!!.profile).circleCrop().into(room_tv_two)
                            }
                            if(i == 2){
                                Glide.with(room_tv_three).load(response.body()!!.profile).circleCrop().into(room_tv_three)
                            }
                            if(i == 3){
                                Glide.with(room_tv_four).load(response.body()!!.profile).circleCrop().into(room_tv_four)
                            }
                            if(i == 4){
                                Glide.with(room_tv_five).load(response.body()!!.profile).circleCrop().into(room_tv_five)
                            }
                            if(i == 5){
                                Glide.with(room_tv_six).load(response.body()!!.profile).circleCrop().into(room_tv_six)
                            }
                            if(i == 6){
                                Glide.with(room_tv_seven).load(response.body()!!.profile).circleCrop().into(room_tv_seven)
                            }
                            if(i == 7){
                                Glide.with(room_tv_eight).load(response.body()!!.profile).circleCrop().into(room_tv_eight)
                            }

                        }
                    }
                }))

            }

            if(usernum >= 1) {
                room_tv_one.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[0]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }
            if(usernum >= 2) {
                room_tv_two.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[1]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }
            if(usernum >= 3) {
                room_tv_three.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[2]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }
            if(usernum >= 4) {
                room_tv_four.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[3]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }
            if(usernum >= 5) {
                room_tv_five.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[4]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }
            if(usernum >= 6) {
                room_tv_six.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[5]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }
            if(usernum >= 7) {
                room_tv_seven.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[6]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }
            if(usernum >= 8) {
                room_tv_one.setOnClickListener {
                    val retrofit =
                        Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                            GsonConverterFactory.create()
                        ).build()
                    var server = retrofit.create(RetrofitUser::class.java)
                    server.getUser(chatRoom.people[7]).enqueue((object : Callback<UserInfo> {
                        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<UserInfo>,
                            response: Response<UserInfo>
                        ) {
                            if (response.body() == null) {
                                Log.e("get user", response.body().toString())
                            } else {
                                val profile =
                                    LayoutInflater.from(context).inflate(R.layout.name_tag, null)
                                val alertDialog = AlertDialog.Builder(context)
                                    .setView(profile)
                                    .create()

                                val name = profile.findViewById<TextView>(R.id.one_tv_id)
                                val postnum = profile.findViewById<TextView>(R.id.one_tv_post_num)
                                val userThumbnail =
                                    profile.findViewById<ImageView>(R.id.one_imgv_profile)

                                name.text = response.body()!!.nickName
                                postnum.text = response.body()!!.feeds.size.toString()
                                Glide.with(userThumbnail).load(response.body()!!.profile)
                                    .centerCrop().into(userThumbnail)

                                alertDialog.show()


                            }
                        }
                    }))
                }
            }


        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("adapter")
        holder.onBind(chatRoomList[position])
        holder.itemView.setOnClickListener {
            val FeedPost =  LayoutInflater.from(context).inflate(R.layout.room_join, null)
            val alertDialog = AlertDialog.Builder(context)
                .setView(FeedPost)
                .create()

            val button = FeedPost.findViewById<Button>(R.id.room_join)
            val button_out = FeedPost.findViewById<Button>(R.id.room_out)
            if(chatRoomList[position].owner == userEmail){
                button_out.text = "방 터트리기"
            }


            button.setOnClickListener {
                if (userEmail in chatRoomList[position].people){
                    Toast.makeText(context, "이미 방에 참가하였습니다.", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }else{
                    val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                        GsonConverterFactory.create()).build()
                    var server = retrofit.create(RetrofitUser::class.java)

                    //방 참가하기
                    server.joinRoom(chatRoomList[position]._id ,userEmail).enqueue((object: Callback<joinRoomResult> {
                        override fun onFailure(call: Call<joinRoomResult>, t: Throwable) {
                            Log.e("frag3", "fail")
                            t.printStackTrace()
                        }
                        override fun onResponse(call: Call<joinRoomResult>, response: Response<joinRoomResult>) {
                            if(response.body() != null){
                                Toast.makeText(context, "방에 참가하였습니다.", Toast.LENGTH_SHORT).show()
                                alertDialog.dismiss()
                            }
                        }
                    }))


                }

            }

            button_out.setOnClickListener {
                if(chatRoomList[position].owner == userEmail){
                    val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                        GsonConverterFactory.create()).build()
                    var server = retrofit.create(RetrofitUser::class.java)

                    //방 참가하기
                    server.deleteRoom(chatRoomList[position]._id).enqueue((object: Callback<deleteRoomResult> {
                        override fun onFailure(call: Call<deleteRoomResult>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<deleteRoomResult>, response: Response<deleteRoomResult>) {
                            if(response.body() != null){
                                Toast.makeText(context, "방 터트렸습니다.", Toast.LENGTH_SHORT).show()
                                alertDialog.dismiss()
                            }
                        }
                    }))
                }else{
                    val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                        GsonConverterFactory.create()).build()
                    var server = retrofit.create(RetrofitUser::class.java)

                    //방 참가하기
                    server.quitRoom(chatRoomList[position]._id, userEmail).enqueue((object: Callback<quitRoomResult> {
                        override fun onFailure(call: Call<quitRoomResult>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<quitRoomResult>, response: Response<quitRoomResult>) {
                            if(response.body() != null){
                                Toast.makeText(context, "방에서 나갔습니다", Toast.LENGTH_SHORT).show()
                                alertDialog.dismiss()
                            }
                        }
                    }))


                }




            }

            alertDialog.show()


        }

    }

    private fun removeItemView(position: Int) {
        chatRoomList.removeAt(position)
        notifyDataSetChanged()
    }


}