package com.adidimasrizal.mychatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    public static final int MSG_TYPE_RECEIVER=0;
    public static final int MSG_TYPE_SENDER=1;
    private String sender_token;

    private List<Model.Datachats> listchats;

    public ChatAdapter(String sender_token, List<Model.Datachats> listchats) {
        this.sender_token = sender_token;
        this.listchats = listchats;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType==MSG_TYPE_SENDER){
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_sender, viewGroup, false);
            return new ChatViewHolder(view);
        }else{
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_receiver, viewGroup, false);
            return new ChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        Model.Datachats datachats=listchats.get(position);
        holder.tvShowChat.setText(datachats.getMessage());
        holder.tvShowTime.setText(datachats.getTime());

    }

    @Override
    public int getItemCount() {
        return listchats.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvShowChat, tvShowTime;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            tvShowChat=itemView.findViewById(R.id.show_message);
            tvShowTime=itemView.findViewById(R.id.show_time);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String sender=sender_token;
        if(listchats.get(position).getToken_sender().equals(sender)){
            return MSG_TYPE_SENDER;
        }else{
            return MSG_TYPE_RECEIVER;
        }
    }
}
