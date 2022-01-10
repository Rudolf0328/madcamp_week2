package com.example.madcamp_week2.ui.home

import retrofit2.Call
import retrofit2.http.*

data class testresult(val nickName: String, val _id:String, val profile:String)
data class chatroomresult(val _id: String, val name: String, val ownerId: String, val maxUser: String, val currentUser: String, val img: String)

data class registrationresult(val result: String)

data class deleteUserResult(val result: String)

data class feedPostResult(val result: String)

data class UserInfo(val nickName: String, val profile: String, val feeds: ArrayList<String>)

data class getEveryFeedResult(val feeds:ArrayList<Feed>)

data class joinRoomResult(val result: String)



interface RetrofitUser{

    @GET("api/user/{post}")
    fun test(@Path("post") post:String ): Call<testresult>


    //User 정보 받아오기
    @GET("api/user/{post}")
    fun getUser(@Path("post")userID:String): Call<UserInfo>


    //User 등록
    @FormUrlEncoded
    @POST("api/user/")
    fun postRequest(@Field("nickName")username:String,
                    @Field("profile")userThumnail: String,
                    @Field("id")id:String): Call<registrationresult>


    //피드 등록
    @FormUrlEncoded
    @POST("api/feed/{post}")
    fun postFeed(
        @Path("post") post:String,
        @Field("nickName")nickName:String,
        @Field("content")content:String,
        @Field("image")image:String,
        @Field("time")time:String): Call<feedPostResult>

    //피드 정보 가져오기
    @GET("api/feed/{post}")
    fun getFeed(@Path("post")FeedID:String): Call<Feed>

    //모든 피드 정보 가져오기
    @GET("api/feed")
    fun getEveryFeed(): Call<getEveryFeedResult>

    //피드 삭제
    @DELETE("api/feed/{post}")
    fun deleteFeed(): Call<getEveryFeedResult>


   //방에 참가
    @FormUrlEncoded
    @PUT("api/chatroom/{post}")
    fun joinRoom(@Path("post") roomid: String,
                 @Field("userId") userId:String
    ): Call<joinRoomResult>

    @DELETE("api/user/{post}")
    fun deleteUser(@Path("post")post: String): Call<deleteUserResult>

}