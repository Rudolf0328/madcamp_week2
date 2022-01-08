package com.example.madcamp_week2.ui.chatroom

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madcamp_week2.R
//import com.example.madcamp_week2.ui.chat.ChatActivity
import com.example.madcamp_week2.ui.chat.ChatActivity_kt
//import com.example.madcamp_week2.ui.chatroom.data.ChatRoom
import com.example.madcamp_week2.ui.chatroom.data.ChatRoomKT

class FragmentThreeAdapter : RecyclerView.Adapter<FragmentThreeAdapter.ViewHolder>() {
    private var chatRoomList: ArrayList<ChatRoomKT> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_room_item, parent, false)
        val viewHolder: ViewHolder = ViewHolder(view)
        println("on create view holder")
        return viewHolder
    }

    fun submitList(list: ArrayList<ChatRoomKT>) {
        chatRoomList = list
        println("submit list")
        println(chatRoomList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return chatRoomList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgvRoom: ImageView
        var tvName: TextView
        var tvCount: TextView
        var context: Context
        fun onBind(chatRoom: ChatRoomKT) {
            println("on bind")
            Glide.with(itemView)
                .load(chatRoom.img)
                .into(imgvRoom)
            tvName.text = chatRoom.name
            val count = chatRoom.present.toString() + "/" + chatRoom.total
            tvCount.text = count
            itemView.setOnClickListener { // TODO : connect to chatting activity
                val intent = Intent(context, ChatActivity_kt::class.java)
                intent.putExtra("chatRoomName", chatRoom.name)
                context.startActivity(intent)
            }
        }

        init {
            println("view holder")
            imgvRoom = itemView.findViewById<View>(R.id.room_imgv_img) as ImageView
            tvName = itemView.findViewById<View>(R.id.room_tv_name) as TextView
            tvCount = itemView.findViewById<View>(R.id.room_tv_count) as TextView
            context = itemView.context
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("adapter")
        chatRoomList!![position].toStringChatRoom()
        holder.onBind(chatRoomList!![position])
    }

    private fun removeItemView(position: Int) {
        chatRoomList.removeAt(position)
        notifyDataSetChanged()
    }
}