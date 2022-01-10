package com.example.madcamp_week2.ui.chatroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week2.R
import com.example.madcamp_week2.ui.chatroom.data.ChatRoom

class FragmentThreeAdapter(private val context:Context) : RecyclerView.Adapter<FragmentThreeAdapter.ViewHolder>() {
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

        fun onBind(chatRoom: ChatRoom) {
            tvName.setText(chatRoom.name)
            val count: String = chatRoom.currentUser.toString() + "/" + chatRoom.maxUser
            tvCount.text = count
//            itemView.setOnClickListener { // TODO : connect to chatting activity
//                val intent = Intent(context, ChatActivity::class.java)
//                intent.putExtra("name", chatRoom.name)
//                context.startActivity(intent)
//            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("adapter")
        holder.onBind(chatRoomList[position])
    }

    private fun removeItemView(position: Int) {
        chatRoomList.removeAt(position)
        notifyDataSetChanged()
    }


}