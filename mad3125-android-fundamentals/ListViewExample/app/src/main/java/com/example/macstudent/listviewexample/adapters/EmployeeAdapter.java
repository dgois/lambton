package com.example.macstudent.listviewexample.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.macstudent.listviewexample.R;
import com.example.macstudent.listviewexample.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macstudent on 2017-11-28.
 */

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        super(context, 0, employees);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Employee employee = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_employee, parent, false);
        }

        TextView txtEmployeeId = (TextView) convertView.findViewById(R.id.txtEmployeeId);
        TextView txtEmployeeName = (TextView) convertView.findViewById(R.id.txtEmployeeName);

        txtEmployeeId.setText(String.valueOf(employee.getEmployeeId()));
        txtEmployeeName.setText(employee.getEmployeeName());

        return convertView;
    }
}
