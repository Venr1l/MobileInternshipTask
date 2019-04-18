package com.example.venril.mobileinternshiptask.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.venril.mobileinternshiptask.ApiData.RestClient;
import com.example.venril.mobileinternshiptask.ApiData.UserApiService;
import com.example.venril.mobileinternshiptask.ApiData.UserModel;
import com.example.venril.mobileinternshiptask.ApiData.UserRepoModel;
import com.example.venril.mobileinternshiptask.Events.UserInfoEvent;
import com.example.venril.mobileinternshiptask.Events.UserRepoDetailsEvent;
import com.example.venril.mobileinternshiptask.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposActivity extends AppCompatActivity {
    private static final String TAG = "ReposActivity";
    private TextView usernameTextView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReposAdapter adapter;

    private UserModel userModel;
    private List<UserRepoModel> userRepoModelList;

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
        recyclerView = findViewById(R.id.repos_recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
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

        UserApiService userApiService = RestClient.getInstance().create(UserApiService.class);
        Call<List<UserRepoModel>> call = userApiService.getUserReposInfo(userModel.getLogin());
        call.enqueue(new Callback<List<UserRepoModel>>() {

            @Override
            public void onResponse(Call<List<UserRepoModel>> call, Response<List<UserRepoModel>> response) {

                if(response.isSuccessful()) {
                    userRepoModelList = response.body();
                    adapter = new ReposAdapter(userRepoModelList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnRepoDetailsItemClickedListener(new ReposAdapter.RepoDetailsClickListener() {
                        @Override
                        public void onRepoItemClicked(int position, View view) {
                            EventBus.getDefault().postSticky(new UserRepoDetailsEvent(userRepoModelList.get(position)));
                            Intent intent = new Intent(getApplicationContext(), RepoDetailsActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.d(TAG, "onResponse: something went wrong");
                }
            }

            @Override
            public void onFailure(Call<List<UserRepoModel>> call, Throwable throwable) {

                Log.e(TAG, throwable.toString());

            }

        });

    }
}
