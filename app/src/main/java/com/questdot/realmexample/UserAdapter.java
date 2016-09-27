package com.questdot.realmexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    Context context;

    public class UserViewHolder extends RecyclerView.ViewHolder {


        TextView firstName;
        TextView lastName;
        TextView country;



        public UserViewHolder(View view) {
            super(view);
            firstName = (TextView) view.findViewById(R.id.first_name);
            lastName = (TextView)view.findViewById(R.id.last_name);
            country = (TextView)view.findViewById(R.id.country);

        }
    }

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);


        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        final User user = userList.get(position);
        holder.firstName.setText(user.getFirst_name());
        holder.country.setText(user.getCountry());
        holder.lastName.setText(user.getLast_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("user_id", user.getUser_id());
                intent.putExtra("position", position);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}