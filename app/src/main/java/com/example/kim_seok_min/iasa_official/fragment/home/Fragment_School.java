package com.example.kim_seok_min.iasa_official.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kim_seok_min.iasa_official.MainActivity;
import com.example.kim_seok_min.iasa_official.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_School extends Fragment {

    List<Information> informations = new ArrayList<>();
    List<Information> informations2 = new ArrayList<>();
    List<Information> informations3 = new ArrayList<>();
    Information[] information = new Information[6];
    Information[] information2 = new Information[7];
    Information[] information3 = new Information[9];
    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int date = calendar.get(Calendar.DATE);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__school, container, false);

        RecyclerView recyclerView01 = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView recyclerView02 = (RecyclerView) view.findViewById(R.id.recycler_view02);
        RecyclerView recyclerView03 = (RecyclerView) view.findViewById(R.id.recycler_view03);

        TextView textView01 = (TextView) view.findViewById(R.id.menu);
        textView01.setText("급식 메뉴");

        TextView textView02 = (TextView) view.findViewById(R.id.schedule);
        textView02.setText("학사일정");

        TextView textView03 = (TextView) view.findViewById(R.id.timetable);
        textView03.setText("시간표");

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int index = getindex(hour);
        int index02 = 0;
        int index03 = getindex2(hour);

        String[] temp = ((MainActivity)getActivity()).daymenu;
        information[0] = new Information(temp[0], "아침");
        information[1] = new Information(temp[1], "점심");
        information[2] = new Information(temp[2], "저녁");
        information[3] = new Information(temp[3], "아침");
        information[4] = new Information(temp[4], "점심");
        information[5] = new Information(temp[5], "저녁");

        String[] temp2 = ((MainActivity)getActivity()).weeklySchedule;
        for(int i=0; i<7; i++){
            int day = date+i;
            information2[i] = new Information(temp2[i], month+"월 "+day+"일"  );
        }

        String[] temp3 = ((MainActivity)getActivity()).timetable;
        Log.e("시간표", temp3[0]);
        for(int i=0; i<9; i++){
            information3[i] = new Information(temp3[i], (i+1)+"교시");
        }

        informations.clear();
        for(int i = 0; i<6; i++){
            informations.add(information[i]);
        }

        informations2.clear();
        for(int i=0; i<7; i++){
            informations2.add(information2[i]);
        }

        informations3.clear();
        for(int i=0; i<9; i++){
            informations3.add(information3[i]);
        }

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), informations, index);
        recyclerView01.setAdapter(recyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), HORIZONTAL, false);
        recyclerView01.setLayoutManager(layoutManager);
        recyclerView01.scrollToPosition(index);

        RecyclerAdapter recyclerAdapter02 = new RecyclerAdapter(getActivity(), informations2, index02);
        recyclerView02.setAdapter(recyclerAdapter02);
        RecyclerView.LayoutManager layoutManager02 = new LinearLayoutManager(getActivity(), HORIZONTAL, false);
        recyclerView02.setLayoutManager(layoutManager02);
        recyclerView02.scrollToPosition(index02);

        RecyclerAdapter2 recyclerAdapter03 = new RecyclerAdapter2(getActivity(), informations3, index03);
        recyclerView03.setAdapter(recyclerAdapter03);
        RecyclerView.LayoutManager layoutManager03 = new LinearLayoutManager(getActivity(), HORIZONTAL, false);
        recyclerView03.setLayoutManager(layoutManager03);
        recyclerView03.scrollToPosition(index03);

        return view;
    }

    public int getindex(int hour){
        if(hour<=9){
            return 0;
        }else if(hour>9 && hour<=14){
            return 1;
        }else{
            return 2;
        }
    }

    public int getindex2(int hour){
        if(hour<=9 && hour>=0){
            return 0;
        }else if(hour>9 && hour<=10){
            return 1;
        }else if(hour>10 && hour<=11){
            return 2;
        }else if(hour>11 && hour<=12){
            return 3;
        }else if(hour>12 && hour<=13){
            return 4;
        }else if(hour>13 && hour<=14){
            return 5;
        }else if(hour>14 && hour<=15){
            return 6;
        }else if(hour>15 && hour<=16){
            return 7;
        }else{
            return 8;
        }
    }
}
