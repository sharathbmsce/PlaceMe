package com.example.placement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_selec extends AppCompatActivity {
FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_selec);
        //FirebaseApp.initializeApp(this);
      //  firebaseAuth=firebaseAuth.getInstance();
    //    mDatabase= FirebaseDatabase.getInstance().getReference();
    }

    public void add(View view) {
        Intent intent=new Intent(admin_selec.this,add_profile_comp.class);
        startActivity(intent);
    }

    public void results(View view) {
    Intent intent=new Intent(admin_selec.this,admin_results.class);
    startActivity(intent);


    }

    public void viewapps(View view) {
Intent intent=new Intent(admin_selec.this,admin_appls.class);
startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void add_job(View view) {
Intent intent=new Intent(admin_selec.this,add_job.class);
startActivity(intent);
    }
}
