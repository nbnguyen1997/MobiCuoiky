package com.example.tracnghiemv1.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;

public class QuestionController {
    private DBHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Question> getQuestion(int num_exam,String subject){
        ArrayList<Question> lsData = new ArrayList<Question>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //err
        Cursor cursor = (Cursor) db.rawQuery("SELECT * FROM toan WHERE num_exam= '"+num_exam+"' AND subject = '"+subject+"'",null);
        cursor.moveToFirst();//duyệt từ dòng đầu tiên
        do {
            Question item;
             item = new Question(cursor.getInt(0),
                     cursor.getString(1),
                     cursor.getString(2),
                     cursor.getString(3),
                     cursor.getString(4),
                     cursor.getString(5),
                     cursor.getString(6),
                     cursor.getString(7),
                     cursor.getInt(8),
                     cursor.getString(9),"");
            lsData.add(item);
        }while (cursor.moveToNext());
        return lsData;
    }

    public Cursor getSearchQuestion(String key){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //err
        Cursor cursor = (Cursor) db.rawQuery("SELECT * FROM toan WHERE question LIKE '%"+key+"%'",null);

        if (cursor != null)
            cursor.moveToFirst();//duyệt từ dòng đầu tiên
        return cursor;
    }
}
