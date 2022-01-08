package com.example.madcamp_week2.ui.home

import com.google.gson.annotations.SerializedName

class DataVo(val name: String, val address: String, val phonenumber: String, val photo: String)

data class newuserresult(val result: String)

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