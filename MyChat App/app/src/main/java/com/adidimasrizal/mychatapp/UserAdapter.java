package com.adidimasrizal.mychatapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<Model.Users> usersList;
    private String mytoken, sender_name;

    public UserAdapter(List<Model.Users> usersList, String mytoken, String sender_name) {
        this.usersList = usersList;
        this.mytoken=mytoken;
        this.sender_name=sender_name;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
       Model.Users user=usersList.get(position);
       holder.contactName.setText(user.getName());
       holder.contactList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(holder.contactList.getContext(), Chat.class);
               intent.putExtra("receiver_token", user.getToken());
               intent.putExtra("receiver_name", user.getName());
               intent.putExtra("sender_token", mytoken);
               intent.putExtra("sender_name", sender_name);
               holder.contactList.getContext().startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout contactList;
        private TextView contactName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            contactList=itemView.findViewById(R.id.contact_list);
            contactName=itemView.findViewById(R.id.contact_name);
        }
    }
}
