package com.example.try2;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.try2.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    double vendorLati, vendorLongi;

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    public double getLongitude(DatabaseReference reference) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vendorLongi = dataSnapshot.getValue(Double.class);
//                System.out.println(vendorLati);
                Log.d("VendorLongitude", " "+ vendorLongi);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return vendorLongi;
    }

    public double getLatitude(DatabaseReference reference) {
//        double vendorLat = 12.3;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vendorLati = dataSnapshot.getValue(Double.class);
//                System.out.println(vendorLati);
                Log.d("VendorLatitude", " "+ vendorLati);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return vendorLati;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String vendorname = getIntent().getStringExtra("vendorname");

        Log.d("VendorName !!!!!! ", ""+vendorname);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vendors").child(vendorname).child("longi");

        vendorLongi = getLongitude(reference);

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Vendors").child(vendorname).child("lati");

        vendorLati = getLatitude(reference2);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                LatLng sydney = new LatLng(vendorLati,vendorLongi);
                Log.d("New VendorLatitude", " "+ vendorLati);
                Log.d("New VendorLongitude", " "+ vendorLongi);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Vendor Marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
            }
        },4*1000);
    }
}
