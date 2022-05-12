package com.example.buoi11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buoi11.dao.ContactDAOImpl;
import com.example.buoi11.dao.IContactDAO;
import com.example.buoi11.entity.Contact;
import com.example.buoi11.databinding.ActivityUpdateContactBinding;

public class UpdateContactActivity extends AppCompatActivity {

    private ActivityUpdateContactBinding binding;
    private int mIDContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadContact();

        binding.btnUpdate.setOnClickListener(view -> update());
    }

    private void update() {
        String fullname = binding.edtFullname.getText().toString();
        String phone = binding.edtPhone.getText().toString();
        String email = binding.edtEmail.getText().toString();

        Contact contact = new Contact(mIDContact, fullname, phone, email);
        IContactDAO iContactDAO = new ContactDAOImpl(UpdateContactActivity.this);
        boolean result = iContactDAO.update(contact);
        if (result) {
            Toast.makeText(UpdateContactActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();

            // go to main activity
            Intent intent = new Intent(UpdateContactActivity.this, MainActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(UpdateContactActivity.this, "Update error", Toast.LENGTH_SHORT).show();
    }

    private void loadContact() {
        int idB = getIntent().getExtras().getInt("idb");
        IContactDAO contactDAO = new ContactDAOImpl(this);
        Contact c = contactDAO.selectById(idB);
        mIDContact = c.getId();

        binding.edtFullname.setText(c.getFullname());
        binding.edtPhone.setText(c.getPhone());
        binding.edtEmail.setText(c.getEmail());
    }
}