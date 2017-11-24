package com.example.denisgois.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SecondActivity extends Activity implements View.OnClickListener {

    EditText edtNo1, edtNo2;
    Button btnAdd, btnSub, btnMul, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edtNo1 = (EditText) findViewById(R.id.editNumber1);
        edtNo2 = (EditText) findViewById(R.id.editNumber2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        btnSub.setOnClickListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a, b, c;
                a = Integer.parseInt(edtNo1.getText().toString());
                b = Integer.parseInt(edtNo2.getText().toString());
                c = a + b;
                Toast.makeText(getApplicationContext(), "Add : " + c, Toast.LENGTH_SHORT).show();
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a, b, c;
                a = Integer.parseInt(edtNo1.getText().toString());
                b = Integer.parseInt(edtNo2.getText().toString());
                if (b > 0) {
                    c = a / b;
                    Toast.makeText(getApplicationContext(), "Div : " + c, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a, b, c;
                a = Integer.parseInt(edtNo1.getText().toString());
                b = Integer.parseInt(edtNo2.getText().toString());
                c = a * b;
                Toast.makeText(getApplicationContext(), "Mul : " + c, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int a, b, c;
        a = Integer.parseInt(edtNo1.getText().toString());
        b = Integer.parseInt(edtNo2.getText().toString());
        c = a - b;
        String result = String.format(Locale.ENGLISH, "Sub : %d", c);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}
