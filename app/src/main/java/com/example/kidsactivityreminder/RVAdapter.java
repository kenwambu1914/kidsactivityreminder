package com.example.kidsactivityreminder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private ArrayList<ActivityName> activityModalArrayList;
    private Context context;

    public RVAdapter(ArrayList<ActivityName> activityModalArrayList, Context context) {
        this.activityModalArrayList = activityModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rviewcell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ActivityName activityName = activityModalArrayList.get(position);
        holder.textView.setText(activityName.getActivityName());
        holder.textdone.setText(activityName.getActivityStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,ViewItemActivity.class);

                i.putExtra("name",activityName.getActivityName());
                i.putExtra("time",activityName.getActivityTime());
                i.putExtra("date",activityName.getActivityDate());
                i.putExtra("status",activityName.getActivityStatus());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textdone;
        LinearLayout ll_bg;
        Animation animation;

        public ViewHolder(View itemView) {
            super(itemView);
            ll_bg=itemView.findViewById(R.id.ll_bg);
            textView=itemView.findViewById(R.id.textview);
            textdone=itemView.findViewById(R.id.textdone);
            animation= AnimationUtils.loadAnimation(context,R.anim.animation1);

            int number = getRandom(8);
            if (number == 1){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_1));
            }else if (number == 2){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_2));
            }else if (number == 3){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_3));
            }else if (number == 4){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_4));
            }else if (number == 5){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_5));
            }else if (number == 6){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_6));
            }else if (number == 7){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_7));
            }else if (number == 8){
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.gradient_8));
            }else{
                ll_bg.setBackground(ContextCompat.getDrawable(context,R.drawable.snow_bg));
            }
            textView.setAnimation(animation);
            textdone.setAnimation(animation);

        }
    }

    private int getRandom(int max) {
        return (int)(Math.random()*max);
    }
}
