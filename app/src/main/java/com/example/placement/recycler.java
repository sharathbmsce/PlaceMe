package com.example.placement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recycler extends AppCompatActivity {

DatabaseReference ref;
ArrayList<jobs> list;
RecyclerView recyclerView;
public static ArrayList<String> keys=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ref= FirebaseDatabase.getInstance().getReference().child("jobs");
        recyclerView=findViewById(R.id.recycler);

// ****************************************************************************************************************
        if (ref!=null)
        {

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list=new ArrayList<>();
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {

                            list.add(ds.getValue(jobs.class));
                          keys.add(ds.getKey());
                        }
                        AdapterClass adapterClass=new AdapterClass(list,keys);
                        LinearLayoutManager manager=new LinearLayoutManager(recycler.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        adapterClass.notifyDataSetChanged();
                     //   recyclerView.setLayoutManager(manager);
                      //  recyclerView.setHasFixedSize(true);
                       recyclerView.setAdapter(adapterClass);
                        System.out.println(list.size());
                        System.out.println(keys);
                    }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(recycler.this,"Database Error",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
