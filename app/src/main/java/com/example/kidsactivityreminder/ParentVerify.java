package com.example.kidsactivityreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ParentVerify extends AppCompatActivity {
    EditText password;
    MaterialButton lastdelete;
    TextView forgot,account;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_verify);
        password=findViewById(R.id.password);
        forgot=findViewById(R.id.forgotpass);
        account=findViewById(R.id.account);
        lastdelete=findViewById(R.id.verifydeletebtn);
        DB=new DBHelper(this);

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ParentVerify.this,Register.class);
                startActivity(i);
            }
        });

        lastdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String pass=password.getText().toString();

                    if(pass.equals("")){
                        Toast.makeText(ParentVerify.this, "Please fill the password!", Toast.LENGTH_SHORT).show();
                    }else{
                        Boolean checkpass=DB.checkPass(pass);
                        if (checkpass==true){
                            Toast.makeText(ParentVerify.this, "Password Verified", Toast.LENGTH_SHORT).show();
                            String Activityname=getIntent().getStringExtra("activity");
                            DB.delete(Activityname);
                            Toast.makeText(ParentVerify.this, "Activity Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),ViewActivities.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(ParentVerify.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }
}