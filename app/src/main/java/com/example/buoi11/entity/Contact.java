package com.example.buoi11.entity;

public class Contact {
    private int id;
    private String fullname;
    private String phone;
    private String email;

    public Contact(int id, String fullname, String phone, String email) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String fullname, String phone, String email) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
