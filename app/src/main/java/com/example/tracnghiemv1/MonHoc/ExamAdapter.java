package com.example.tracnghiemv1.MonHoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tracnghiemv1.R;

import java.util.ArrayList;
import java.util.List;

public class ExamAdapter extends ArrayAdapter<Exam> {
    public ExamAdapter(Context context, ArrayList<Exam> exam) {
        super(context, 0, exam);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_gridview,parent,false);

        }
        TextView txtExam = (TextView)  convertView.findViewById(R.id.txtExam);
        ImageView imgExam = (ImageView) convertView.findViewById(R.id.imgIcon);

        Exam exam = getItem(position);
        if (exam != null){
            txtExam.setText(""+exam.getName());
            imgExam.setImageResource(R.drawable.subject);

        }
        return convertView;
    }
}
