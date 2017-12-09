package com.c0711561.mad3125.finalproject.activity;

import android.content.Intent;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
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

import static android.Manifest.permission.CAMERA;


public class ReportNewProblemActivity extends AppCompatActivity implements Validator.ValidationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final int REPORTED_NEW_PROBLEM = 34;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 2;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

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
    private GoogleApiClient googleApiClient;
    private Location mLastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_new_problem);
        ButterKnife.inject(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        problemRepository = new ProblemRepository(getApplication());

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        googleApiClient.connect();
    }

    @OnClick(R.id.btnNewProblemSave)
    public void onViewClicked() {
        validator.validate();
    }

    @OnClick(R.id.imageView)
    public void onViewClickedImage() {
        Log.e("DENIS", "Image view");
        if (checkPermission()) {
            dispatchTakePictureIntent();
        } else {
            requestPermission(CAMERA);
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
                edtNewProblemCategory.getText().toString(),
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
                Log.e("TAKE_PHOTO", e.getMessage());
            }

            if (photo != null) {
                Uri photoURI = FileProvider.getUriForFile(ReportNewProblemActivity.this, "com.c0711561.mad3125.finalproject", photo);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                takePictureIntent.putExtra("test", "Denis");
                uriPhoto = photoURI;

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Log.e("TAKE_PHOTO", "Photo is null");
            }
        }

        Log.d("DENIS", "Get device location");
        getDeviceLocation();
        Log.d("DENIS", "DONE");
        if (mLastKnownLocation != null) {
            Log.d("DENIS", "lat : " + mLastKnownLocation.getLatitude() + " - long: " + mLastKnownLocation.getLongitude());
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
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        if (mLocationPermissionGranted) {
            mLastKnownLocation = LocationServices.FusedLocationApi
                    .getLastLocation(googleApiClient);
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
                    // In this sample, we get just a single address.
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
}
