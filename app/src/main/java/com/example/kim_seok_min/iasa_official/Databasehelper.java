package com.example.kim_seok_min.iasa_official;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kim_seok_min.iasa_official.school.SchoolMenu;
import com.example.kim_seok_min.iasa_official.school.SchoolSchedule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

public class Databasehelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "School.db";
    private static final int VERSION_NAME = 1;
    private final static String TAG = "DatabaseHelper";
    private String Path;
    private SQLiteDatabase sqLiteDatabase;
    private Context myContext;

    Calendar cal= Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int date = cal.get(Calendar.DATE);
    int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
    int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    List<SchoolMenu> menuList = null;

    public Databasehelper(Context context){
        super(context, DATABASE_NAME, null, VERSION_NAME);
    }

    public Databasehelper(Context context, String filePath){
        super(context, DATABASE_NAME, null, VERSION_NAME);
        this.myContext = context;
        Path = String.valueOf(filePath+"/"+DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion){

    }

    public void prepareDataBase() throws IOException{
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.d(TAG, "Database exists.");
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        } else {
            Log.d(TAG, "Database not exists.");
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File dbFile = new File(Path);
            checkDB = dbFile.exists();
        } catch (SQLiteException ignored) {
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream myOutput = new FileOutputStream(Path);
        InputStream myInput = myContext.getAssets().open("database/" + DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public Boolean getResult(){
        sqLiteDatabase = SQLiteDatabase.openDatabase(Path,
                null, SQLiteDatabase.OPEN_READONLY);
        String query = "select * from School_Menu where Year="+ year +" and Month="+ month + " and Date="+ date;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor == null){
            return false;
        }else{
            return true;
        }
    }

    public void setDatabase(List<SchoolMenu> schoolMenus, List<SchoolSchedule> schoolSchedules){
        sqLiteDatabase = SQLiteDatabase.openDatabase(Path,
                null, SQLiteDatabase.OPEN_READWRITE);
        int len = schoolMenus.size();
        for(int i=0; i<len; i++){
            String breakfast = schoolMenus.get(i).breakfast;
            String lunch = schoolMenus.get(i).lunch;
            String dinner = schoolMenus.get(i).dinner;
            String schedule = schoolSchedules.get(i).schedule;
            int j = i+1;
            String insert = "insert into School_Menu (Year,Month,Date,Breakfast,Lunch,Dinner,Schedule) values ("
                    +year+","+month+","+(j)+","+"'"+breakfast+"'"+","+"'"+lunch+"'"+","+"'"+dinner+"'"+","+"'"+schedule+"'"+")";
            sqLiteDatabase.execSQL(insert);
        }
    }

    public String[] getMenu(){
        sqLiteDatabase = SQLiteDatabase.openDatabase(Path,
                null, SQLiteDatabase.OPEN_READONLY);
        int tommorow = date+1;

        String out = "select * from School_Menu where Year="+year+" and Month="+month+" and Date="+date+" or Date="+tommorow;
        Cursor cursor = sqLiteDatabase.rawQuery(out, null);
        cursor.moveToFirst();
        String[] daymenu = new String[6];
        daymenu[0] = cursor.getString(cursor.getColumnIndex("Breakfast"));
        daymenu[1] = cursor.getString(cursor.getColumnIndex("Lunch"));
        daymenu[2] = cursor.getString(cursor.getColumnIndex("Dinner"));
        cursor.moveToNext();
        daymenu[3] = cursor.getString(cursor.getColumnIndex("Breakfast"));
        daymenu[4] = cursor.getString(cursor.getColumnIndex("Lunch"));
        daymenu[5] = cursor.getString(cursor.getColumnIndex("Dinner"));
        return daymenu;
    }

    public String[] getSchedule(){
        sqLiteDatabase = SQLiteDatabase.openDatabase(Path,
                null, SQLiteDatabase.OPEN_READONLY);
        String out = "select * from School_Menu where Year="+year+" and Month="+month+" and Date="+date;
        int day = date;
        int day2 = 0;
        for (int i=0; i<6; i++){
            if(day<lastday){
                day++;
                out = out + " or Date="+day;
            }else{
                day2++;
                out = out + " or Date="+day2;
            }
        }
        Log.e("날짜", out);
        Log.e("마지막날", Integer.toString(lastday));
        Cursor cursor = sqLiteDatabase.rawQuery(out, null);
        String[] weeklySchedule = new String[7];
        cursor.moveToFirst();
        weeklySchedule[0] = cursor.getString(cursor.getColumnIndex("Schedule"));
        for(int i=0; i<6; i++){
            cursor.moveToNext();
            weeklySchedule[i+1] = cursor.getString(cursor.getColumnIndex("Schedule"));
        }
        return weeklySchedule;
    }

    public String[] getTimetable(){
        sqLiteDatabase = SQLiteDatabase.openDatabase(Path,
                null, SQLiteDatabase.OPEN_READONLY);
        String out = "select * from Time_Table where DOW="+dayofweek;
        Cursor cursor = sqLiteDatabase.rawQuery(out, null);
        String[] Timetable = new String[9];
        cursor.moveToFirst();
        Timetable[0] = cursor.getString(cursor.getColumnIndex("1"));
        for(int i=1; i<9; i++){
            String column = Integer.toString(i+1);
            Timetable[i] = cursor.getString(cursor.getColumnIndex(column));
        }
        return Timetable;
    }
}
