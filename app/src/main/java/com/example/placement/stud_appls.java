package com.example.placement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
// **********************ATTENTION !!!!!!! GET USN FROM INTENT BECAUSE USN IS NULL HERE ****************
public class stud_appls extends AppCompatActivity {
    DatabaseReference mDatabase,pDatabase,uDatabase;
    FirebaseUser user;
    String usn,email;
    DatabaseReference ref;
    ArrayList<jobs> list;
    RecyclerView recyclerView;
    BottomNavigationView b;
    public static ArrayList<String> keys=new ArrayList<>();

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
        setContentView(R.layout.activity_stud_appls);
        recyclerView=findViewById(R.id.apps_recycler);
        uDatabase=FirebaseDatabase.getInstance().getReference().child("students");
        user= FirebaseAuth.getInstance().getCurrentUser();
        b=findViewById(R.id.navigationbar);
        b.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(user!=null)
        {
            email=user.getEmail().replace(".",",");
        }
        uDatabase.child(email).child("usn").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usn=dataSnapshot.getValue(String.class);
                System.out.println(usn);
                ref= FirebaseDatabase.getInstance().getReference().child("stud-apps").child(usn);
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
                            stud_adapter adapterClass=new stud_adapter(list,keys);
                            LinearLayoutManager manager=new LinearLayoutManager(stud_appls.this);
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
                            Toast.makeText(stud_appls.this,"Database Error",Toast.LENGTH_SHORT).show();
                        }
                    });
            }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }


    @Override
    protected void onStart() {
        super.onStart();


    }
}
