 package com.adidimasrizal.mychatapp;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adidimasrizal.mychatapp.retrofit.ApiService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


 public class Chat extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvReceiver;
    private RecyclerView rvChats;
    private EditText edtChat;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btnBack=findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvReceiver=findViewById(R.id.chat_receiver);
        edtChat=findViewById(R.id.chat_edit_text);
        btnSend=findViewById(R.id.chat_btn_send);
        edtChat=findViewById(R.id.chat_edit_text);

        String sender_token=getIntent().getStringExtra("sender_token");
        String receiver_token=getIntent().getStringExtra("receiver_token");
        String receiver_name=getIntent().getStringExtra("receiver_name");
        String sender_name=getIntent().getStringExtra("sender_name");
        tvReceiver.setText(receiver_name);


        showChats();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=edtChat.getText().toString();
                if (message.isEmpty()){
                    Toast.makeText(Chat.this, "Silakan tulis sesuatu", Toast.LENGTH_SHORT).show();
                }
                ApiService.endPoint().sendChat(sender_token, sender_name, receiver_token, receiver_name,message)
                        .enqueue(new Callback<Model>() {
                            @Override
                            public void onResponse(Call<Model> call, Response<Model> response) {
                                Model model=response.body();
                                List<Model.Datachats> listchats=model.getDatatchats();

                                rvChats=findViewById(R.id.rv_chat);
                                rvChats.setHasFixedSize(true);
                                LinearLayoutManager layoutManager=new LinearLayoutManager(Chat.this, LinearLayoutManager.VERTICAL, false);
                                layoutManager.setStackFromEnd(true);
                                rvChats.setLayoutManager(layoutManager);
                                ChatAdapter chatAdapter=new ChatAdapter(sender_token, listchats);
                                rvChats.setAdapter(chatAdapter);
                            }

                            @Override
                            public void onFailure(Call<Model> call, Throwable t) {
                                Log.d("SENDCHAT", t.toString());
                            }
                        });
                edtChat.setText("");
            }
        });


    }
     public BroadcastReceiver myReceiver = new BroadcastReceiver() {
         @Override
         public void onReceive(Context context, Intent intent) {
             String action=intent.getStringExtra("message_body");
             showChats();
         }
     };

     private void showChats() {
         String sender_token=getIntent().getStringExtra("sender_token");
         String receiver_token=getIntent().getStringExtra("receiver_token");
         ApiService.endPoint().getDataChats(sender_token,receiver_token)
                 .enqueue(new Callback<Model>() {
                     @Override
                     public void onResponse(Call<Model> call, Response<Model> response) {
                         Model model=response.body();
                         List<Model.Datachats> listchats=model.getDatatchats();

                         rvChats=findViewById(R.id.rv_chat);
                         rvChats.setHasFixedSize(true);
                         LinearLayoutManager layoutManager=new LinearLayoutManager(Chat.this, LinearLayoutManager.VERTICAL, false);
                         layoutManager.setStackFromEnd(true);
                         rvChats.setLayoutManager(layoutManager);
                         ChatAdapter chatAdapter=new ChatAdapter(sender_token,listchats);
                         rvChats.setAdapter(chatAdapter);


                     }

                     @Override
                     public void onFailure(Call<Model> call, Throwable t) {
                         Log.d("TAG", t.toString());
                     }
                 });
     }

     @Override
     protected void onResume() {
         super.onResume();
         registerReceiver(myReceiver, new IntentFilter("MessageFromFCM"));
     }

     @Override
     protected void onPause() {
         super.onPause();
         unregisterReceiver(myReceiver);
//        registerReceiver(myReceiver, new IntentFilter("MessageFromFCM"));
     }
 }