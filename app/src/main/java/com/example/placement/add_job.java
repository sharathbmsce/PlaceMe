package com.example.placement;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class add_job extends AppCompatActivity {
EditText id,des,date1,title;
private DatabaseReference mDatabase;
final Calendar myCalendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        id = (EditText) findViewById(R.id.jid);
        des = (EditText) findViewById(R.id.desc);
        date1 = (EditText) findViewById(R.id.date_ret);
        title=(EditText)findViewById(R.id.title);
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }
DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR,year);
        myCalendar.set(Calendar.MONTH,month);
        myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        UpdateLabel();
    }
};
    private void UpdateLabel() {
        String myFormat="MM/dd/yy";
        SimpleDateFormat sdf=new SimpleDateFormat(myFormat, Locale.US);
        date1.setText(sdf.format(myCalendar.getTime()));
    }

    public void post(View view) {
String jid=id.getText().toString();
String jdate=date1.getText().toString();
String jdes=des.getText().toString();
String jtitle=title.getText().toString();
mDatabase.child("jobs").child(jid).child("date").setValue(jdate);
mDatabase.child("jobs").child(jid).child("description").setValue(jdes).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        Toast.makeText(add_job.this,"Job Posted!",Toast.LENGTH_LONG).show();
        id.setText("");
        date1.setText("");
        des.setText("");
        title.setText("");
    }
});
mDatabase.child("jobs").child(jid).child("title").setValue(jtitle);
    }

    public void dateselec(View view) {
        new DatePickerDialog(add_job.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}
