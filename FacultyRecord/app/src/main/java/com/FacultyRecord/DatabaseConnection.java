package com.FacultyRecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "GROUP12";
    public static final String TABLE_NAME = "Faculty_record";
    public static final String one = "IDNUMBER";
    public static final String two = "NAME";
    public static final String three = "ADDRESS";
    public static final String four = "HIGHESTDEGREE";
   

    public DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (IDNUMBER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR,ADDRESS VARCHAR,HIGHESTDEGREE VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id,String name,String address,String degree) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(one,id);
        contentValues.put(two,name);
        contentValues.put(three,address);
        contentValues.put(four,degree);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String address,String degree) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(one,id);
        contentValues.put(two,name);
        contentValues.put(three,address);
        contentValues.put(four,degree);
   
       long result= db.update(TABLE_NAME, contentValues, "IDNUMBER = ?",new String[] { id });
		if(result > 0){
            return true;
        }else
            return false;
    
}
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "IDNUMBER = ?",new String[] {id});
    }
}
