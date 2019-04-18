package com.example.venril.mobileinternshiptask.ApiData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApiService {

    @GET("users/{user}")
    Call<UserModel> getUserInfo(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<UserRepoModel>> getUserReposInfo(@Path("user") String user);
}
