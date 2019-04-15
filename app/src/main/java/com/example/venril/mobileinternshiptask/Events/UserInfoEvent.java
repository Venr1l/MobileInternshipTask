package com.example.venril.mobileinternshiptask.Events;

import com.example.venril.mobileinternshiptask.ApiData.UserModel;

public class UserInfoEvent {

    public final UserModel userModel;

    public UserInfoEvent(UserModel userModel) {
        this.userModel = userModel;
    }
}
