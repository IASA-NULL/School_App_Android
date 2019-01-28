package com.example.kim_seok_min.iasa_official;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim_seok_min.iasa_official.fragment.community.Fragment_Community;
import com.example.kim_seok_min.iasa_official.fragment.library.Fragment_Library;
import com.example.kim_seok_min.iasa_official.fragment.home.Fragment_School;
import com.example.kim_seok_min.iasa_official.school.Download;
import com.example.kim_seok_min.iasa_official.school.DownloadSchedule;
import com.example.kim_seok_min.iasa_official.school.SchoolMenu;
import com.example.kim_seok_min.iasa_official.school.SchoolSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public ArrayList<String> array;
    private Toolbar toolbar;
    public String[] daymenu = null;
    public String[] weeklySchedule = null;
    public String[] timetable = null;
    public List<SchoolMenu> menus;
    public List<SchoolSchedule> schoolSchedules;
    private ViewPager viewPager;

    Fragment_School fragment_school;
    Fragment_Library fragment_library;
    Fragment_Community fragment_community;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Databasehelper databasehelper = new Databasehelper(getApplicationContext(), getFilesDir().getAbsolutePath());
        try{
            databasehelper.prepareDataBase();
        }catch (IOException io){
            throw new Error("Unable to create Database");
        }

        if(databasehelper.getResult()==true){
            Download download = new Download();
            DownloadSchedule downloadSchedule = new DownloadSchedule();
            try {
                menus = download.execute().get();
                schoolSchedules = downloadSchedule.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            databasehelper.setDatabase(menus, schoolSchedules);
        }
        daymenu = databasehelper.getMenu();
        weeklySchedule = databasehelper.getSchedule();
        timetable = databasehelper.getTimetable();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        toolbar.setTitle("School");
                        break;
                    case 1:
                        toolbar.setTitle("Library");
                        break;
                    case 2:
                        toolbar.setTitle("Community");
                        break;
                }

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        toolbar.setTitle("School");
        setupViewPager(viewPager);
    }
    /*
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_school:
                    toolbar.setTitle("School");
                    fragment = new Fragment_School();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_library:
                    toolbar.setTitle("Library");
                    fragment = new Fragment_Library();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_community:
                    toolbar.setTitle("Community");
                    fragment = new Fragment_Community();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.viewpager, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    */

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_school:
                    toolbar.setTitle("School");
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_library:
                    toolbar.setTitle("Library");
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_community:
                    toolbar.setTitle("Community");
                    viewPager.setCurrentItem(2);
            }
            return false;
        }
    };

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragment_school=new Fragment_School();
        fragment_library=new Fragment_Library();
        fragment_community=new Fragment_Community();
        adapter.addFragment(fragment_school);
        adapter.addFragment(fragment_library);
        adapter.addFragment(fragment_community);
        viewPager.setAdapter(adapter);
    }
}
