package com.example.placement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_profile_comp extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText name, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_comp);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        name = (EditText) findViewById(R.id.name);
        id = (EditText) findViewById(R.id.compid);

    }

    public void add_comp(View view) {
        String coname=name.getText().toString();
        String coid=id.getText().toString();
        mDatabase.child("company").child(coid).setValue(coname);


    }
}
