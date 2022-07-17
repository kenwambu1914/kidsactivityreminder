package com.example.kidsactivityreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class NotificationMessage extends AppCompatActivity {
      Button done;
      TextView act,tim,dat;
      DBHelper MyDB;
      TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);

        done=findViewById(R.id.done);
        act=findViewById(R.id.textviewact);
        tim=findViewById(R.id.textviewtime);
        dat=findViewById(R.id.textviewdate);
        MyDB=new DBHelper(this);

        Bundle bundle =getIntent().getExtras();
        String actiVity=bundle.getString("activity");
        String tiMe=bundle.getString("time");
        String daTe=bundle.getString("date");

        act.setText(actiVity);
        tim.setText(tiMe);
        dat.setText(daTe);

        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);}
            }
        });

        textToSpeech.speak(act.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
        textToSpeech.speak(act.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status ="Done";
                String actiVity=act.getText().toString();
                String activityTime=tim.getText().toString();
                String activityDate=dat.getText().toString();

               MyDB.updateStatus(actiVity,activityTime,activityDate,status);
                Toast.makeText(NotificationMessage.this, "The activity status has changed to done", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),ViewActivities.class);
                startActivity(i);

            }
        });


    }
}