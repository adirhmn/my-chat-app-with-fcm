package com.adidimasrizal.mychatapp.retrofit;

import com.adidimasrizal.mychatapp.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("datachats")
    Call<Model> getDataChats(@Query("sender") String sender,
                             @Query("receiver") String receiver);

    @GET("sendchat")
    Call<Model> sendChat(@Query("token_sender") String token_sender,
                         @Query("name_sender") String name_sender,
                         @Query("token_receiver") String token_receiver,
                         @Query("name_receiver") String name_receiver,
                         @Query("message") String message);

    @GET("register")
    Call<Model> register(@Query("token") String token,
                         @Query("name") String name);

    @GET("checkuser")
    Call<Model> checkuser(@Query("token") String token);

    @GET("history")
    Call<Model> history(@Query("token") String token);

    @GET("users")
    Call<Model> getUsers(@Query("token") String token);
}
