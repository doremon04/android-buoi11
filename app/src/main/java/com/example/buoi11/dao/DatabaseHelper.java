package com.example.buoi11.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tblStudent (\n" +
                "    id       INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                "    fullname STRING        NOT NULL,\n" +
                "    phone    VARCHAR (11),\n" +
                "    email    VARCHAR (255) \n" +
                ");\n";
        db.execSQL(sql);

        sql = "INSERT INTO tblStudent(fullname, phone, email) VALUES ('Hoàng Cao Long', '0123456789', 'longhc@gmail.com')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
