package com.example.venril.mobileinternshiptask.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.venril.mobileinternshiptask.ApiData.RestClient;
import com.example.venril.mobileinternshiptask.ApiData.UserApiService;
import com.example.venril.mobileinternshiptask.ApiData.UserModel;
import com.example.venril.mobileinternshiptask.Events.UserInfoEvent;
import com.example.venril.mobileinternshiptask.R;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final String BASE_URL = "https://api.github.com/";
//    public static final String API_TOKEN = "f6d309d7c6ec380409a0598a2e677c2f84080708";
    private static Retrofit retrofit = null;

    private EditText usernameEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.nickname_edittext);
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserApiService userApiService = RestClient.getInstance().create(UserApiService.class);
                Call<UserModel> call = userApiService.getUserInfo(usernameEditText.getText().toString());
                call.enqueue(new Callback<UserModel>() {

                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                        if(response.isSuccessful()) {
                            UserModel userModel = response.body();
                            Log.d(TAG, "onResponse: found login: " + userModel.getLogin() + ", name: " + userModel.getName());
                            EventBus.getDefault().postSticky(new UserInfoEvent(userModel));
                            Intent intent = new Intent(getApplicationContext(), ReposActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d(TAG, "onResponse: something went wrong");
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "User not found.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable throwable) {

                        Log.e(TAG, throwable.toString());

                    }

                });
            }
        });
    }
}
