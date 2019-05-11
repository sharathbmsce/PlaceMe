package com.example.placement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
      EditText name,pass;
      private FirebaseAuth firebaseAuth;
      private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        FirebaseApp.initializeApp(MainActivity.this);
      firebaseAuth= FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    // ADD STUD_HOME IF USER IS AVAILABLE *********************************
                    Intent I = new Intent(MainActivity.this,stud_appls.class);
                    startActivity(I);
                } else {
                    Toast.makeText(MainActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    public void login(View view) {
        String email=name.getText().toString();
        String password=pass.getText().toString();
        if(email.isEmpty())
        {
            name.setError("Provide Email First !");
            name.requestFocus();
        }
       else if (password.isEmpty())
        {
            pass.setError("Enter Password !!");
            pass.requestFocus();
        }
        else if (email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(this,"Fields are empty !!",Toast.LENGTH_LONG).show();
        }
        else if(!(email.isEmpty() && password.isEmpty()))
        {
            if (email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin123"))
            {
                Intent intent=new Intent(MainActivity.this,admin_selec.class);
                startActivity(intent);
            }
         firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(!task.isSuccessful())
        {
           Toast.makeText(getApplicationContext(),"Not Successful",Toast.LENGTH_LONG).show();

        }
        else {
            // ADD STUD_HOME REFERENCE AFTER LOGIN *****************
            Intent intent=new Intent(MainActivity.this,stud_appls.class);
            startActivity(intent);
        }
    }
});

        }
        else {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void signup(View view) {
Intent intent=new Intent(MainActivity.this,signup.class);
startActivity(intent);
    }
}
