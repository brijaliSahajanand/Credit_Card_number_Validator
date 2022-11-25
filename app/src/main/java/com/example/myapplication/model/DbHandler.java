package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "usersdb";
    private static final int DB_VERSION = 1;
    private static final String KEY_DESG = "designation";
    private static final String KEY_ID = "id";
    private static final String KEY_LOC = "location";
    private static final String KEY_NAME = "name";
    private static final String TABLE_Users = "userdetails";

    public DbHandler(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE userdetails(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,location TEXT,designation TEXT)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS userdetails");
        onCreate(sQLiteDatabase);
    }

    public void insertUserDetails(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put(KEY_LOC, str2);
        contentValues.put(KEY_DESG, str3);
        writableDatabase.insert(TABLE_Users, (String) null, contentValues);
        writableDatabase.close();
    }

    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT name, location, designation FROM userdetails", (String[]) null);
        while (rawQuery.moveToNext()) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", rawQuery.getString(rawQuery.getColumnIndex("name")));
            hashMap.put(KEY_DESG, rawQuery.getString(rawQuery.getColumnIndex(KEY_DESG)));
            hashMap.put(KEY_LOC, rawQuery.getString(rawQuery.getColumnIndex(KEY_LOC)));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public ArrayList<HashMap<String, String>> GetUserByUserId(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        Cursor query = writableDatabase.query(TABLE_Users, new String[]{"name", KEY_LOC, KEY_DESG}, "id=?", new String[]{String.valueOf(i)}, (String) null, (String) null, (String) null, (String) null);
        if (query.moveToNext()) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", query.getString(query.getColumnIndex("name")));
            hashMap.put(KEY_DESG, query.getString(query.getColumnIndex(KEY_DESG)));
            hashMap.put(KEY_LOC, query.getString(query.getColumnIndex(KEY_LOC)));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public void DeleteUser(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_Users, "id = ?", new String[]{String.valueOf(i)});
        writableDatabase.close();
    }

    public int UpdateUserDetails(String str, String str2, int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_LOC, str);
        contentValues.put(KEY_DESG, str2);
        return writableDatabase.update(TABLE_Users, contentValues, "id = ?", new String[]{String.valueOf(i)});
    }
}
