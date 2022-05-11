package com.example.buoi11.dao;

import com.example.buoi11.entity.Contact;

import java.util.List;

public interface IContactDAO {
    public List<Contact> select();
    public boolean insert(Contact c);
    public boolean update(Contact c);
    public boolean delete(int id);
}
