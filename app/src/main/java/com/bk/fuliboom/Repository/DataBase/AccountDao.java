package com.bk.fuliboom.Repository.DataBase;

import android.database.Cursor;
import android.util.Log;

import com.bk.fuliboom.Repository.Beans.AccountInfo;
import com.bk.fuliboom.Utils.FuliApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bk on 2016/9/17.
 */

public class AccountDao {
    private AccountsDBHelper helper;
    private String tableName;
    public AccountDao(String name){
        Log.e("context" ,"" + (FuliApplication.getInstance() == null));
        helper = new AccountsDBHelper(FuliApplication.getInstance());
        tableName = name;
    }

    public void insertOne(AccountInfo accountInfo){
        if (accountInfo == null ||  accountInfo.getmAccount() == null || accountInfo.getmPassword()== null)  return;
        String[] params = new String[2];
        params[0] = accountInfo.getmAccount();
        params[1] = accountInfo.getmPassword();
        String insert = "insert into " +
                tableName +
                "  (account, psw) values(?, ?)";
        helper.getWritableDatabase().execSQL(insert,params);
    }

    public void deleteAll(){
        String delete = "delete * from "  + tableName;
        helper.getWritableDatabase().execSQL(delete);
    }

    public List<AccountInfo> getSection( int start, int count){
        List<AccountInfo> data = new ArrayList<>();
        String selectRecent = "select * from " + tableName  + "  order by id desc limit " + start + ", " + count;
        Cursor cursor = helper.getReadableDatabase().rawQuery(selectRecent,null);
        while (cursor.moveToNext()){
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setmAccount(cursor.getString(cursor.getColumnIndex("account")));
            accountInfo.setmPassword(cursor.getString(cursor.getColumnIndex("psw")));
            data.add(accountInfo);
        }
        cursor.close();
        return data;
    }

    public List<AccountInfo> getRecent(){
        List<AccountInfo> data = new ArrayList<>();
        String selectRecent = "select * from " + tableName + "  order by id desc limit 5";
        Log.e("aaaaaaaaa","" + (helper == null));
        Cursor cursor = helper.getWritableDatabase().rawQuery(selectRecent,null);
        while (cursor.moveToNext()){
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setmAccount(cursor.getString(cursor.getColumnIndex("account")));
            accountInfo.setmPassword(cursor.getString(cursor.getColumnIndex("psw")));
            data.add(accountInfo);
        }
        cursor.close();
        return data;
    }

    public List<AccountInfo> getAllAccounts(){
        List<AccountInfo> data = new ArrayList<>();
        String selectAll = "select * from " + tableName + "  order by id desc";
        Cursor cursor = helper.getReadableDatabase().rawQuery(selectAll,null);
        while (cursor.moveToNext()){
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setmAccount(cursor.getString(cursor.getColumnIndex("account")));
            accountInfo.setmPassword(cursor.getString(cursor.getColumnIndex("psw")));
            data.add(accountInfo);
        }
        cursor.close();
        return data;
    }

}
