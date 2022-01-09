package com.example.madcamp_week2;

import com.example.madcamp_week2.ui.chatroom.data.ChatRoom;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {
    @FormUrlEncoded
    @GET("api/chatroom/{id}")
    Call<ChatRoom> getChatRoom(
            @Path("id") String id
    );

    @GET("api/chatroom/")
    Call<ArrayList<ChatRoom>> getAllChatRoom();

//    @POST("api/")
//    Call<ChatRoom> addToilet(@Body ChatRoom chatRoom);
//    @FormUrlEncoded
//    @PUT("toilet/{id}")
//    Call<String> putToilet(
//            @Path("id") String id,
//            @Field("score") double score,
//            @Field("comment") String comment
//    );
//    @GET("toilet/avg/{id}")
//    Call<String> getScore(
//            @Path("id") String id
//    );
//
//    @GET("toilet/id/{id}")
//    Call<ChatRoom> getReviewData(
//            @Path("id") String id
//    );
//
//    @GET("v2/local/geo/coord2regioncode.JSON")
//    Call<AddrResult> getAddress(
//            @Header("Authorization") String key,
//            @Query("x") String x,
//            @Query("y") String y
//    );
//
//
//    @GET("trash")
//    Call<ArrayList<Result>> getTrash();
//
//    @POST("trash/add")
//    Call<Result> addTrash(@Body Result result);
//    @FormUrlEncoded
//    @PUT("trash/{id}")
//    Call<String> putTrash(
//            @Path("id") String id,
//            @Field("score") double score,
//            @Field("comment") String comment
//    );
//    @GET("trash/avg/{id}")
//    Call<String> getTrashScore(
//            @Path("id") String id
//    );
//
//    @GET("trash/id/{id}")
//    Call<Result> getTrashReviewData(
//            @Path("id") String id
//    );
}
