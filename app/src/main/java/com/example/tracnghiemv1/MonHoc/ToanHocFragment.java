package com.example.tracnghiemv1.MonHoc;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.tracnghiemv1.MainActivity;
import com.example.tracnghiemv1.R;
import com.example.tracnghiemv1.Slide.ScreenSlide;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToanHocFragment extends Fragment {
    ExamAdapter examAdapter;
    GridView gvExam;
    ArrayList<Exam> examArrayList = new ArrayList<Exam>();

    public ToanHocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Toán Học");
        return inflater.inflate(R.layout.fragment_toan_hoc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Code
        gvExam = (GridView) getActivity().findViewById(R.id.gvExam);
        examArrayList.add(new Exam("Đề Số 1"));
        examArrayList.add(new Exam("Đề Số 2"));
        examArrayList.add(new Exam("Đề Số 3"));
        examArrayList.add(new Exam("Đề Số 4"));
        examArrayList.add(new Exam("Đề Số 5"));
        examArrayList.add(new Exam("Đề Số 6"));

        examAdapter = new ExamAdapter(getActivity(),examArrayList);
        gvExam.setAdapter(examAdapter);
        gvExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),ScreenSlide.class);
                intent.putExtra("num_exam",i+1);
                intent.putExtra("subject","toan");
                startActivity(intent);
            }
        });
    }
}
