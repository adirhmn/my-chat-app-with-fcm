package com.adidimasrizal.mychatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.adidimasrizal.mychatapp.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contact extends AppCompatActivity {

    private ImageView btnBack;
    private RecyclerView rvContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnBack=findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String mytoken=getIntent().getStringExtra("mytoken");
        String sender_name=getIntent().getStringExtra("sender_name");



        ApiService.endPoint().getUsers(mytoken)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        List<Model.Users> usersList=response.body().getUsers();
                        rvContact=findViewById(R.id.rv_contact);
                        rvContact.setHasFixedSize(true);
                        rvContact.setLayoutManager(new LinearLayoutManager(Contact.this, LinearLayoutManager.VERTICAL, false));
                        UserAdapter userAdapter=new UserAdapter(usersList, mytoken, sender_name);
                        rvContact.setAdapter(userAdapter);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {

                    }
                });
    }
}