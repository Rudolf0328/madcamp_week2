package com.example.madcamp_week2

import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

data class Upload(
    val id: String,
    val title: String?,
    val description: String?,
    val datetime: Long,
    val type: String,
    val animated: Boolean,
    val width: Int,
    val height: Int,
    val size: Int,
    val views: Int,
    val bandwidth: Int,
    val vote: Int?,
    val favorite: Boolean,
    val nsfw: Int?,
    val section: Int?,
    val accountUrl: String?,
    val account_id: Int,
    val isAd: Boolean,
    val in_most_viral: Boolean,
    val has_sound: Boolean,
    val tags: List<String>,
    val ad_type: Int,
    val ad_url: String,
    val edited: Int,
    val in_gallery: Boolean,
    val deletehash: String,
    val name: String,
    val link: String
)

data class ImgurResponse(
    val data: Upload,
    val success: Boolean,
    val status: Int,
)

interface Retrofit_imgur{
//    @Multipart
    @Multipart
    @POST("/3/upload")
    fun postRequest(
        @Header("Authorization") client_id:String,
        @Part image: MultipartBody.Part?
    ): Call<ImgurResponse>

}