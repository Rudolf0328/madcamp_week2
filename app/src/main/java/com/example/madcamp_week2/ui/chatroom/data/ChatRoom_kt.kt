package com.example.madcamp_week2.ui.chatroom.data

import java.util.*

data class ChatRoom (
    val _id: String,
    val name: String,
    val owner: String,
    val people: ArrayList<String>,
    val maxUser: String,
    val currentUser: Int,
    val status: Boolean,
    val image: Int,
    val time: String
)