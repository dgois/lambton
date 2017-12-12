package com.c0711561.mad3125.finalproject.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProblemsMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ProblemRepository problemRepository;
    private Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems_maps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Problem Location");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        problemRepository = new ProblemRepository(getApplication());

        int problemId = getIntent().getIntExtra("problemId", 0);
        problem = problemRepository.findById(problemId);
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
        mMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        LatLng problemLocation = new LatLng(problem.getLatitude(), problem.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(problemLocation)
                .title(problem.getTitle())
                .visible(true)
                .snippet(problem.getDescription()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(problemLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(problem.getLatitude(), problem.getLongitude()), 19.0f));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentActivityIntent = NavUtils.getParentActivityIntent(this);
                parentActivityIntent.putExtra("problemId", problem.getId());
                NavUtils.navigateUpTo(this, parentActivityIntent);
                return true;
            default: return true;
        }
    }
}
