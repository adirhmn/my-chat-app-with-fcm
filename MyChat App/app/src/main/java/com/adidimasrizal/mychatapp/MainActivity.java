package com.adidimasrizal.mychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adidimasrizal.mychatapp.retrofit.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvHistory;
    private ImageView btnContact;
    public String sender_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String mytoken=getIntent().getStringExtra("mytoken");
        sender_name=getIntent().getStringExtra("sender_name");

        btnContact=findViewById(R.id.main_contact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Contact.class);
                intent.putExtra("mytoken", mytoken);
                intent.putExtra("sender_name", sender_name);
                startActivity(intent);
            }
        });

        ApiService.endPoint().history(mytoken)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        List<Model.History> historyList=response.body().getHistory();

                        rvHistory=findViewById(R.id.rv_history);
                        rvHistory.setHasFixedSize(true);
                        rvHistory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        HistoryAdapter historyAdapter=new HistoryAdapter(historyList, mytoken);
                        rvHistory.setAdapter(historyAdapter);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("FAILURE", t.toString());

                    }
                });

    }




    @Override
    protected void onResume() {
        super.onResume();
        String mytoken=getIntent().getStringExtra("mytoken");
        ApiService.endPoint().history(mytoken)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        List<Model.History> historyList=response.body().getHistory();

                        rvHistory=findViewById(R.id.rv_history);
                        rvHistory.setHasFixedSize(true);
                        rvHistory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        HistoryAdapter historyAdapter=new HistoryAdapter(historyList, mytoken);
                        rvHistory.setAdapter(historyAdapter);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {

                    }
                });

    }


}