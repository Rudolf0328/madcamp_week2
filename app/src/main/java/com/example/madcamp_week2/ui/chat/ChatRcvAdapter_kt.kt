package com.example.madcamp_week2.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week2.R
import java.util.ArrayList

class ChatRcvAdapter(private val chatList: ArrayList<Chat_kt>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return if (viewType == Code_kt.ViewType.CENTER_CONTENT) {
            view = inflater.inflate(R.layout.chat_center_item, parent, false)
            CenterViewHolder(view)
        } else if (viewType == Code_kt.ViewType.LEFT_CONTENT) {
            view = inflater.inflate(R.layout.chat_left_item, parent, false)
            LeftViewHolder(view)
        } else {
            view = inflater.inflate(R.layout.chat_right_item, parent, false)
            RightViewHolder(view)
        }
    }

    // 실제 각 뷰 홀더에 데이터를 연결해주는 함수
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is CenterViewHolder) {
            viewHolder.textv.text = chatList[position].content
        } else if (viewHolder is LeftViewHolder) {
            viewHolder.textv_nicname.text = chatList[position].name
            viewHolder.textv_msg.text = chatList[position].content
        } else {
            (viewHolder as RightViewHolder).textv_msg.text = chatList[position].content
        }
    }

    // 리사이클러뷰안에서 들어갈 뷰 홀더의 개수
    override fun getItemCount(): Int {
        return chatList.size
    }

    // ★★★
    // 위에 3개만 오버라이드가 기본 셋팅임,
    // 이 메소드는 ViewType때문에 오버라이딩 했음(구별할려고)
    override fun getItemViewType(position: Int): Int {
        return chatList[position].viewType
    }

    // "리사이클러뷰에 들어갈 뷰 홀더", 그리고 "그 뷰 홀더에 들어갈 아이템들을 셋팅"
    inner class CenterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textv: TextView

        init {
            textv = itemView.findViewById<View>(R.id.textv) as TextView
        }
    }

    inner class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgv: ImageView
        var textv_nicname: TextView
        var textv_msg: TextView
        var textv_time: TextView

        init {
            imgv = itemView.findViewById<View>(R.id.imgv) as ImageView
            textv_nicname = itemView.findViewById<View>(R.id.textv_nicname) as TextView
            textv_msg = itemView.findViewById<View>(R.id.textv_msg) as TextView
            textv_time = itemView.findViewById<View>(R.id.textv_time) as TextView
        }
    }

    inner class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textv_msg: TextView
        var textv_time: TextView

        init {
            textv_msg = itemView.findViewById<View>(R.id.textv_msg) as TextView
            textv_time = itemView.findViewById<View>(R.id.textv_time) as TextView
        }
    }
}