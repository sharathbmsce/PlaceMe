package com.example.placement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class admin_results extends AppCompatActivity {
RelativeLayout l;
EditText n;
Integer no;
private  int text1=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_results);
        l=(RelativeLayout) findViewById(R.id.relative);
        n=(EditText)findViewById(R.id.no);

    }

    public void add_edit(View view) {
        String no1=n.getText().toString();
        no= Integer.parseInt(no1);
        int top=500,left=230;
       for (int i=0;i<no;i++){
            EditText e = new EditText(this);
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
}
