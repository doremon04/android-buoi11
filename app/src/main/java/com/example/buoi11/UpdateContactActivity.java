package com.example.buoi11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buoi11.dao.ContactDAOImpl;
import com.example.buoi11.dao.IContactDAO;
import com.example.buoi11.entity.Contact;

public class UpdateContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact_acitivity);

        Button button = findViewById(R.id.btnUpdate);
        button.setOnClickListener(view -> {
            EditText edtFullname = (EditText) findViewById(R.id.edtFullname);
            EditText edtPhone = (EditText) findViewById(R.id.edtPhone);
            EditText edtEmail = (EditText) findViewById(R.id.edtEmail);

            String fullname = edtFullname.getText().toString();
            String phone = edtPhone.getText().toString();
            String email = edtEmail.getText().toString();

            Contact contact = new Contact(fullname, phone, email);
            IContactDAO iContactDAO = new ContactDAOImpl(UpdateContactActivity.this);
            boolean result = iContactDAO.update(contact);
            if (result) {
                Toast.makeText(UpdateContactActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();

                // go to main activity
                Intent intent = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(UpdateContactActivity.this, "Update error", Toast.LENGTH_SHORT).show();
        });
    }
    private void loadContact(){

    }
}