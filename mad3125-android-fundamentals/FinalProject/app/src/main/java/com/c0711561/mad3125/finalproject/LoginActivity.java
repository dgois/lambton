package com.c0711561.mad3125.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.model.User;
import com.c0711561.mad3125.finalproject.repository.UserRepository;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @Email
    @InjectView(R.id.edtEmailLogin)
    EditText edtEmailLogin;

    @NotEmpty
    @Password
    @InjectView(R.id.edtPassword)
    EditText edtPassword;

    @InjectView(R.id.btnLogin)
    Button btnLogin;

    @InjectView(R.id.btnSignIn)
    Button btnSignIn;

    private Validator validator;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        userRepository = new UserRepository(getApplication());
//        userRepository.insertAll(new User("a@a.com", "123456", "USER"));

    }

    @OnClick({R.id.btnLogin, R.id.btnSignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                validator.validate();
                break;
            case R.id.btnSignIn:
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        User user = userRepository.findByEmail(edtEmailLogin.getText().toString());
        if (user != null) {
            Toast.makeText(this, "Proceed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
