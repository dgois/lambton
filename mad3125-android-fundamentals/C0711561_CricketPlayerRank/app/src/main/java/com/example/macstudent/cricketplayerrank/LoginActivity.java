package com.example.macstudent.cricketplayerrank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.macstudent.cricketplayerrank.model.User;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.edtEmail)
    EditText edtUsername;
    @InjectView(R.id.edtPassword)
    EditText edtPassword;
    @InjectView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                User user = new User(userName, password);
                if (user.isValid()) {
                    Intent intent = new Intent(LoginActivity.this, PlayerPortifolioEntryActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Login")
                            .setMessage("Wrong password or username. Please try again!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
    }
}
