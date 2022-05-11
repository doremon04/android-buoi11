package com.example.buoi11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.buoi11.adapter.AdapterContact;
import com.example.buoi11.dao.ContactDAOImpl;
import com.example.buoi11.dao.IContactDAO;
import com.example.buoi11.entity.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadContact();
    }

    private void loadContact(){
        IContactDAO contactDAO = new ContactDAOImpl(this);
        List<Contact> list = contactDAO.select();

        AdapterContact adapterContact = new AdapterContact(this, list);
        ListView listView = findViewById(R.id.listContact);
        listView.setAdapter(adapterContact);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        registerForContextMenu(listView);
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
        switch (id){
            case R.id.menuAdd:
                Intent intent = new Intent(this, AddNewActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}