package com.example.placement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class stud_results extends AppCompatActivity {
    DatabaseReference mDatabase, pDatabase, uDatabase;
    FirebaseUser user;
    String usn, email;
    String k;
    ArrayList<String> jids = new ArrayList<>();
    ArrayList<String> jtitle = new ArrayList<>();
    TableLayout tb;
BottomNavigationView b;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navprofile:
                    Intent intent=new Intent(getApplicationContext(),stud_profile.class);
                    startActivity(intent);
                    return true;
                case R.id.navresults:
                    Intent intent1=new Intent(getApplicationContext(),stud_results.class);
                    startActivity(intent1);
                    return true;
                case R.id.navapps:
                    Intent intent2=new Intent(getApplicationContext(),stud_appls.class);
                    startActivity(intent2);
                    return true;
                case R.id.navhome:
                    Intent intent3=new Intent(getApplicationContext(),stud_home.class);
                    startActivity(intent3);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_results);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("results");
        tb = (TableLayout) findViewById(R.id.table);
        pDatabase = FirebaseDatabase.getInstance().getReference().child("students");
        uDatabase = FirebaseDatabase.getInstance().getReference().child("jobs");
        user = FirebaseAuth.getInstance().getCurrentUser();
        b=findViewById(R.id.navigationbar);
        b.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (user != null) {
            email = user.getEmail();
        }
        String email1 = email.replace(".", ",");
        pDatabase.child(email1).child("usn").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usn = dataSnapshot.getValue(String.class);
                mDatabase.child(usn).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            jids.add(d.getKey());
                        }
                        System.out.println("here you go" + jids);
                        for (int i = 0; i < jids.size(); i++) {
                            uDatabase.child(jids.get(i)).child("title").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    jtitle.add(dataSnapshot.getValue(String.class));
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void dis(View view) {
        System.out.println(jids.size());
        for (int i = 0; i < jids.size(); i++) {
            TableRow r = new TableRow(this);
            TextView v = new TextView(this);
            TextView q = new TextView(this);
            TextView s = new TextView(this);
            q.setText(jids.get(i));
            s.setText(jtitle.get(i));
            s.setTextSize(25);
            q.setTextSize(25);
            // v.setText(name.get(i));
            //v.setTextSize(25);
            // v.setTextColor(R.color.colorPrimary);
            // r.addView(v);
            r.addView(s);
            r.addView(q);
            tb.addView(r);

        }
    }
}


