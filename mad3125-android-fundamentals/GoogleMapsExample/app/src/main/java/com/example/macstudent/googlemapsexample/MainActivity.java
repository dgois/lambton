package com.example.macstudent.googlemapsexample;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView lstAdresses;
    Address currentAddress;

    private List<Address> address;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = new ArrayList<>();
        address.add(new Address("My Home", 43.779864, -79.343579));

        lstAdresses = (ListView) findViewById(R.id.lstAdresses);

        List<String> addressAsString = loadAddress();

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addressAsString);

        lstAdresses.setAdapter(stringArrayAdapter);

        lstAdresses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                mapIntent.putExtra("longitude", address.get(i).getLongitude());
                mapIntent.putExtra("latitude", address.get(i).getLatitude());

                startActivity(mapIntent);
            }
        });

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



//        if (currentAddress != null) {
//            addressAsString.add(currentAddress.getName());
//            address.add(currentAddress);
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("DENIS", "Checking permissions");
        if (!checkPermissions()) {
            Log.i("DENIS", "Request permissions");
            requestPermission();
        } else {
            Log.i("DENIS", "Get Last Location");
            getLastLocation();
        }
    }

    private void requestPermission() {
        boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (shouldProvideRationale) {
            startLocationPermissionRequest();
        } else {
            startLocationPermissionRequest();
        }
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private List<String> loadAddress() {

        List<String> aString = new ArrayList<>();
        for (int i = 0; i < address.size(); i ++) {
            aString.add(address.get(i).getName());
        }

        return aString;
    }

    @SuppressWarnings("MissingPermission")
    public void getLastLocation() {
        mFusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();

                            String str = String.format(Locale.ENGLISH, "Lat %s: %f", mLastLocation.getLatitude());
                            str += String.format(Locale.ENGLISH, "Lng %s: %f", mLastLocation.getLongitude());

                            Log.d("LOC", str);
                            currentAddress = new Address("Current Address", mLastLocation.getLatitude(), mLastLocation.getLongitude());

                        } else {
                            Log.w("LOC", "getLastLocation:exception", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                Log.i("LOC", "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
            }
        }
    }
}
