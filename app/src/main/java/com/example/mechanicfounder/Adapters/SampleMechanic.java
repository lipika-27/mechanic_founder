package com.example.mechanicfounder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mechanicfounder.MechanicDetailsActivity;
import com.example.mechanicfounder.Model.User;
import com.example.mechanicfounder.R;

import java.util.ArrayList;

public class SampleMechanic extends RecyclerView.Adapter<SampleMechanic.ViewHolder> {

    ArrayList<User> list;
    Context context;

    public SampleMechanic(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_mechanic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User users = list.get(position);
        holder.MBname.setText(users.getBName());
        holder.Mname.setText(users.getUserName());
   //     holder.Maddress.setText(users.getAddress());
        holder.Mstate.setText(users.getState());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MechanicDetailsActivity.class);
                 intent.putExtra("mechId",users.getMechId());
                 intent.putExtra("BNmae",users.getBName());
                 intent.putExtra("userName",users.getUserName());
               //  intent.putExtra("address",users.getAddress());
                 intent.putExtra("state",users.getState());

                 context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView MBname,Mname,Maddress,Mstate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MBname = itemView.findViewById(R.id.MBname);
            Mname = itemView.findViewById(R.id.Mname);
          //  Maddress = itemView.findViewById(R.id.Maddress);
            Mstate = itemView.findViewById(R.id.Mstate);
        }
    }
}
