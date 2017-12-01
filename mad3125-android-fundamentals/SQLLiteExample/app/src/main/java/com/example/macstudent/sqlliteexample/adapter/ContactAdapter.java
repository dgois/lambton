package com.example.macstudent.sqlliteexample.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.macstudent.sqlliteexample.R;
import com.example.macstudent.sqlliteexample.model.Contact;

import java.util.List;

/**
 * Created by macstudent on 2017-11-30.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(@NonNull Context context, @NonNull List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        TextView txtId = (TextView) convertView.findViewById(R.id.contactId);
        TextView txtName = (TextView) convertView.findViewById(R.id.contactName);
        TextView txtPhone = (TextView) convertView.findViewById(R.id.contactPhone);

        txtId.setText(String.valueOf(contact.getId()));
        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhoneNumber());

        return convertView;
    }
}
