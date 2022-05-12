package com.example.buoi11;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buoi11.adapter.AdapterContact;
import com.example.buoi11.dao.ContactDAOImpl;
import com.example.buoi11.dao.IContactDAO;
import com.example.buoi11.databinding.ActivityMainBinding;
import com.example.buoi11.entity.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Contact> lst;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadContact();
    }

    private void loadContact() {
        IContactDAO contactDAO = new ContactDAOImpl(this);
        lst = contactDAO.select();

        AdapterContact adapterContact = new AdapterContact(this, lst);
        binding.listContact.setAdapter(adapterContact);

        binding.listContact.setOnItemClickListener((adapterView, view, pos, l) -> {
            Intent intent = new Intent(MainActivity.this, UpdateContactActivity.class);
            int idB = lst.get(pos).getId();
            intent.putExtra("idb", idB);
            startActivity(intent);
        });

        registerForContextMenu(binding.listContact);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContact();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuAdd:
                Intent intent = new Intent(this, AddNewActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}