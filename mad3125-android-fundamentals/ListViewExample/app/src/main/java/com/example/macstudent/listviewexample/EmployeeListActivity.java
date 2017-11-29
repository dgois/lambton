package com.example.macstudent.listviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.macstudent.listviewexample.adapters.EmployeeAdapter;
import com.example.macstudent.listviewexample.model.Employee;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EmployeeListActivity extends AppCompatActivity {

    @InjectView(R.id.lstEmployees)
    ListView lstEmployees;
    ArrayList<Employee> employees;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        ButterKnife.inject(this);
        employees = populateEmployeeList();

        adapter = new EmployeeAdapter(this, employees);

        lstEmployees.setAdapter(adapter);

        lstEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int listIndex, long l) {
                Employee e = employees.get(listIndex);
                //adapter.add(new Employee(new Random().nextInt(100), "TEST-TEST-Denis"));

                Bundle bundle = new Bundle();
                bundle.putSerializable("emp", e);
                EmployeeDetailActivity.startIntent(EmployeeListActivity.this, bundle);
            }
        });
    }

    private ArrayList<Employee> populateEmployeeList() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Denis"));
        employees.add(new Employee(2, "Abel"));
        employees.add(new Employee(3, "Beatriz"));
        employees.add(new Employee(4, "Carlos"));
        employees.add(new Employee(5, "Dayane"));
        employees.add(new Employee(6, "Fabricio"));
        employees.add(new Employee(7, "Estenio"));
        employees.add(new Employee(8, "Gabriel"));
        employees.add(new Employee(9, "Henrique"));
        employees.add(new Employee(10, "Joao"));

        return employees;
    }
}
