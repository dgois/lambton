package com.example.macstudent.sqlliteexample;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.macstudent.sqlliteexample.db.dao.ContactDAO;
import com.example.macstudent.sqlliteexample.db.dao.PersonDAO;
import com.example.macstudent.sqlliteexample.model.Contact;
import com.example.macstudent.sqlliteexample.model.Person;

import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private ContactDAO contactDAO;
    private PersonDAO personDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity_main);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Contacts Denis");
        }

        contactDAO = new ContactDAO(ContactActivity.this);
        personDAO = new PersonDAO(ContactActivity.this);

        Log.d("CONTACT: ", "Inserting ..");
        contactDAO.addContact(new Contact("Ravi", "9100000000"));
        contactDAO.addContact(new Contact("Srinivas", "9199999999"));
        contactDAO.addContact(new Contact("Tommy", "9522222222"));
        contactDAO.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = contactDAO.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("CONTACT: ", log);
        }

        Log.d("CONTACT:", "Count: " + contactDAO.getContactsCount());

        personDAO.addPerson(new Person("Denis"));

        Log.d("CONTACT:", "Person: " + personDAO.getPerson(1).getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_contacts, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.listContacts == item.getItemId()) {
            startActivity(new Intent(this, ListContactsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
