package com.example.tracnghiemv1.Score;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tracnghiemv1.question.DBHelper;
import com.example.tracnghiemv1.question.Question;

import java.util.ArrayList;

public class ScoreController {
    private DBHelper dbHelper;

    public ScoreController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void InsertScore(String name, int score , String room){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("score",score);
        contentValues.put("room",room);
        database.insert("tbscore",null,contentValues);
        database.close();
    }

    public Cursor getScore(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //err
        Cursor cursor = (Cursor) db.query("tbscore",null,null,null,null,null,"_id DESC",null);

        if (cursor != null)
            cursor.moveToFirst();//duyệt từ dòng đầu tiên
        return cursor;
    }

}
