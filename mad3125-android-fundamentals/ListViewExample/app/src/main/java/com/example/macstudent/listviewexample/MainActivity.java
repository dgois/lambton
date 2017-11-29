package com.example.macstudent.listviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.lstEmployee)
    ListView lstEmployee;

    String[] employeeNames = {"Denis", "Arthur", "Bill", "Thommy", "Gislaine", "Ana", "Bruno", "Carl", "Ester", "Fagner", "Fred", "Guilherme"};
    List<String> employeeNameList = Arrays.asList(employeeNames);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, employeeNameList);
        lstEmployee.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(MainActivity.this, "Add Menu", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_setting:
                Toast.makeText(MainActivity.this, "Setting Menu", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
