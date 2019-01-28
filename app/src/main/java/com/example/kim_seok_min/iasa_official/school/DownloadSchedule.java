package com.example.kim_seok_min.iasa_official.school;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DownloadSchedule extends AsyncTask<String, String, List<SchoolSchedule>> {

    Calendar cal= Calendar.getInstance();

    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int date = cal.get(Calendar.DATE)-1;
    int dayofweek = cal.get(Calendar.DAY_OF_WEEK);

    String breakfast1 = null;
    String lunch1 = null;
    String dinner1 = null;

    ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected List<SchoolSchedule> doInBackground(String... strings) {
        School api = new School(School.Type.HIGH, School.Region.INCHEON, "E100002238");

        List<SchoolSchedule> schedule = null;

        {
            try {
                schedule = api.getMonthlySchedule(year, month);
            } catch (SchoolException e) {
                e.printStackTrace();
            }
        }
        return schedule;
    }

    @Override
    protected void onPostExecute(List<SchoolSchedule> avoid){
        super.onPostExecute(avoid);
    }
}
