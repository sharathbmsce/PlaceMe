package com.example.placement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_results extends AppCompatActivity {
RelativeLayout l;
EditText n,jobid;
Integer no;
ArrayList<EditText> et=new ArrayList<>();
DatabaseReference mDatabase,jDatabase;
private  int text1=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_results);
        l=(RelativeLayout) findViewById(R.id.relative);
        n=(EditText)findViewById(R.id.no);
        jobid=(EditText)findViewById(R.id.jid);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("results");
        jDatabase=FirebaseDatabase.getInstance().getReference().child("jobs");
    }

    public void add_edit(View view) {
        String no1=n.getText().toString();

        no= Integer.parseInt(no1);
        int top=500,left=230;
       for (int i=0;i<no;i++){
            EditText e = new EditText(this);
            et.add(e);
            e.setId(text1);
            e.setText("");
            e.setEms(11);
            e.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) e.getLayoutParams();
            params.setMargins(left, top, 0, 0);
            e.setLayoutParams(params);
            l.addView(e);
            System.out.println(e.getId());
            top+=100;
            text1++;
        }
    }

    public void upload(View view) {
final String [] strings=new String[et.size()];
        String id=jobid.getText().toString();
        final String jobt=jobid.getText().toString();
for (int i=0;i<et.size();i++)
{
    strings[i]=et.get(i).getText().toString();

}
jDatabase.child(id).child("title").addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String  jtitle=dataSnapshot.getValue(String.class);
        for (int k=0;k<strings.length;k++)
        {
            System.out.println(jobt + jtitle);
            mDatabase.child(strings[k]).child(jobt).child("title").setValue(jtitle);
            System.out.println("im here");
        }
        Toast.makeText(admin_results.this,"Results Uploaded",Toast.LENGTH_LONG).show();;
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }

    public void remove(View view) {
        String id=jobid.getText().toString();
        jDatabase.child(id).removeValue();


    }
}
