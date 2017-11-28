package com.example.macstudent.passingvalue;

import android.content.Intent;
import android.media.Rating;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class StartActivity extends AppCompatActivity {

    RadioGroup rg;
    CheckBox cbOne;
    CheckBox cbSecond;
    CheckBox cbThird;
    TextView txtCheckBox;
    ToggleButton tb;
    SeekBar sb;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);

                intent.putExtra("id", 1);
                intent.putExtra("name", "Denis Gois");

                startActivity(intent);
            }
        });

        rg = (RadioGroup) findViewById(R.id.rgOptions);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (R.id.rbFirst == i) {
                    Toast.makeText(StartActivity.this, "First", Toast.LENGTH_SHORT).show();
                } else if (R.id.rbSecond == i) {
                    Toast.makeText(StartActivity.this, "Second", Toast.LENGTH_SHORT).show();
                } else if (R.id.rbThird == i) {
                    Toast.makeText(StartActivity.this, "Third", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbOne = (CheckBox) findViewById(R.id.cbFirst);
        txtCheckBox = (TextView) findViewById(R.id.txtSelectCheckBox);
        cbOne.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cbOne.isChecked()) {
                    txtCheckBox.setText(txtCheckBox.getText() + " First:");
                } else {
                    txtCheckBox.setText("SelectedCheckBox: ");
                }
            }
        });

        tb = (ToggleButton) findViewById(R.id.tbButton);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(tb.isChecked()) {
                    Toast.makeText(StartActivity.this, tb.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StartActivity.this, tb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtCheckBox.setText("Value: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rb = (RatingBar) findViewById(R.id.ratingBar);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(StartActivity.this, "Value: " + v, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
