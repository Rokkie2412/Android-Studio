package com.example.projectuasmoop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSql extends SQLiteOpenHelper {
    public DatabaseSql(Context context) {
        super(context, "Database_Perawat.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, name text,penyakit text, kondisi1 text, kondisi2 text, kondisi3 text, kondisi4 text)");
        db.execSQL("CREATE TABLE userDewasa(id integer PRIMARY KEY AUTOINCREMENT, name text,penyakit  text, kondisi1 text, kondisi2 text, kondisi3 text, kondisi4 text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userDewasa");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public Boolean insertUser(String name,String penyakit, String kondisi1, String kondisi2, String kondisi3, String kondisi4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("penyakit",penyakit);
        contentValues.put("kondisi1", kondisi1);
        contentValues.put("kondisi2",kondisi2);
        contentValues.put("kondisi3",kondisi3);
        contentValues.put("kondisi4",kondisi4);
        long insert = db.insert("user", null, contentValues);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean insertUserDewasa(String name,String penyakit,String kondisi1, String kondisi2, String kondisi3, String kondisi4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("penyakit",penyakit);
        contentValues.put("kondisi1", kondisi1);
        contentValues.put("kondisi2",kondisi2);
        contentValues.put("kondisi3",kondisi3);
        contentValues.put("kondisi4",kondisi4);
        long insert = db.insert("userDewasa", null, contentValues);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

}
