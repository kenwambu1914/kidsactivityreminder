package com.example.kidsactivityreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Register extends AppCompatActivity {
    EditText regpassword,re_regpassword,username;
    MaterialButton createpass;
    TextView accounttext;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regpassword=findViewById(R.id.regpassword);
        createpass=findViewById(R.id.createpass);
        accounttext=findViewById(R.id.accounttext);
        username=findViewById(R.id.username);
        re_regpassword=findViewById(R.id.re_regpassword);
        DB=new DBHelper(this);

        createpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   String user=username.getText().toString();
                   String pass=regpassword.getText().toString();
                   String repass=re_regpassword.getText().toString();

                   if (user.equals("")||pass.equals("")||repass.equals(""))
                       Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                   else {
                       if(pass.equals(repass)){
                           Boolean checkuser =DB.checkUsername(user);
                           if(checkuser==false){
                               Boolean insert = DB.insertData(user,pass);
                               if (insert==true){
                                   Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                   Intent intent=new Intent(getApplicationContext(),ParentVerify.class);
                                   startActivity(intent);
                               }else {
                                   Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                               }
                           }else{
                               Toast.makeText(Register.this, "User already exists.Please go back to verification", Toast.LENGTH_SHORT).show();
                           }
                       }else{
                           Toast.makeText(Register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                       }
                   }
            }
        });

        accounttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Register.this,ParentVerify.class);
                startActivity(i);
            }
        });
    }
}