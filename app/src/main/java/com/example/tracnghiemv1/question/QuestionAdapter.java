package com.example.tracnghiemv1.question;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tracnghiemv1.R;

public class QuestionAdapter extends CursorAdapter {

    LinearLayout linQuestion;

    public QuestionAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_question,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtQuestion = (TextView) view.findViewById(R.id.txtQuestion);
        linQuestion = (LinearLayout) view.findViewById(R.id.linSearch);
        if (cursor.getPosition() % 2 ==0){
            linQuestion.setBackgroundColor(Color.parseColor("#AF9797"));
        }else{
            linQuestion.setBackgroundColor(Color.parseColor("#EEE1E1"));
        }
        txtQuestion.setText(cursor.getString(1));

    }
}