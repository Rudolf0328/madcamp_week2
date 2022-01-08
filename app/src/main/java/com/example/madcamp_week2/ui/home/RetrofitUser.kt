package com.example.madcamp_week2.ui.home

import android.graphics.Bitmap
import retrofit2.Call
import retrofit2.http.*

data class testresult(val nickName: String, val _id:String, val profile:String)

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