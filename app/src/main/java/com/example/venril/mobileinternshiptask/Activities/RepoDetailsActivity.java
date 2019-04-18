package com.example.venril.mobileinternshiptask.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.venril.mobileinternshiptask.ApiData.UserRepoModel;
import com.example.venril.mobileinternshiptask.Events.UserRepoDetailsEvent;
import com.example.venril.mobileinternshiptask.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class RepoDetailsActivity extends AppCompatActivity {
    private UserRepoModel userRepoModel;
    private TextView name;
    private TextView description;
    private TextView language;
    private TextView forks;
    private TextView watchers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);
        name = findViewById(R.id.details_name_value);
        description = findViewById(R.id.details_description_value);
        language = findViewById(R.id.details_language_value);
        forks = findViewById(R.id.details_forks_value);
        watchers = findViewById(R.id.details_watchers_value);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true)
    public void onUserInfoEvent(UserRepoDetailsEvent event) {
        userRepoModel = event.userRepoModel;
        name.setText(userRepoModel.getName());
        description.setText(userRepoModel.getDescription());
        language.setText(userRepoModel.getLanguage().toString());
        forks.setText(userRepoModel.getForksCount().toString());
        watchers.setText(userRepoModel.getWatchersCount().toString());
    }
}
