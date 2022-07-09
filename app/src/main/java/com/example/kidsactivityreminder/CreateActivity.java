package com.example.kidsactivityreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
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

    Button timebtn,creatact;
    int hour, minute;

    EditText date_ed;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        timebtn=findViewById(R.id.timebtn);
        creatact=findViewById(R.id.createact);
        date_ed=findViewById(R.id.date_ed);

        autoCompleteTextView=findViewById(R.id.auto_complete);
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_spinner,titles);
        autoCompleteTextView.setAdapter(adapterItems);
        DB=new DBHelper(this);


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
                date_ed.setText(sdf.format(calendar.getTime()));
            }
        };

        date_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(CreateActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        creatact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c= Calendar.getInstance();
                int day,month,year,hour,min;

                String Activity = autoCompleteTextView.getText().toString();
                String Time =timebtn.getText().toString();
                String Date=date_ed.getText().toString();
                String Status ="pending";

                String splitTime[]=Time.split(":");
                hour=Integer.parseInt(splitTime[0]);
                min=Integer.parseInt(splitTime[1]);

                String splitDate[]=Date.split("/");
                month=Integer.parseInt(splitDate[0]);
                day=Integer.parseInt(splitDate[1]);
                year=Integer.parseInt(splitDate[2]);

                int DY=(c.get(Calendar.DAY_OF_MONTH));
                int MN=(c.get(Calendar.MONTH)+1);
                int YR=(c.get(Calendar.YEAR));


                 if(Activity.equals("")){
                     Toast.makeText(CreateActivity.this, "Activity field is empty!", Toast.LENGTH_SHORT).show();
                 }else {
                     if( day == DY&& month == MN&& year == YR){
                         if(hour >= c.get(Calendar.HOUR_OF_DAY)){
                             if(hour == c.get(Calendar.HOUR_OF_DAY)){
                                 Boolean minute =(min>c.get(Calendar.MINUTE));
                                 if(minute==true){
                                     Boolean insert =DB.insertActivity(Activity,Time,Date,Status);
                                     if (insert==true){
                                         Toast.makeText(CreateActivity.this, "Activity created", Toast.LENGTH_SHORT).show();
                                         autoCompleteTextView.setText("");
                                         timebtn.setText("Choose Time");
                                         date_ed.setText("Choose Date");
                                         AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                         final int id = (int) System.currentTimeMillis();
                                         Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
                                         intent.putExtra("activity",Activity);
                                         intent.putExtra("time",Date);
                                         intent.putExtra("date",Time);
                                         PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, PendingIntent.FLAG_ONE_SHOT);


                                         String timedate = Date +" "+Time;
                                         DateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:mm");
                                         try {
                                             java.util.Date dt = formatter.parse(timedate);
                                             am.set(AlarmManager.RTC_WAKEUP, dt.getTime(), pendingIntent);
                                         } catch (ParseException e) {
                                             e.printStackTrace();
                                         }

                                     }else{
                                         Toast.makeText(CreateActivity.this, "Failed to create activity", Toast.LENGTH_SHORT).show();
                                     }
                                 }else{
                                     Toast.makeText(CreateActivity.this, "Passed Time", Toast.LENGTH_SHORT).show();
                                 }

                             }else{
                                 Boolean insert =DB.insertActivity(Activity,Time,Date,Status);
                                 if (insert==true){
                                     Toast.makeText(CreateActivity.this, "Activity created", Toast.LENGTH_SHORT).show();
                                     autoCompleteTextView.setText("");
                                     timebtn.setText("Choose Time");
                                     date_ed.setText("Choose Date");

                                     AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                     final int id = (int) System.currentTimeMillis();
                                     Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
                                     intent.putExtra("activity",Activity);
                                     intent.putExtra("time",Date);
                                     intent.putExtra("date",Time);
                                     PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, PendingIntent.FLAG_ONE_SHOT);


                                     String timedate = Date +" "+Time;
                                     DateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:mm");
                                     try {
                                         java.util.Date dt = formatter.parse(timedate);
                                         am.set(AlarmManager.RTC_WAKEUP, dt.getTime(), pendingIntent);
                                     } catch (ParseException e) {
                                         e.printStackTrace();
                                     }

                                 }else{
                                     Toast.makeText(CreateActivity.this, "Failed to create activity", Toast.LENGTH_SHORT).show();
                                 }
                             }

                         }else{
                             Toast.makeText(CreateActivity.this, "Passed Time", Toast.LENGTH_SHORT).show();
                         }

                     }else{
                         Boolean insert =DB.insertActivity(Activity,Time,Date,Status);
                         if (insert==true){
                             Toast.makeText(CreateActivity.this, "Activity created", Toast.LENGTH_SHORT).show();
                             autoCompleteTextView.setText("");
                             timebtn.setText("Choose Time");
                             date_ed.setText("Choose Date");
                             AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                             final int id = (int) System.currentTimeMillis();
                             Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
                             intent.putExtra("activity",Activity);
                             intent.putExtra("time",Date);
                             intent.putExtra("date",Time);
                             PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, PendingIntent.FLAG_ONE_SHOT);


                             String timedate = Date +" "+Time;
                             DateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:mm");
                             try {
                                 java.util.Date dt = formatter.parse(timedate);
                                 am.set(AlarmManager.RTC_WAKEUP, dt.getTime(), pendingIntent);
                             } catch (ParseException e) {
                                 e.printStackTrace();
                             }


                         }else{
                             Toast.makeText(CreateActivity.this, "Failed to create activity", Toast.LENGTH_SHORT).show();
                         }


                     }


                }

            }
        });
    }

    public void popTimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selhour, int selminute) {
                hour=selhour;
                minute=selminute;
                timebtn.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));

            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(this,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }
}