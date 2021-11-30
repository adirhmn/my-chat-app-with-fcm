package com.adidimasrizal.mychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.adidimasrizal.mychatapp.retrofit.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    private int load_time=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    Log.w("FCM", "Fetching FCM registration token failed", task.getException());
                                    return;
                                }

                                String token=task.getResult();
                                ApiService.endPoint().checkuser(token)
                                        .enqueue(new Callback<Model>() {
                                            @Override
                                            public void onResponse(Call<Model> call, Response<Model> response) {
                                                Model.Checkuser user =response.body().getCheckuser();
                                                String status=user.getStatus();
                                                if (!status.equals("no")){
                                                    Intent intent =new Intent(SplashScreen.this, MainActivity.class);
                                                    intent.putExtra("mytoken", token);
                                                    intent.putExtra("sender_name", user.getName());
                                                    startActivity(intent);
                                                    finish();
                                                }else {
                                                    Intent intent =new Intent(SplashScreen.this, Register.class);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<Model> call, Throwable t) {
                                                Log.d("LogSplash", t.toString());
                                            }
                                        });
                            }
                        });

            }
        },load_time);
    }
}