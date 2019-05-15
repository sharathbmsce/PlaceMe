package com.example.placement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.*;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class stud_profile extends AppCompatActivity {
EditText stuname,stuno,stuss,stupu,stucg,stuusn;
DatabaseReference mDatabase,rootRef,profRef;
ImageView view1;
FirebaseUser user;
    String st;
    FirebaseAuth firebaseAuth;
private Uri filepath;
private final int PICK_IMAGE_REQUEST=71;
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
                    intent2.putExtra("USN",stuusn.getText().toString());
                    startActivity(intent2);
                    return true;
                case R.id.navhome:
                    Intent intent3=new Intent(getApplicationContext(),recycler.class);
                    startActivity(intent3);
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
        view1=(ImageView)findViewById(R.id.view);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            st=user.getEmail().replace(".",",");
        }
      rootRef=FirebaseDatabase.getInstance().getReference();
      profRef=rootRef.child("students").child(st);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationbar);
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

        stucg.setFocusableInTouchMode(false);
        stuusn.setFocusableInTouchMode(false);
        stuname.setFocusableInTouchMode(false);
        stuno.setFocusableInTouchMode(false);
        stuss.setFocusableInTouchMode(false);
        stupu.setFocusableInTouchMode(false);

    }

    public void update(View view) {
       stucg.setFocusableInTouchMode(true);
        stuusn.setFocusableInTouchMode(true);
        stuname.setFocusableInTouchMode(true);
        stuno.setFocusableInTouchMode(true);
        stuss.setFocusableInTouchMode(true);
        stupu.setFocusableInTouchMode(true);
    }

    public void select_image(View view) {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                view1.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
