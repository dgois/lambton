package com.c0711561.mad3125.finalproject.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.model.User;
import com.c0711561.mad3125.finalproject.repository.UserRepository;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.lang.reflect.Array;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {

    public static int SIGNUP_REQUEST_CODE = 30;

    @NotEmpty
    @Email
    @InjectView(R.id.edtSignUpEmail)
    TextInputEditText edtSignUpEmail;

    @NotEmpty
    @Password
    @InjectView(R.id.edtSignUpPassword)
    TextInputEditText edtSignUpPassword;

    @NotEmpty
    @ConfirmPassword
    @InjectView(R.id.edtSignUpPasswordConfirmation)
    TextInputEditText edtSignUpPasswordConfirmation;

    @InjectView(R.id.btnSignUpSave)
    Button btnSignUpSave;
    @InjectView(R.id.usersType)
    Spinner usersType;

    private Validator validator;
    private UserRepository userRepository;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        userRepository = new UserRepository(getApplication());
        populateSpinnerUsersType();
    }

    private void populateSpinnerUsersType() {
        adapter = ArrayAdapter.createFromResource(this, R.array.usersType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usersType.setAdapter(adapter);
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "GetBack", Toast.LENGTH_SHORT).show();

        User newUser = new User(edtSignUpEmail.getText().toString(), edtSignUpPassword.getText().toString(), adapter.getItem(usersType.getSelectedItemPosition()).toString());
        userRepository.insertAll(newUser);

        finish();
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

    @OnClick(R.id.btnSignUpSave)
    public void onViewClicked() {
        validator.validate();
    }
}
