package com.example.kidsactivityreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ViewItemActivity extends AppCompatActivity {
    int hour, minute;
    Button timebtn1,saveact1,deleteact;
    Context context;
    EditText date_ed1;
    AutoCompleteTextView autocomplete1;
    String activity1,time1,date1,status1;
    DBHelper DB;
    ActivityName activityName;
    ArrayAdapter<String> adapterItems;
    String[] titles ={"WAKE UP",
            "BRUSH YOUR TEETH",
            "READ BOOKS",
            "LUNCH BREAK",
            "BUILD LEGOS",
            "ONLINE ART LESSONS",
            "INDOOR PICNIC",
            "DANCE PRACTICE",
            "PLAY BOARD GAMES",
            "SUPPER TIME",
            "SIMPLE CLEANING",
            "SLEEP TIME"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        timebtn1=findViewById(R.id.timebtn1);
        saveact1=findViewById(R.id.saveact1);
        deleteact=findViewById(R.id.deleteact);
        date_ed1=findViewById(R.id.date_ed1);
        autocomplete1=findViewById(R.id.auto_complete1);
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_spinner,titles);
        autocomplete1.setAdapter(adapterItems);
        DB=new DBHelper(ViewItemActivity.this);

        activity1=getIntent().getStringExtra("name");
        time1=getIntent().getStringExtra("time");
        date1=getIntent().getStringExtra("date");
        status1=getIntent().getStringExtra("status");

        autocomplete1.setText(activity1);
        timebtn1.setText(time1);
        date_ed1.setText(date1);

        saveact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activitY=autocomplete1.getText().toString();
                String timE=timebtn1.getText().toString();
                String datE=date_ed1.getText().toString();

                DB.updateActivity(activity1,activitY,timE,datE,status1);
                Toast.makeText(ViewItemActivity.this, "Details updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),ViewActivities.class);
                startActivity(intent);
            }
        });




        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                updateCalender();

            }
            private void  updateCalender(){
                String Format ="MM/dd/yy";
                SimpleDateFormat sdf =new SimpleDateFormat(Format,Locale.getDefault());
                date_ed1.setText(sdf.format(calendar.getTime()));
            }
        };
        date_ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ViewItemActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

     deleteact.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(getApplicationContext(),ParentVerify.class);
             intent.putExtra("activity",autocomplete1.getText().toString());
             startActivity(intent);

         }
     });
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selhour, int selminute) {
                hour=selhour;
                minute=selminute;
                timebtn1.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));

            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(this,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}