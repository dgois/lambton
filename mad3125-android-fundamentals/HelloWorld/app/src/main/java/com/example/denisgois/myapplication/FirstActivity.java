package com.example.denisgois.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtTitle = (TextView) findViewById(R.id.txtMessage);
        txtTitle.setText("Welcome Denis to Android Programming");

        String s = txtTitle.getText().toString();

        Log.e("STRING", s);

        //support app compatibility AppCompatActivity
        //getSupportActionBar();
    }
}
