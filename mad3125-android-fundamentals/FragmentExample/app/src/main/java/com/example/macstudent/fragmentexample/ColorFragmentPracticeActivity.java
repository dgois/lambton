package com.example.macstudent.fragmentexample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ColorFragmentPracticeActivity extends AppCompatActivity {

    @InjectView(R.id.btnRed)
    Button btnRed;
    @InjectView(R.id.btnBlue)
    Button btnBlue;
    @InjectView(R.id.btnGreen)
    Button btnGreen;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_fragment_practice);
        ButterKnife.inject(this);
        fragmentManager = getSupportFragmentManager();

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFragment(R.color.holo_red_dark);
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFragment(android.R.color.holo_blue_dark);
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFragment(android.R.color.holo_green_dark);
            }
        });
    }

    private Fragment createFragment(int color) {
        Bundle arguments = new Bundle();
        arguments.putInt("color", color);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EmptyFragment fragment = new EmptyFragment();
        fragment.setArguments(arguments);
        fragmentTransaction.add(R.id.colorFragmentContainers, fragment);
        fragmentTransaction.commit();
        return fragment;
    }
}
