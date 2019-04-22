package com.example.placement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_appls extends AppCompatActivity {
DatabaseReference mDatabase,jDatabase,cDatabase;
EditText jid;
LinearLayout linear;
    private  int text1=10,i=0;
TextView title1,compname;
String jids,title,sjid;
ArrayList<String>usn=new ArrayList<>();
ArrayList<String>usn1;
ArrayList<String>name=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appls);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("applications");
        jid=(EditText)findViewById(R.id.jobid);
        jDatabase=FirebaseDatabase.getInstance().getReference();
        cDatabase=FirebaseDatabase.getInstance().getReference();
        jid=(EditText)findViewById(R.id.jobid);
        title1=(TextView)findViewById(R.id.jobtitle);
        compname=(TextView)findViewById(R.id.compname);
         linear=(LinearLayout)findViewById(R.id.linear);
    }

    public void search(View view) {

        jids = jid.getText().toString();
        sjid = jids.substring(0, 2);
        jDatabase.child("jobs").child(jids).child("title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tit = dataSnapshot.getValue(String.class);
                title1.setText(tit);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cDatabase.child("company").child(sjid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String comp = dataSnapshot.getValue(String.class);
                compname.setText(comp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child(jids).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    usn.add(d.getKey());
                    usn1 = usn;
                }
                System.out.println(usn.size());
                for ( i=0;i< usn.size();i++) {
                    mDatabase.child(jids).child(usn1.get(i)).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                      @ Override

                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           int top = 200, left = 230;
                            name.add(dataSnapshot.getValue(String.class));
                            EditText e=new EditText(admin_appls.this);
                            e.setId(text1);
                            e.setText(name.get(usn.size()-i)+"---"+ usn.get(usn.size()-i));
                          e.setFocusableInTouchMode(false);
                          e.setBackgroundColor(android.R.color.transparent);
                          e.setEms(11);
                          e.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                          LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                          params.setMargins(left, top, 0, 0);
                          e.setLayoutParams(params);
                          linear.addView(e);
                          /*  System.out.println(i);
                          System.out.println(usn.get(usn.size()-i));
                          System.out.println(name.get(usn.size()-i));*/
                          i--;

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*
        final int top = 300, left = 230;
        System.out.println(usn1.size());
        for ( i=0;i< usn1.size();i++)
        {
            System.out.println("i at begin"+ i);
            mDatabase.child(jids).child(usn1.get(i)).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    System.out.println("i inside"+ i);
                    name.add(dataSnapshot.getValue(String.class));
                    System.out.println(usn1.get(i-1));
                    System.out.println(name);
                    EditText e = new EditText(admin_appls.this);
                    e.setId(text1);
                    //e.setText(name.get(i-1)+"-----"+ usn1.get(i-1));
                    e.setFocusableInTouchMode(false);
                    e.setBackgroundColor(android.R.color.transparent);
                    e.setEms(11);
                    e.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                    params.setMargins(left, top, 0, 0);
                    e.setLayoutParams(params);
                    linear.addView(e);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }}
*/
    } }

