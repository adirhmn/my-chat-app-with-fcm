package com.adidimasrizal.mychatapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<Model.History> historyList;
    private String mytoken;

    public HistoryAdapter(List<Model.History> historyList, String mytoken) {
        this.historyList = historyList;
        this.mytoken=mytoken;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup, false);
        return new HistoryAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        Model.History history = historyList.get(position);
        holder.name.setText(history.getName_receiver());
        holder.lastChat.setText(history.getMessage());
        holder.time.setText(history.getTime());
        holder.date.setText(history.getDate());
        holder.listChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(holder.listChat.getContext(), Chat.class);
                intent.putExtra("receiver_name", history.getName_receiver());
                intent.putExtra("receiver_token", history.getToken_receiver());
                intent.putExtra("sender_token", mytoken);
                intent.putExtra("sender_name",history.getName_sender());
                holder.listChat.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView name, lastChat, time, date;
        private LinearLayout listChat;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.his_contact);
            lastChat=itemView.findViewById(R.id.his_latschat);
            time=itemView.findViewById(R.id.his_time);
            date=itemView.findViewById(R.id.his_date);
            listChat=itemView.findViewById(R.id.his_chat);
        }
    }
}
