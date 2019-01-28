package com.example.kim_seok_min.iasa_official.fragment.library;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.kim_seok_min.iasa_official.R;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Library extends Fragment {

    private int[] imageIDs = new int[] {
            R.drawable.gallery_image_01,
            R.drawable.gallery_image_02,
            R.drawable.gallery_image_03,
            R.drawable.gallery_image_04,
            R.drawable.gallery_image_05,
            R.drawable.gallery_image_06,
            R.drawable.gallery_image_07,
            R.drawable.gallery_image_08,
            R.drawable.gallery_image_09,
            R.drawable.gallery_image_10,
            R.drawable.gallery_image_11,
            R.drawable.gallery_image_12,
    };

    private int[] imageIDs2 = new int[]{
            R.drawable.gallery_image_04,
            R.drawable.gallery_image_05,
            R.drawable.gallery_image_06,
    };

    private int[] imageIDs3 = new int[]{
            R.drawable.gallery_image_07,
            R.drawable.gallery_image_08,
            R.drawable.gallery_image_09,
    };

    private int[] imageIDs4 = new int[]{
            R.drawable.gallery_image_10,
            R.drawable.gallery_image_11,
            R.drawable.gallery_image_12,
    };

    AutoScrollViewPager autoViewPager;
    GridView gridView;
    GridView gridView2;
    GridView gridView3;
    GridView gridView4;

    public Fragment_Library() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment__library, container, false);

        ArrayList<String> data = new ArrayList<>();
        data.add("http://iasa.icehs.kr/upload/sysImg/isaa/M_visual.jpg");
        data.add("https://t1.daumcdn.net/cfile/tistory/210C51375334FE4D13");
        data.add("https://post-phinf.pstatic.net/MjAxNzEwMjZfMTk2/MDAxNTA4OTk2MDE1ODU5.YUZ4ZUlTMKYySICFq_olz_4bVI8pQkUrOtvxVnNPNJUg.NePYrs5F8d_rmbyn1z1RujdLmJqryKoAWrPOD4AOrjEg.JPEG/1.jpg?type=w1200");

        autoViewPager = (AutoScrollViewPager) view.findViewById(R.id.autoviewpager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getActivity(), data);
        autoViewPager.setAdapter(scrollAdapter);
        autoViewPager.setInterval(5000);
        autoViewPager.startAutoScroll();

        TextView textView = (TextView) view.findViewById(R.id.library_text);
        textView.setText(" 도서목록");

        gridView = (GridView) view.findViewById(R.id.gridViewImages);
        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(getActivity(), imageIDs);
        gridView.setAdapter(imageGridAdapter);

        gridView2 = (GridView) view.findViewById(R.id.gridViewImages2);
        ImageGridAdapter imageGridAdapter2 = new ImageGridAdapter(getActivity(), imageIDs2);
        gridView2.setAdapter(imageGridAdapter2);

        gridView3 = (GridView) view.findViewById(R.id.gridViewImages3);
        ImageGridAdapter imageGridAdapter3 = new ImageGridAdapter(getActivity(), imageIDs3);
        gridView3.setAdapter(imageGridAdapter3);

        gridView4 = (GridView) view.findViewById(R.id.gridViewImages4);
        ImageGridAdapter imageGridAdapter4 = new ImageGridAdapter(getActivity(), imageIDs4);
        gridView4.setAdapter(imageGridAdapter4);

        return view;
    }

}
