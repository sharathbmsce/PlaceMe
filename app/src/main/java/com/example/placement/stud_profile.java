package com.example.placement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.*;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class stud_profile extends AppCompatActivity {
EditText stuname,stuno,stuss,stupu,stucg,stuusn;
DatabaseReference mDatabase,rootRef,profRef;
FirebaseUser user;
    String st;
    FirebaseAuth firebaseAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navprofile:
                    Intent intent=new Intent(stud_profile.this,stud_profile.class);
                    startActivity(intent);
                    return true;
                case R.id.navresults:
                 //   mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navapps:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navhome:
                    Intent intent1=new Intent(stud_profile.this,stud_home.class);
                    startActivity(intent1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_profile);
        stuname=(EditText)findViewById(R.id.studname);
        stuno=(EditText)findViewById(R.id.studno);
        stuss=(EditText)findViewById(R.id.sslc);
        stupu=(EditText)findViewById(R.id.pu);
        stucg=(EditText)findViewById(R.id.cgpa);
        stuusn=(EditText)findViewById(R.id.usn);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            st=user.getEmail().replace(".",",");
        }
      rootRef=FirebaseDatabase.getInstance().getReference();
      profRef=rootRef.child("students").child(st);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        profRef.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                stuname.setText(value);
               stuname.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("usn").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1=dataSnapshot.getValue(String.class);
                stuusn.setText(value1);
              stuusn.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("phnno").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value2=dataSnapshot.getValue(String.class);
                stuno.setText(value2);
               stuno.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("sslc").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value3=dataSnapshot.getValue(String.class);
                stuss.setText(value3);
              stuss.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("puc").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value4=dataSnapshot.getValue(String.class);
                stupu.setText(value4);
               stupu.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("cgpa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value5=dataSnapshot.getValue(String.class);
                stucg.setText(value5);
                stucg.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void prof_up(View view) {
        String stname=stuname.getText().toString();
        String stusn=stuusn.getText().toString();
        String stno=stuno.getText().toString();
        String stssl=stuss.getText().toString();
        String stpu=stupu.getText().toString();
        String stcg=stucg.getText().toString();
        mDatabase.child("students").child(st).child("name").setValue(stname);
        mDatabase.child("students").child(st).child("usn").setValue(stusn);
        mDatabase.child("students").child(st).child("phnno").setValue(stno);
        mDatabase.child("students").child(st).child("sslc").setValue(stssl);
        mDatabase.child("students").child(st).child("puc").setValue(stpu);
        mDatabase.child("students").child(st).child("cgpa").setValue(stcg);
    }

    public void update(View view) {
       stucg.setFocusableInTouchMode(true);
        stuusn.setFocusableInTouchMode(true);
        stuname.setFocusableInTouchMode(true);
        stuno.setFocusableInTouchMode(true);
        stuss.setFocusableInTouchMode(true);
        stupu.setFocusableInTouchMode(true);
    }
}
