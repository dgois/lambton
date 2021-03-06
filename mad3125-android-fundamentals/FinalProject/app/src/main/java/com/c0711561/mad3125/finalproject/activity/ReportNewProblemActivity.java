package com.c0711561.mad3125.finalproject.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;


public class ReportNewProblemActivity extends AppCompatActivity implements Validator.ValidationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final int REPORTED_NEW_PROBLEM = 34;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @NotEmpty
    @InjectView(R.id.edtNewProblemTitle)
    TextInputEditText edtNewProblemTitle;

    @NotEmpty
    @InjectView(R.id.edtNewProblemDescription)
    TextInputEditText edtNewProblemDescription;

    @InjectView(R.id.imageView)
    ImageView imageView;

    @InjectView(R.id.problemCategories)
    Spinner problemCategories;

    private Validator validator;
    private ProblemRepository problemRepository;
    private Uri uriPhoto;
    private GoogleApiClient googleApiClient;
    private Location mLastKnownLocation;
    private ArrayAdapter adapter;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_new_problem);
        ButterKnife.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Report a New Problem");
        validator = new Validator(this);
        validator.setValidationListener(this);
        problemRepository = new ProblemRepository(getApplication());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this )
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        googleApiClient.connect();

        populateSpinnerProblemCategories();
    }

    @Override
    protected void onStart() {
        super.onStart();
        askForPermissions();
        displayLocationSettingsRequest(this);
        getDeviceLocation();
    }

    private void populateSpinnerProblemCategories() {
        adapter = ArrayAdapter.createFromResource(this, R.array.problemCategories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        problemCategories.setAdapter(adapter);
    }

    private void askForPermissions() {
        if (!checkPermission(CAMERA)) {
            requestPermission(CAMERA);
        }
        if (!checkPermission(ACCESS_FINE_LOCATION)) {
            requestPermission(ACCESS_FINE_LOCATION);
        }
    }

    @OnClick(R.id.imageView)
    public void onViewClickedImage() {
        if (checkPermission(CAMERA)) {
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onValidationSucceeded() {
        Problem newProblem = new Problem(
                edtNewProblemTitle.getText().toString(),
                edtNewProblemDescription.getText().toString(),
                getAddressBasedOnLatitudeAndLongitude(),
                mLastKnownLocation.getLatitude(),
                mLastKnownLocation.getLongitude(),
                adapter.getItem(problemCategories.getSelectedItemPosition()).toString(),
                convertImageToBytes(),
                new Date());
        long createdProblemId = problemRepository.insert(newProblem);
        getIntent().putExtra("createdProblemId", createdProblemId);
        setResult(RESULT_OK, getIntent());
        finish();
    }

    private byte[] convertImageToBytes() {
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
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

    private void requestPermission(String permission) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQUEST_CODE);

    }

    private boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photo = null;
            try {
                photo = createImageFile();
            } catch (IOException e) {
                Log.e("TAKE_PHOTO", e.getMessage());
            }

            if (photo != null) {
                Uri photoURI = FileProvider.getUriForFile(ReportNewProblemActivity.this, "com.c0711561.mad3125.finalproject", photo);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                uriPhoto = photoURI;

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Log.e("TAKE_PHOTO", "Photo is null");
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                imageView.setImageURI(uriPhoto);
        } else {
            Log.e("TAKE_PHOTO", "The response from take pickture intent is not ok");
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("NEW_PROBLEM", "On connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("NEW_PROBLEM", "Play services connection suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("NEW_PROBLEM", "Play services connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        if (checkPermission(ACCESS_FINE_LOCATION)) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mLastKnownLocation = location;
                    } else {
                        mLastKnownLocation = new Location("default");
                        mLastKnownLocation.setLatitude(37.4220);
                        mLastKnownLocation.setLongitude(-122.0840);
                    }
                }
            });
        }
    }

    private String getAddressBasedOnLatitudeAndLongitude() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        // Address found using the Geocoder.
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    mLastKnownLocation.getLatitude(),
                    mLastKnownLocation.getLongitude(),
                    1);
            return addresses.get(0).getAddressLine(0);
        } catch (IOException ioException) {
            Log.e("NEW_PROBLEM", ioException.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            Log.e("NEW_PROBLEM", "Latitude = " + mLastKnownLocation.getLatitude() +
                    ", Longitude = " + mLastKnownLocation.getLongitude(), illegalArgumentException);
        }
        return "Default Address";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.solver_problem_context, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_problem:
                validator.validate();
                return true;
            case android.R.id.home:
                showAlert();
                return true;
            default: return true;
        }
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You will loose this problem!")
        .setTitle("Cancel New Problem creation?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NavUtils.navigateUpFromSameTask(ReportNewProblemActivity.this);
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.create().show();
    }

    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("LOCATION_NEW_PROBLEM", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("LOCATION_NEW_PROBLEM", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(ReportNewProblemActivity.this, 11);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("LOCATION_NEW_PROBLEM", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("LOCATION_NEW_PROBLEM", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }
}
