package com.adidimasrizal.mychatapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static String BASE_URL="http://192.168.43.129:5000/v1/";
    private static Retrofit retrofit;
    public static ApiEndPoint endPoint(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndPoint.class);
    }
}
