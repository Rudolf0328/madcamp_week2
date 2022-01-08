package com.example.madcamp_week2.ui.home

import com.google.gson.annotations.SerializedName

class Feed(val nickName:String, val time:String, val content:String, val image:String)

data class User(
    @SerializedName("nickname")
    val username: String,
    @SerializedName("profile")
    val userThumnail: String,
    @SerializedName("id")
    val id: String, )

data class ChatRoom (
    @SerializedName("name")
    val name: String,
    @SerializedName("ownerId")
    val ownerId: String,
    @SerializedName("maxUser")
    val maxUser: String,
    @SerializedName("image")
    val image: String
    )