package com.example.madcamp_week2.ui.home

import android.graphics.Bitmap
import retrofit2.Call
import retrofit2.http.*

data class testresult(val nickName: String, val _id:String, val profile:String)
data class chatroomresult(val _id: String, val name: String, val ownerId: String, val maxUser: String, val currentUser: String, val img: String)

data class registrationresult(val result: String)

data class deleteUserResult(val result: String)


interface RetrofitUser{

    @GET("api/user/{post}")
    fun test(@Path("post") post:String ): Call<testresult>


    @FormUrlEncoded
    @POST("api/user/")
    fun postRequest(@Field("nickName")username:String,
                    @Field("profile")userThumnail: String,
                    @Field("id")id:String): Call<registrationresult>

    @DELETE("api/user/{post}")
    fun deleteUser(@Path("post")post: String): Call<deleteUserResult>

}

interface RetrofitChatRoom {
    @GET("api/chatroom/61d82b5000eeaee3bb7e4ce1")
    fun getChatRoom(): Call<chatroomresult>

    @FormUrlEncoded
    @POST("api/chatroom/")
    fun postChatRoom(@Field("name")name: String,
    @Field("ownerId")ownerId:String,
    @Field("maxUser")maxUser: String,
    @Field("image")image: String) : Call<chatroomresult>
}