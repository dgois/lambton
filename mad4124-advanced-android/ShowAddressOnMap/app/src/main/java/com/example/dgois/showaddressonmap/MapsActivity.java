package com.example.dgois.showaddressonmap;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng mDefaultLocation = new LatLng(43.653226, -79.383184);
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 15));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                String address = getAddress(point);
                Toast.makeText(getApplicationContext(),
                        address,
                        Toast.LENGTH_LONG).show();
                mMap.addMarker(new MarkerOptions().position(point).title(address));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
            }
        });
    }

    private String getAddress(LatLng location) {
        StringBuilder locInfo = new StringBuilder();
        StringBuilder addressLine = new StringBuilder();
        if (Geocoder.isPresent()) {
            Geocoder coder = new Geocoder(this);
            try {
                List<Address> addresses = coder.getFromLocation(
                        location.latitude, location.longitude, 3);
                if (addresses != null) {
                    String placeName = addresses.get(0).getLocality();
                    String featureName = addresses.get(0).getFeatureName();
                    String country = addresses.get(0).getCountryName();
                    String road = addresses.get(0).getThoroughfare();
                    locInfo.append(String.format("[%s][%s][%s][%s]\n",
                            placeName, featureName, road, country));

                    int addIdx = addresses.get(0).getMaxAddressLineIndex();

                    for (int idx = 0; idx <= addIdx; idx++) {
                        addressLine.append(addresses.get(0).getAddressLine(idx)).append(", ");
                    }
                    addressLine = addressLine.deleteCharAt(addressLine.length() - 1);

                }
                Log.i("DENIS", locInfo.toString());
                Log.i("DENIS", addressLine.toString());
            } catch (IOException e) {
                Log.e("GPS", "Failed to get address", e);
            }
        } else {
            Toast.makeText(MapsActivity.this, "No geocoding available",
                    Toast.LENGTH_LONG).show();
        }
        return addressLine.toString();
    }
}
