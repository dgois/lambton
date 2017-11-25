package com.example.macstudent.linearlayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtNumber1, edtNumber2;
    TextView result;
    Button btnSum, btnSub, btnMul, btnDiv;
    int a, b, c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_linear_layout);

        edtNumber1 = (EditText) findViewById(R.id.edtNumber1);
        edtNumber2 = (EditText) findViewById(R.id.edtNumber2);

        result = (TextView) findViewById(R.id.txtResult);
        btnSum = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumbers();

                c = a + b;

                result.setText(String.valueOf(c));
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumbers();

                c = a - b;

                result.setText(String.valueOf(c));
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumbers();

                c = a * b;

                result.setText(String.valueOf(c));
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumbers();

                if (b == 0) {
                    edtNumber2.setError(getString(R.string.error_message_division_by_zero));
                    return;
                }

                c = a / b;

                result.setText(String.valueOf(c));
            }
        });
    }

    private void getNumbers() {
        if (edtNumber1.getText() == null || "".equals(edtNumber1.getText().toString().trim())) {
            edtNumber1.setError(getString(R.string.label_error_inform_number_1));
            return;
        }
        if (edtNumber2.getText() == null || "".equals(edtNumber2.getText().toString().trim())) {
            edtNumber2.setError(getString(R.string.label_error_inform_number_2));
            return;
        }
        a = Integer.parseInt(edtNumber1.getText().toString());
        b = Integer.parseInt(edtNumber2.getText().toString());
    }
}
