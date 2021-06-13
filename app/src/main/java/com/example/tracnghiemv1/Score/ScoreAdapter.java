package com.example.tracnghiemv1.Score;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.tracnghiemv1.R;

public class ScoreAdapter extends CursorAdapter {
    public ScoreAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_list_item,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
       TextView txtNameStudent = (TextView) view.findViewById(R.id.txtNameStudent);
       TextView txtDiem = (TextView) view.findViewById(R.id.txtDiem);

       txtNameStudent.setText(cursor.getString(1));
       txtDiem.setText(cursor.getInt(2)+"");
    }
}
