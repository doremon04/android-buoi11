package com.example.buoi11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buoi11.dao.ContactDAOImpl;
import com.example.buoi11.dao.IContactDAO;
import com.example.buoi11.entity.Contact;

public class AddNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        Button button = findViewById(R.id.btnAddNew);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtFullname = (EditText) findViewById(R.id.edtFullname);
                EditText edtPhone = (EditText) findViewById(R.id.edtPhone);
                EditText edtEmail = (EditText) findViewById(R.id.edtEmail);

                String fullname = edtFullname.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();

                Contact contact = new Contact(fullname, phone, email);
                IContactDAO iContactDAO = new ContactDAOImpl(AddNewActivity.this);
                boolean result = iContactDAO.insert(contact);
                if (result)
                    Toast.makeText(AddNewActivity.this, "Add new successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddNewActivity.this, "Add new error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}