package com.c0711561.mad3125.finalproject.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.Manifest.permission.CAMERA;


public class ReportNewProblemActivity extends AppCompatActivity implements Validator.ValidationListener {

    public static final int REPORTED_NEW_PROBLEM = 34;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @NotEmpty
    @InjectView(R.id.edtNewProblemTitle)
    TextInputEditText edtNewProblemTitle;

    @NotEmpty
    @InjectView(R.id.edtNewProblemDescription)
    TextInputEditText edtNewProblemDescription;

    @NotEmpty
    @InjectView(R.id.edtNewProblemCategory)
    TextInputEditText edtNewProblemCategory;

    @InjectView(R.id.btnNewProblemSave)
    Button btnNewProblemSave;

    @InjectView(R.id.imageView)
    ImageView imageView;

    private Validator validator;
    private ProblemRepository problemRepository;
    private Uri uriPhoto;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_new_problem);
        ButterKnife.inject(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        problemRepository = new ProblemRepository(getApplication());
    }

    @OnClick(R.id.btnNewProblemSave)
    public void onViewClicked() {
        validator.validate();
    }

    @OnClick(R.id.imageView)
    public void onViewClickedImage() {
        Log.e("DENIS", "Image view");
        if (checkPermission()) {
            Toast.makeText(this,"Permission already granted.", Toast.LENGTH_LONG).show();
            dispatchTakePictureIntent();

        } else {

            Toast.makeText(this, "Please request permission.", Toast.LENGTH_LONG).show();
            requestPermission();
        }
    }

    @Override
    public void onValidationSucceeded() {
        Problem newProblem = new Problem(edtNewProblemTitle.getText().toString(), edtNewProblemDescription.getText().toString(), "Location test", edtNewProblemCategory.getText().toString(), new byte[10], new Date());
        long createdProblemId = problemRepository.insert(newProblem);
        getIntent().putExtra("createdProblemId", createdProblemId);
        setResult(RESULT_OK, getIntent());
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

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photo = null;
            try {
                photo = createImageFile();
            } catch (IOException e) {
                Log.e("DENIS", "Image view");
                Log.e("TAKE_PHOTO", e.getMessage());
            }

            if (photo != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.c0711561.mad3125.finalproject", photo);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                takePictureIntent.putExtra("test", "Denis");
                uriPhoto = photoURI;
                Log.e("DENIS", "Set URI on Extras. URI: " + photoURI.toString());

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Log.e("DENIS", "Photo is null");
            }

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
