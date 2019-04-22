package com.example.placement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class job_desc extends AppCompatActivity {
TextView date,desc,title,company;
String date1,title1,company1,desc1;
String us,key;
DatabaseReference mDatabase,pDatabase,sDatabase;
DatabaseReference aDatabase;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_desc);
        Intent intent=getIntent();
         key=intent.getStringExtra("JID");
        final String key1=key.substring(0,2);
        System.out.println(key1);
        date=(TextView)findViewById(R.id.jdate);
        desc=(TextView)findViewById(R.id.jdesc);
        title=(TextView)findViewById(R.id.jtitle);
        company=(TextView)findViewById(R.id.jcomp);
        aDatabase=FirebaseDatabase.getInstance().getReference().child("applications");
        mDatabase= FirebaseDatabase.getInstance().getReference().child("jobs").child(key);
        pDatabase= FirebaseDatabase.getInstance().getReference().child("company");
        user= FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null)
        {
           us=user.getEmail().replace(".",",");
        }
      System.out.println(us);
        sDatabase=FirebaseDatabase.getInstance().getReference().child("students").child(us);
        mDatabase.child("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                date1=dataSnapshot.getValue(String.class);
                date.setText(date1);
                date.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("description").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                desc1=dataSnapshot.getValue(String.class);
                desc.setText(desc1);
                desc.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                title1=dataSnapshot.getValue(String.class);
                title.setText(title1);
                title.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        pDatabase.child(key1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                company1=dataSnapshot.getValue(String.class);
                company.setText(company1);
                company.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void apply(View view) {
        sDatabase.child("usn").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             final String  stusn=dataSnapshot.getValue(String.class);
             sDatabase.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     String stuname=dataSnapshot.getValue(String.class);
                     aDatabase.child(key).child(stusn).child("name").setValue(stuname);
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
