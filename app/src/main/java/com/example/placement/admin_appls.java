package com.example.placement;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class admin_appls extends AppCompatActivity {
    DatabaseReference mDatabase, jDatabase, cDatabase;
    EditText jid;
    LinearLayout linear;
    private int text1 = 10, i = 0;
    TextView title1, compname;
    TableLayout tb;
    String jids, title, sjid;
    ArrayList<String> usn = new ArrayList<>();
    ArrayList<String> cgpa = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appls);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("applications");
        jid = (EditText) findViewById(R.id.jobid);
        jDatabase = FirebaseDatabase.getInstance().getReference();
        cDatabase = FirebaseDatabase.getInstance().getReference();
        jid = (EditText) findViewById(R.id.jobid);
        title1 = (TextView) findViewById(R.id.jobtitle);
        compname = (TextView) findViewById(R.id.compname);
        linear = (LinearLayout) findViewById(R.id.linear);
        tb = (TableLayout) findViewById(R.id.tb);


    }

    public void search(View view) throws InterruptedException {

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

                }
                System.out.println(usn);
                for (i = 0; i < usn.size(); i++) {
                    mDatabase.child(jids).child(usn.get(i)).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override

                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            name.add(dataSnapshot.getValue(String.class));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                    mDatabase.child(jids).child(usn.get(i)).child("cgpa").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            cgpa.add(dataSnapshot.getValue(String.class));
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

        System.out.println(name.size());


    }

    public void display(View view) {

        for (int i = 0; i < usn.size(); i++) {
            TableRow r = new TableRow(this);
            TextView v = new TextView(this);
            TextView q = new TextView(this);
            TextView s = new TextView(this);
            q.setText(cgpa.get(i));
            s.setText(usn.get(i));
            s.setTextSize(25);
            q.setTextSize(25);
            v.setText(name.get(i));
            v.setTextSize(25);
            v.setTextColor(R.color.colorPrimary);
            r.addView(v);
            r.addView(s);
            r.addView(q);
            tb.addView(r);


        }
    }
}

