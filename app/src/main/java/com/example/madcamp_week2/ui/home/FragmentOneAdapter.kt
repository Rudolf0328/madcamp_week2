package com.example.madcamp_week2.ui.home

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcamp_week2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        holder.itemView.setOnClickListener { view ->
            setPosition(position)
            Toast.makeText(view.context, "이름:" + dataList[position].nickName + " " + "전화번호:" + dataList[position].content + " 클릭!", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnLongClickListener { view ->
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
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}