package com.example.tracnghiemv1.Slide;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracnghiemv1.R;
import com.example.tracnghiemv1.question.Question;
import com.example.tracnghiemv1.question.QuestionController;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkanswer";
    private int mPageNumber;//vị trí hiện tại
    private int checkAns;//checkans

    ArrayList<Question> arr_quest;

    TextView txtNum,txtQuestion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;



    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        txtNum = (TextView) rootView.findViewById(R.id.tvNum);
        txtQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);

        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtNum.setText("Câu "+(mPageNumber+1));

        txtQuestion.setText(getItem( mPageNumber).getQuestion());
        radA.setText(getItem( mPageNumber).getAns_a());
        radB.setText(getItem( mPageNumber).getAns_b());
        radC.setText(getItem( mPageNumber).getAns_c());
        radD.setText(getItem( mPageNumber).getAns_d());
        if (checkAns != 0){
            radA    .setClickable(false);
            radB    .setClickable(false);
            radC    .setClickable(false);
            radD    .setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                getItem( mPageNumber).choiceID = i;
                getItem( mPageNumber).setAnsert(GetChoiceFromID(i));

                //Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public Question getItem(int position){
        return arr_quest.get(position);
    }


    private String GetChoiceFromID(int ID){
        switch (ID){
            case R.id.radA:
                return "a";

            case R.id.radB:
                return "b";

            case R.id.radC:
                return "c";

            case R.id.radD:
                return "d";
        }
        return "";
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Xử lí Database

        arr_quest = new ArrayList<Question>();
        ScreenSlide screenSlide = (ScreenSlide) getActivity();
        arr_quest = screenSlide.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns = getArguments().getInt(ARG_CHECKANSWER);
    }




    public static ScreenSlidePageFragment create(int pageNumber,int checkAnswer){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,pageNumber);
        args.putInt(ARG_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }


    //kiểm tra đúng thì đổi màu
    private void getCheckAns(String ans){
        if (ans.equals("a")){
            radA.setBackgroundColor(Color.RED);
        }
        else if (ans.equals("b")){
            radB.setBackgroundColor(Color.RED);
        }
        else if (ans.equals("c")){
            radC.setBackgroundColor(Color.RED);
        }
        else if (ans.equals("d")){
            radD.setBackgroundColor(Color.RED);
        }
        else;
    }

}
