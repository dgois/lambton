package com.example.macstudent.fragmentexample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener {

    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.activityTitle);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("FRAGMENT", "Test");
    }

    @Override
    public void setData(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        txtTitle.setText(str);
    }
}
