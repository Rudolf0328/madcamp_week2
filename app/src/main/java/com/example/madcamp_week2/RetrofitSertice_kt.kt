package com.example.madcamp_week2

import com.example.madcamp_week2.ui.chatroom.data.ArrayResult
import com.example.madcamp_week2.ui.chatroom.data.ChatRoom
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface RetrofitService_kt {
    @FormUrlEncoded
    @GET("api/chatroom/{id}")
    fun getChatRoom(
        @Path("id") id: String?
    ): Call<ChatRoom>

    @GET("api/chatroom/")
    fun getAllChatRoom(): Call<ArrayList<ChatRoom>>

    @FormUrlEncoded
    @POST("api/chatroom/")
    fun addChatRoom(@Field("name") name:String,
                    @Field("owner") ownerId:String,
                    @Field("maxUser") maxUser:Int,
                    @Field("image") image:Int,
                    @Field("time")time:String)
    :Call<ArrayResult>
}