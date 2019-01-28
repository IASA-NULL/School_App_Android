package com.example.kim_seok_min.iasa_official;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private ArrayList<String> arr  = new ArrayList<String>();

    public void setArr(ArrayList<String> arr) {
        this.arr = arr;
    }

    public ArrayList<String> getArr() {
        return arr;
    }
}
