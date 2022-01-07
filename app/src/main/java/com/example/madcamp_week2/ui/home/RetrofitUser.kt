package com.example.madcamp_week2.ui.home

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

data class testresult(val nickName: String, val _id:String, val profile:String)

interface RetrofitUser{

    @GET("/user/ck071608000")
    fun test(): Call<testresult>


    @FormUrlEncoded
    @POST("api/user/")
    fun postRequest(@Field("nickName")username:String,
        @Field("profile")userThumnail: String,
        @Field("id")id:String): Call<newuserresult>

}