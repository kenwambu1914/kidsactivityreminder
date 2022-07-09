package com.example.kidsactivityreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="activity.db";

    public DBHelper( Context context) {
        super(context,DBNAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key,password TEXT)");
        MyDB.execSQL("create Table activities(activity TEXT,time TEXT,date TEXT, status TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists activities");

    }
    public Boolean insertData(String username,String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result =MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else
            return true;

    }
    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username=?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkPass( String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where password =?",new String[] {password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertActivity(String activity,String time,String date,String status){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("activity",activity);
        contentValues.put("time",time);
        contentValues.put("date",date);
        contentValues.put("status",status);
        long result =MyDB.insert("activities",null,contentValues);
        if (result==-1) return false;
        else
            return true;

    }

public ArrayList<ActivityName> readActivities() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from activities" ,null);

        ArrayList<ActivityName> activityModalArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                activityModalArrayList.add(new ActivityName(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return activityModalArrayList;
}
    public void updateActivity(String Originalactivity,String activity,String time,String date,String status){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("activity",activity);
        contentValues.put("time",time);
        contentValues.put("date",date);
        contentValues.put("status",status);
        MyDB.update("activities",contentValues,"activity=?",new String[]{Originalactivity});
        MyDB.close();

    }
    public void delete(String activity){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.delete("activities","activity=?",new String[]{activity});
        MyDB.close();

    }

    }

