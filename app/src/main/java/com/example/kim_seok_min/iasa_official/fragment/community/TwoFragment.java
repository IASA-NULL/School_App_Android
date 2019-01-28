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


public class TwoFragment extends Fragment {

    ListView listView;
    public TwoFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_two, container, false);
        listView = (ListView) view.findViewById(R.id.listview2);

        List<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        list.add("사진 콘테스트 오늘 마감입니다.");
        list.add("오페라, 뮤지컬 감상문 제출");
        list.add("1-3,4,5반 화학 실험 준비");
        list.add("수강 신청 시 자유학점 안내");
        list.add("뇌과학 나갈 학생 김은경 선생님으로");
        list.add("면학 1교시 수학 퀴즈 안내");
        list.add("오늘 미적분 수업 변경 안내");
        list.add("토요일 면학 1차시 물리 honors 프로그램");
        list.add("이번주 토요일 융복합 페스티벌");
        list.add("오늘 물리 방과후 시간 프린트 가져오기");
        list.add("수강신청 중복학생 최종근 선생님으로");
        list.add("검도 출석률 70% 이상 검도 대회 신청");
        list.add("1학년 국어2 수행평가 안내");
        list.add("면학 2차시 지구과학 수행평가");
        list.add("아침조회 1학년 교무실 앞에서");

        return view;
    }

}
