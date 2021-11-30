package com.adidimasrizal.mychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adidimasrizal.mychatapp.firebase.MyFirebaseMessagingService;
import com.adidimasrizal.mychatapp.retrofit.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText regName;
    private Button regSign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        regName=findViewById(R.id.reg_name);
        regSign=findViewById(R.id.reg_btn_sign);

        regSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=regName.getText().toString();
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                String token = task.getResult();
                                ApiService.endPoint().register(String.valueOf(token), name)
                                        .enqueue(new Callback<Model>() {
                                            @Override
                                            public void onResponse(Call<Model> call, Response<Model> response) {
                                                Log.d("Register", response.toString());
                                            }

                                            @Override
                                            public void onFailure(Call<Model> call, Throwable t) {
                                                Log.d("Register", t.toString());
                                            }
                                        });

                                Toast.makeText(Register.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Register.this, MainActivity.class);
                                intent.putExtra("mytoken", token);
                                intent.putExtra("sender_name", name);
                                startActivity(intent);
                                finish();
                            }
                        });

            }
        });


    }
}