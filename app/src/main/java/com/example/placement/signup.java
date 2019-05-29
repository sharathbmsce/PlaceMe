package com.example.placement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
EditText email,pass,pass1;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        pass1=(EditText)findViewById(R.id.pass1);
      FirebaseApp.initializeApp(this);
        firebaseAuth=FirebaseAuth.getInstance();

    }

    public void signup1(View view) {
        String emailID = email.getText().toString();
        String paswd = pass.getText().toString();
        if (emailID.isEmpty()) {
            email.setError("Provide your Email first!");
            email.requestFocus();
        } else if (paswd.isEmpty()) {
            pass.setError("Set your password");
            pass.requestFocus();
        } else if (emailID.isEmpty() && paswd.isEmpty()) {
            Toast.makeText(signup.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
        }
        else if (!(emailID.isEmpty() && paswd.isEmpty()) && pass.getText().toString().contentEquals(pass1.getText().toString())) {
            firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(signup.this.getApplicationContext(),
                                "SignUp unsuccessful: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(signup.this,"Sign Up Successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(signup.this, recycler.class));
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
