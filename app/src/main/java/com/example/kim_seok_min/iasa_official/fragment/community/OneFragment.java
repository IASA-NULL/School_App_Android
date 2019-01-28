package com.example.kim_seok_min.iasa_official.fragment.community;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kim_seok_min.iasa_official.R;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends Fragment {

    ListView listView;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_one, container, false);
        listView = (ListView) view.findViewById(R.id.listview);

        List<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        list.add("노래방 사용시간 늘려주세요");
        list.add("와이파이가 느립니다.");
        list.add("학교 3d 프린터가 부족합니다.");
        list.add("기숙사에 비데 설치해주세요.");
        list.add("1-1반 와이파이 설치해주세요.");
        list.add("축구공 더 사주세요.");
        list.add("서버 설치해주세요.");
        list.add("운동장 잔디 관리가 시급합니다.");
        list.add("체육관 천장 보수 필요합니다.");
        list.add("기숙사 정수기 필터 교체 해주세요.");
        list.add("천체 관측실 개방 신청해주세요.");

        return view;
    }

}
