package com.example.venril.mobileinternshiptask.Events;

import com.example.venril.mobileinternshiptask.ApiData.UserRepoModel;

public class UserRepoDetailsEvent {
    public UserRepoModel userRepoModel;

    public UserRepoDetailsEvent(UserRepoModel userRepoModel) {
        this.userRepoModel = userRepoModel;
    }
}
