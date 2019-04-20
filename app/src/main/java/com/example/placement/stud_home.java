package com.example.placement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class stud_home extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navprofile:
                    Intent intent=new Intent(stud_home.this,stud_profile.class);
                    startActivity(intent);
                    return true;
                case R.id.navresults:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navapps:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navhome:
                    Intent intent1=new Intent(stud_home.this,stud_home.class);
                    startActivity(intent1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void logout1(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
