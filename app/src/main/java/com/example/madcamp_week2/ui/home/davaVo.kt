package com.example.madcamp_week2.ui.home

import com.google.gson.annotations.SerializedName

data class Feed(var _id: String, val nickName:String?, val time:String, val image:String, val content:String, var userId: String?)

data class User(
    @SerializedName("nickname")
    val username: String,
    @SerializedName("profile")
    val userThumnail: String,
    @SerializedName("id")
    val id: String, )

//data class ChatRoom (
//    @SerializedName("name")
//    val name: String,
//    @SerializedName("ownerId")
//    val ownerId: String,
//    @SerializedName("maxUser")
//    val maxUser: String,
//    @SerializedName("image")
//    val image: String
//    )