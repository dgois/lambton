package com.example.macstudent.imageviewexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;
    private Uri uriPhoto;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.camera_menu_action, menu);
        imageView = (ImageView) findViewById(R.id.imageView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:
                if (checkPermission()) {

                    Toast.makeText(this,"Permission already granted.", Toast.LENGTH_LONG).show();
                    dispatchTakePictureIntent();

                } else {

                    Toast.makeText(this, "Please request permission.", Toast.LENGTH_LONG).show();
                    requestPermission();
                }
                break;
        }
        return (super.onOptionsItemSelected(item));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                } else {
                    Toast.makeText(this, "Permission Denied, You cannot access location data and camera.", Toast.LENGTH_SHORT).show();
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Parcelable parcelableExtra = data.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
                if(parcelableExtra != null) {
                    Uri imageUri = (Uri) parcelableExtra;
                    imageView.setImageURI(imageUri);
                } else {
                    Log.e("DENIS", "Parcelable is null, but " + uriPhoto.toString());
                }

                imageView.setImageURI(uriPhoto);
            }
        } else {
            Log.e("DENIS", "It is not OK");
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);

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
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.macstudent.imageviewexample", photo);

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

//    private void askForPermission(String permission, Integer requestCode) {
//        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {
//
//                //This is called if user has denied the permission before
//                //In this case I am just asking the permission again
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
//
//            } else {
//
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
//            }
//        } else {
//            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
//            switch (requestCode) {
//                //Location
//
//                case CAMERA_PERMISSION_REQUEST_CODE:
//                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                        startActivityForResult(takePictureIntent, 12);
//                    }
//                    break;
//            }
//
//            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//        }
//
//    }
}
