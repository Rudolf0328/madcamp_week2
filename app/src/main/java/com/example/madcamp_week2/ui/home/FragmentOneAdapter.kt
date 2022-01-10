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


class CustomAdapter(private val context: Context, private val dataList: ArrayList<Feed>) :
    RecyclerView.Adapter<CustomAdapter.ItemViewHolder>() {

    var mPosition = 0

    fun getPosition(): Int {
        return mPosition
    }

    fun setPosition(position: Int) {
        mPosition = position
    }

    fun addItem(dataVo: Feed) {
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

            //사진 처리
//            if (dataVo.image != "") {
//                val resourceId =
//                    context.resources.getIdentifier(dataVo.image, "drawable", context.packageName)
//
//                if (resourceId > 0) {
//                    userPhoto.setImageResource(resourceId)
//                } else {
//                    userPhoto.setImageResource(R.drawable.restmb_idxmake)
//                }
//            } else {
//                userPhoto.setImageResource(R.drawable.restmb_idxmake)
//            }

//
//            //TextView에 데이터 세팅
//            userName.text = dataVo.nickName
//            val today = LocalDate.now()
//            val Strnow = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
//            val year = today.getYear().toString()
//            val month = today.getMonth().toString()
//            val day = today.getDayOfMonth().toString()
//
//            date.text = Strnow
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
            setPosition(position)
            Toast.makeText(view.context, "이름:" + dataList[position].nickName + " " + "전화번호:" + dataList[position].content + " 롱클릭!", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}