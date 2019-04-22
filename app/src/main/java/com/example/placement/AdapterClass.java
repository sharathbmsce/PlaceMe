package com.example.placement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>
{

ArrayList<jobs> list;
ArrayList<String> keys;
public AdapterClass(ArrayList<jobs> list,ArrayList<String> keys)
{
    this.list=list;
    this.keys=keys;
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(list.get(i).getTitle());
        myViewHolder.description.setText(list.get(i).getDescription());
        myViewHolder.date.setText(list.get(i).getDate());
        myViewHolder.refid.setText(keys.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
TextView title,description,date,refid;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            date=itemView.findViewById(R.id.date);
            refid=itemView.findViewById(R.id.refid);
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(v.getContext(),job_desc.class);
                  intent.putExtra("JID",refid.getText().toString());
                 v.getContext().startActivity(intent);
              }
          });

        }
    }
}
