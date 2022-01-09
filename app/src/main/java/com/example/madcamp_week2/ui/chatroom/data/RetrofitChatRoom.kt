//package com.example.madcamp_week2.ui.chatroom.data
//
//import android.graphics.Bitmap
//import retrofit2.Call
//import retrofit2.http.*
//
//class chatroom(val name: String, val owner: String, val maxUser: String, val image: String) {
//    override fun toString(): String {
//        return "name: " + name + "owner: " + owner + "maxUser: " + maxUser + "image: " + image
//    }
//}
//
//data class chatroomresult(val _id: String, val name: String, val ownerId: String, val maxUser: String, val currentUser: String, val img: String)
//data class chatroomsresult(val chatrooms: Array<chatroom>)
//
//interface RetrofitChatRoom {
//    @GET("api/chatroom/61d82b5000eeaee3bb7e4ce1")
//    fun getChatRoom(): Call<chatroomresult>
//
//    @GET("api/chatroom/")
//    fun getChatRooms(): Call<chatroomsresult>
//
//    @FormUrlEncoded
//    @POST("api/chatroom/")
//    fun postChatRoom(@Field("name")name: String,
//                     @Field("ownerId")ownerId:String,
//                     @Field("maxUser")maxUser: String,
//                     @Field("image")image: String) : Call<chatroomresult>
//}