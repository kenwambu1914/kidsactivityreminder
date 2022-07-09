package com.example.kidsactivityreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivities extends AppCompatActivity {
    Animation animation;
    ArrayList<ActivityName> activityNameArrayList;
    DBHelper dbHelper;
    RVAdapter rvAdapter;
    RecyclerView r_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activities);

        activityNameArrayList=new ArrayList<>();
        dbHelper=new DBHelper(ViewActivities.this);

        activityNameArrayList=dbHelper.readActivities();

        rvAdapter=new RVAdapter(activityNameArrayList,ViewActivities.this);
        r_view=findViewById(R.id.r_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewActivities.this, RecyclerView.VERTICAL, false);
        r_view.setLayoutManager(linearLayoutManager);
        animation= AnimationUtils.loadAnimation(this,R.anim.animation1);
        r_view.setAdapter(rvAdapter);


    }
}