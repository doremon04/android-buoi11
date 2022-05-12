package com.example.buoi11.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.buoi11.entity.Contact;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactDAOImpl implements IContactDAO {
    private SQLiteDatabase mDB;

    public ContactDAOImpl(Context ctx) {
        DatabaseHelper helper = new DatabaseHelper(ctx);
        mDB = helper.getWritableDatabase();
    }

    @Override
    public List<Contact> select() {
        String sql = "SELECT * FROM tblStudent";
        List<Contact> list = new ArrayList<>();
        Cursor c = mDB.rawQuery(sql, null);
        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String fullname = c.getString(c.getColumnIndex("fullname"));
            @SuppressLint("Range") String phone = c.getString(c.getColumnIndex("phone"));
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));
            Contact contact = new Contact(id, fullname, phone, email);
            list.add(contact);
        }
        return list;
    }

    @Override
    public Contact selectById(int id) {
        String sql = "SELECT * FROM tblBook WHERE id = ?";
        Cursor c = mDB.rawQuery(sql, new String[]{String.valueOf(id)});
        while (c.moveToNext()) {
            @SuppressLint("Range") String fullname = c.getString(c.getColumnIndex("fullname"));
            @SuppressLint("Range") String phone = c.getString(c.getColumnIndex("phone"));
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));

            Contact contact = new Contact(id, fullname, phone, email);
            return contact;
        }
        return null;
    }

    @Override
    public boolean insert(Contact c) {
        ContentValues cv = new ContentValues();
        cv.put("fullname", c.getFullname());
        cv.put("phone", c.getPhone());
        cv.put("email", c.getEmail());
        long newId = mDB.insert("tblStudent", null, cv);
        if (newId > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Contact c) {

        ContentValues cv = new ContentValues();
        cv.put("fullname", c.getFullname());
        cv.put("phone", c.getPhone());
        cv.put("email", c.getEmail());
        long newId = mDB.update("tblStudent", cv, " id = " + c.getId(), null);
        if (newId > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        long newId = mDB.delete("tblStudent", "id =" + id, null);
        if (newId > 0) return true;
        return false;
    }
}
