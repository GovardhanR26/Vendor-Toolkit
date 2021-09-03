package com.example.try2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // creating variables for our list view.
    private ListView coursesLV;

    // creating a new array list.
    ArrayList<String> coursesArrayList;

    // creating a variable for database reference.
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // initializing variables for listviews.
        coursesLV = findViewById(R.id.idLVCourses);

        // initializing our array list
        coursesArrayList = new ArrayList<String>();

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String username = bundle.getString("USERNAME");
        double[] userCoord = bundle.getDoubleArray("USER_COORDS");

        // calling a method to get data from
        // Firebase and set data to list view
        initializeListView(userCoord);

    }

    private void initializeListView(double[] userCoord) {
        // creating a new array adapter for our list view.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, coursesArrayList);
        coursesLV.setAdapter(adapter);
        // below line is used for getting reference
        // of our Firebase Database.
        reference = FirebaseDatabase.getInstance().getReference().child("Vendors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                coursesArrayList.clear();
                for( DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Vendor info = snapshot1.getValue(Vendor.class);
                    Double vendorLat = Double.valueOf(info.getLati());
                    Double vendorLongi = Double.valueOf(info.getLongi());
                    Double custLat = Double.valueOf(userCoord[0]);
                    Double custLongi= Double.valueOf(userCoord[1]);

//                    //checking the distance
                    DistanceCheck distCheck = new DistanceCheck();
                    Double distance = distCheck.distance(vendorLat, custLat, vendorLongi, custLongi, 0, 0);

                    //if distance less than 100 metres, display those Vendors
                    if(distance<100) {
                        String txt = info.getName();
                        coursesArrayList.add(txt);
                    }
//                    String txt = info.getName() + " : " + info.getCategory();
//                    coursesArrayList.add(txt);

                    // listener here ??
                    // OnClickListener to coursesLV
                    coursesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Object str = coursesLV.getItemAtPosition(position);
                            String strobj = String.valueOf(str);
                            Log.d("Clicked ListView", "onItemClick: "+strobj);


//                            prestationEco str = (prestationEco)o; //As you are using Default String Adapter
//                            Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();

                            // my code
                            Intent i = new Intent(HomeActivity.this, MapsActivity.class);
                            i.putExtra("vendorname",strobj);
                            startActivity(i);
                            //i.putExtra("longi",vendorLongi);
//
//                            Bundle bundle = new Bundle();
//                            bundle.putDoubleArray([vendorLat,vendorLongi]);
//                            startActivity(i);
                            // Creates an Intent that will load a map of San Francisco
//                            Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
//                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                            mapIntent.setPackage("com.google.android.apps.maps");
//                            startActivity(mapIntent);

                            //final String selectedFromList = (String) ListView.getItemAtPosition(position);

//                            ItemClicked item = Adapter.get

                           // String item = coursesArrayList.get(position);

//                            reference.orderByChild(selectedFromList).addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    String retrievedValue=dataSnapshot.child(selectedFromList).getValue().toString();
//                                    Toast.makeText(HomeActivity.this, "Value: "+retrievedValue, Toast.LENGTH_LONG).show();
//
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
                        }
                    });
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // in below line we are calling method for add child event
        // listener to get the child of our database.
//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // this method is called when new child is added to
//                // our data base and after adding new child
//                // we are adding that item inside our array list and
//                // notifying our adapter that the data in adapter is changed.
//                coursesArrayList.add(snapshot.getValue(String.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // this method is called when the new child is added.
//                // when the new child is added to our list we will be
//                // notifying our adapter that data has changed.
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                // below method is called when we remove a child from our database.
//                // inside this method we are removing the child from our array list
//                // by comparing with it's value.
//                // after removing the data we are notifying our adapter that the
//                // data has been changed.
//                coursesArrayList.remove(snapshot.getValue(String.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // this method is called when we move our
//                // child in our database.
//                // in our code we are note moving any child.
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // this method is called when we get any
//                // error from Firebase with error.
//            }
//        });
        // below line is used for setting
        // an adapter to our list view.
        coursesLV.setAdapter(adapter);
    }
}
