package com.example.macstudent.sqlliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.macstudent.sqlliteexample.adapter.ContactAdapter;
import com.example.macstudent.sqlliteexample.db.dao.ContactDAO;
import com.example.macstudent.sqlliteexample.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ListContactsActivity extends AppCompatActivity {

    ListView contacts;

    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        contacts = (ListView) findViewById(R.id.contacts);
        contactDAO = new ContactDAO(this);
        ContactAdapter adapter = new ContactAdapter(this, contactDAO.getAllContacts());
        contacts.setAdapter(adapter);
    }
}
