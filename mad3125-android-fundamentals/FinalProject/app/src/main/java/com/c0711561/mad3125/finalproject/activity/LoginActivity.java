package com.c0711561.mad3125.finalproject.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
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
    TextInputEditText edtEmailLogin;

    @NotEmpty
    @Password
    @InjectView(R.id.edtPassword)
    TextInputEditText edtPassword;

    @InjectView(R.id.btnLogin)
    Button btnLogin;

    @InjectView(R.id.btnSignUp)
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

        createAdminUserAndSetLogin();
    }

    private void createAdminUserAndSetLogin() {

        User foundUser = userRepository.findByEmail("a@a.com");
        if (foundUser == null) {
            User reporter = new User("a@a.com", "123456", User.roleTypes.REPORTER.name());
            userRepository.insertAll(reporter);
        }
        foundUser = userRepository.findByEmail("b@b.com");
        if (foundUser == null) {
            User solver = new User("b@b.com", "123456", User.roleTypes.SOLVER.name());
            userRepository.insertAll(solver);
        }
        edtEmailLogin.setText("a@a.com");
        edtPassword.setText("123456");
    }

    @OnClick({R.id.btnLogin, R.id.btnSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                validator.validate();
                break;
            case R.id.btnSignUp:
                startActivityForResult(new Intent(LoginActivity.this, SignUpActivity.class), SignUpActivity.SIGNUP_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        User user = userRepository.findByEmail(edtEmailLogin.getText().toString());
        if (user != null) {
            setLoggedUserOnSharedPreferences(user);
            if (user.isReporter()) {
                startNextActivity(this, ProblemListActivity.class);
            } else {
                startNextActivity(this, SolverProblemListActivity.class);
            }
        } else {
            Toast.makeText(this, "Invalid login credentials. New User? Sign Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLoggedUserOnSharedPreferences(User user) {
        SharedPreferences.Editor editor = getSharedPreferences(getPackageName(), MODE_PRIVATE).edit();
        editor.putString("loggedUserEmail", user.getEmail());
        editor.commit();
    }

    private void startNextActivity(Context context, Class<? extends AppCompatActivity> clazz) {
        Intent nextIntent = new Intent(this, clazz);
        startActivity(nextIntent);
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
