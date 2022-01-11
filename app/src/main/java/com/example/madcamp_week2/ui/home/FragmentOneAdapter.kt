package com.example.madcamp_week2.ui.home

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcamp_week2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CustomAdapter(private val context: Context, private val dataList: ArrayList<Feed>) :
    RecyclerView.Adapter<CustomAdapter.ItemViewHolder>() {

    var mPosition = 0

    fun getPosition(): Int {
        return mPosition
    }

    fun setPosition(position: Int) {
        mPosition = position
    }

    fun addItem(dataVo: Feed, id:String, feedId: String) {
        dataVo.userId = id
        dataVo._id = feedId
        Log.e("temp", dataVo.userId!!)
        dataList.add(dataVo)
        //갱신처리 반드시 해야함
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < dataList.size) {
            dataList.removeAt(position)
            //notifyItemRemoved(position)
            //갱신처리 반드시 해야함
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userPhoto = itemView.findViewById<ImageView>(R.id.feed_image)
        private val userName = itemView.findViewById<TextView>(R.id.feed_userName)
        private val date = itemView.findViewById<TextView>(R.id.feed_date)
        private val frag1_content = itemView.findViewById<TextView>(R.id.feed_content)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataVo: Feed, context: Context) {
            Log.e("iu image", dataVo.image)
            Glide.with(context).load(dataVo.image).into(userPhoto)
            userName.text = dataVo.nickName
            date.text = dataVo.time
            frag1_content.text = dataVo.content

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.feed_layout, parent, false)
        return ItemViewHolder(view)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
//        holder.itemView.setOnClickListener { view ->
//            setPosition(position)
//            Toast.makeText(view.context, "이름:" + dataList[position].nickName + " " + "전화번호:" + dataList[position].content + " 클릭!", Toast.LENGTH_SHORT).show()
//        }

        val delete = holder.itemView.findViewById<ImageButton>(R.id.chat_room_delete)
        val edit = holder.itemView.findViewById<ImageButton>(R.id.chat_room_fix)


        edit.setOnClickListener {
            Toast.makeText(context,"피드 수정 중", Toast.LENGTH_SHORT).show()
            val profile =
                LayoutInflater.from(context).inflate(R.layout.view_item_layout_feed_post, null)
            val alertDialog = AlertDialog.Builder(context)
                .setView(profile)
                .create()

            val image = profile.findViewById<ImageView>(R.id.userImg_feedpost)
            val nickName = profile.findViewById<TextView>(R.id.userName_feedpost)
            val content = profile.findViewById<EditText>(R.id.content_feedpost)
            val date = profile.findViewById<TextView>(R.id.date_feedpost)
            val button = profile.findViewById<Button>(R.id.photo_button_feedpost)
            val sendButton = profile.findViewById<Button>(R.id.sent_FeedPost)

            button.visibility = View.INVISIBLE

            Glide.with(image).load(dataList[position].image).into(image)
            image.visibility = View.VISIBLE
            nickName.text = dataList[position].nickName
            content.setText(dataList[position].content)
            date.text = dataList[position].time

            alertDialog.show()

            val today = LocalDate.now()
            val Strnow = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))



            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                GsonConverterFactory.create()).build()
            var server = retrofit.create(RetrofitUser::class.java)

            //피드 수정
            sendButton.setOnClickListener {
                server.editFeed(dataList[position]._id, Strnow, dataList[position].image, content.text.toString() ).enqueue((object: Callback<editFeedResult> {
                    override fun onFailure(call: Call<editFeedResult>, t: Throwable) {
                        Log.e("come", "here")
                    }
                    override fun onResponse(call: Call<editFeedResult>, response: Response<editFeedResult>) {
                        if(response.body()!! == null){
                            Log.e("come", "here3")
                        }else {
                            Log.e("come", "here2")
                            alertDialog.dismiss()
                        }

                    }
                }))
            }
        }


        delete.setOnClickListener {
            Toast.makeText(context,"피드 삭제 중", Toast.LENGTH_SHORT).show()
            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
                GsonConverterFactory.create()).build()
            var server = retrofit.create(RetrofitUser::class.java)

            //유저 정보 불러오기
            Log.e("temp", dataList[position].toString())
            server.deleteFeed(dataList[position]._id, dataList[position].userId!!).enqueue((object: Callback<deleteFeedResult> {
                override fun onFailure(call: Call<deleteFeedResult>, t: Throwable) {
                    Log.e("response1", "error")
                    t.printStackTrace()
                }
                override fun onResponse(call: Call<deleteFeedResult>, response: Response<deleteFeedResult>) {
                    if (response?.body() != null ) {
                        Toast.makeText(context, "피드 삭제 성공", Toast.LENGTH_SHORT).show()


                    }else{
                        Toast.makeText(context,"피드 삭제 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }))
        }

//        holder.itemView.setOnLongClickListener { view ->
//            Toast.makeText(context,"피드 삭제 중", Toast.LENGTH_SHORT).show()
//            val retrofit = Retrofit.Builder().baseUrl("http://192.249.18.77:80").addConverterFactory(
//                GsonConverterFactory.create()).build()
//            var server = retrofit.create(RetrofitUser::class.java)
//
//            //유저 정보 불러오기
//            Log.e("temp", dataList[position].toString())
//            server.deleteFeed(dataList[position]._id, dataList[position].userId!!).enqueue((object: Callback<deleteFeedResult> {
//                override fun onFailure(call: Call<deleteFeedResult>, t: Throwable) {
//                    Log.e("response1", "error")
//                    t.printStackTrace()
//                }
//                override fun onResponse(call: Call<deleteFeedResult>, response: Response<deleteFeedResult>) {
//                    if (response?.body() != null ) {
//                        Toast.makeText(context, "피드 삭제 성공", Toast.LENGTH_SHORT).show()
//
//
//                    }else{
//                        Toast.makeText(context,"피드 삭제 실패", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }))
//            return@setOnLongClickListener true
//        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}