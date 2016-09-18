package com.bk.fuliboom.Repository.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bk on 2016/9/17.
 */

public class AccountsDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "fuliboom.db";
    public static final String TABLE_YOUKU = "youku";
    public static final String TABLE_IQIYI = "iqiyi";
    private static final String STATEMENT_CREATE_YOUKU = "create table if not exists " +
            TABLE_YOUKU +
            " (id integer primary key ," +
            "account text, " +
            "psw text);";
    private static final String STATEMENT_CREATE_IQIYI = "create table if not exists " +
            TABLE_IQIYI +
            " (id integer primary key," +
            "account text, " +
            "psw text);";




    public AccountsDBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STATEMENT_CREATE_YOUKU);
        db.execSQL(STATEMENT_CREATE_IQIYI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_IQIYI);
        db.execSQL("drop table if exists " + TABLE_YOUKU);
        onCreate(db);

    }
}
