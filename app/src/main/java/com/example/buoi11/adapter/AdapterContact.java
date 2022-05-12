package com.example.buoi11.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buoi11.MainActivity;
import com.example.buoi11.R;
import com.example.buoi11.UpdateContactActivity;
import com.example.buoi11.dao.ContactDAOImpl;
import com.example.buoi11.dao.IContactDAO;
import com.example.buoi11.entity.Contact;

import java.util.List;

public class AdapterContact extends ArrayAdapter<Contact> {
    private final Context mCtx;
    private final List<Contact> mList;

    public AdapterContact(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, R.layout.item_contact, objects);
        this.mCtx = context;
        this.mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(this.mCtx).inflate(R.layout.item_contact, null);
        }
        Contact c = mList.get(position);
        TextView txtFullname = v.findViewById(R.id.itemFullname);
        TextView txtPhone = v.findViewById(R.id.itemPhone);
        TextView txtEmail = v.findViewById(R.id.itemEmail);

        ImageView btnUpdate = v.findViewById(R.id.itemBtnUpdate);
        ImageView btnDelete = v.findViewById(R.id.itemBtnDelete);

        txtFullname.setText(c.getFullname());
        txtPhone.setText(c.getPhone());
        txtEmail.setText(c.getEmail());

        btnUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(mCtx, UpdateContactActivity.class);
            int idB = c.getId();
            intent.putExtra("idb", idB);
            mCtx.startActivity(intent);
        });

        btnDelete.setOnClickListener(view -> {
            IContactDAO iContactDAO = new ContactDAOImpl(mCtx);
            boolean result = iContactDAO.delete(c.getId());
            if (result) {
                Toast.makeText(mCtx, "Delete Succesfully", Toast.LENGTH_SHORT).show();
                AdapterContact.this.notifyDataSetChanged();

                // go to main activity
                Intent intent = new Intent(mCtx, MainActivity.class);
                mCtx.startActivity(intent);
            } else Toast.makeText(mCtx, "Delete Error", Toast.LENGTH_SHORT).show();
        });

        return v;
    }
}
