package com.example.macstudent.myapplication;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    //https://github.com/ragunathjawahar/android-saripaar
    @NotEmpty
    @Email
    @InjectView(R.id.edtEmail)
    EditText edtEmail;

    @InjectView(R.id.btnValidate)
    Button btnValidate;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "db_denis").build();

        final Validator validator = new Validator(this);
        validator.setValidationListener(this);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        db.userDAO().insertAll(new User(edtEmail.getText().toString()));

        String users = Arrays.toString(db.userDAO().getAll().toArray());

        Toast.makeText(this, "Yay! we got it right! " + users, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
