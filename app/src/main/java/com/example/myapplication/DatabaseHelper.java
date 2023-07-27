package com.example.myapplication;
import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.ArrayList;
import android.content.Context;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myapp.db";
    private static final String TABLE_NAME = "mytable";
    private static final String COLUMN_PRINCIPAL = "principal";
    private static final String COLUMN_RATE = "rate";
    private static final String COLUMN_MONTHS = "months";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_INTEREST = "interest";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_PRINCIPAL + " REAL," +
                    COLUMN_RATE + " REAL," +
                    COLUMN_MONTHS + " REAL," +
                    COLUMN_INTEREST + " REAL," +
                    COLUMN_DATE + " TEXT)";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade logic here
    }
    public void insertData(double principal,double rate,double months,double interest,String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRINCIPAL, principal);
        values.put(COLUMN_RATE, rate);
        values.put(COLUMN_MONTHS, months);
        values.put(COLUMN_INTEREST, interest);
        values.put(COLUMN_DATE, date);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void clearData() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
    @SuppressLint("Range")
    public ArrayList<DataModel> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataModel> dataList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM mytable", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    DataModel dataModel = new DataModel();
                    dataModel.setPrincipal(cursor.getDouble(cursor.getColumnIndex("principal")));
                    dataModel.setRate(cursor.getDouble(cursor.getColumnIndex("rate")));
                    dataModel.setMonths(cursor.getDouble(cursor.getColumnIndex("months")));
                    dataModel.setInterest(cursor.getDouble(cursor.getColumnIndex("interest")));
                    dataModel.setDate(cursor.getString(cursor.getColumnIndex("date")));
                    dataList.add(dataModel);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        db.close();
        return dataList;
    }
    @SuppressLint("Range")
    public ArrayList<DataModel1>getDataInterest() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = null;
        ArrayList<DataModel1> dataList1 = new ArrayList<>();
        String s = "";
        try {
            cursor1 = db.rawQuery("SELECT SUM(interest) AS TotalInterest, SUM(principal) AS TotalPrincipal FROM mytable", null);
            if (cursor1 != null && cursor1.moveToFirst()) {
                do {
                    DataModel1 dataModel1 = new DataModel1();
                    dataModel1.setTotalPrincipal(cursor1.getDouble(cursor1.getColumnIndex("TotalPrincipal")));
                    dataModel1.setTotalInterest(cursor1.getDouble(cursor1.getColumnIndex("TotalInterest")));
                    dataList1.add(dataModel1);
                } while (cursor1.moveToNext());
            }
        } finally {
            if (cursor1 != null) {
                cursor1.close();
            }
        }
        db.close();
     return dataList1;
    }
}

