package com.sujan.herosapi.api;

import com.sujan.herosapi.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface HerosApi {

    //Create user
    @POST("users/signup")
    Call<Void>registerUser(@Body User user);

    //login user
    @POST("users/login")
    Call<Void>login(@Body User user);

}