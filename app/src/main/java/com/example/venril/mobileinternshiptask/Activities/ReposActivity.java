package com.example.venril.mobileinternshiptask.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.venril.mobileinternshiptask.ApiData.UserModel;
import com.example.venril.mobileinternshiptask.Events.UserInfoEvent;
import com.example.venril.mobileinternshiptask.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ReposActivity extends AppCompatActivity {
    private TextView usernameTextView;

    UserModel userModel;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        usernameTextView = findViewById(R.id.username_textview);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true)
    public void onUserInfoEvent(UserInfoEvent event) {
        userModel = event.userModel;
        usernameTextView.setText(userModel.getLogin());
    }
}
