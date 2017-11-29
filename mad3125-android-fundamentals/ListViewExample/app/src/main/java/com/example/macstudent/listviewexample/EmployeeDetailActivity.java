package com.example.macstudent.listviewexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.macstudent.listviewexample.model.Employee;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EmployeeDetailActivity extends AppCompatActivity {

    @InjectView(R.id.employeeId)
    TextView employeeId;
    @InjectView(R.id.employeeName)
    TextView employeeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        ButterKnife.inject(this);
        Employee e = null;
        if (getIntent().getExtras() != null) {
            e = (Employee) getIntent().getExtras().getSerializable("emp");
            employeeId.setText(String.valueOf(e.getEmployeeId()));
            employeeName.setText(e.getEmployeeName());
        }

        SharedPreferences preferences = getSharedPreferences("myPref");
    }

    public static void startIntent(Context context) {
        context.startActivity(new Intent(context, EmployeeDetailActivity.class));
    }

    public static void startIntent(Context context, Bundle bundle) {
        Intent me = new Intent(context, EmployeeDetailActivity.class);
        me.putExtras(bundle);
        context.startActivity(me);
    }
}
