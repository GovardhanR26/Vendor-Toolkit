package com.example.usersapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {
    EditText name,mobile,password1,password2,useriso,mail,cat;
    Button regbtn;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Vendors vendor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Vendors");
        vendor = new Vendors();
        mAuth = FirebaseAuth.getInstance();

        name = (EditText)findViewById(R.id.regname);
        mobile = (EditText)findViewById(R.id.regmob);
        password1 = (EditText)findViewById(R.id.regpass);
        password2 = (EditText)findViewById(R.id.regrepass);
        useriso = (EditText)findViewById(R.id.regiso);
        mail = (EditText)findViewById(R.id.regmail);
        regbtn = (Button)findViewById(R.id.regbtn);
        cat = (EditText)findViewById(R.id.cat);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rname = name.getText().toString();
                String rmob = mobile.getText().toString();
                String ruser = useriso.getText().toString();
                String rpass1 = password1.getText().toString();
                String rpass2 = password2.getText().toString();
                String rmail = mail.getText().toString();
                String category = cat.getText().toString();


                //Intent i = new Intent(Registration.this, MainActivity.class);

                if (rname.equals("") || rmob.equals("") || rpass1.equals("") || rpass2.equals("") || ruser.equals("") || rmail.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (rpass1.equals(rpass2)) {
                        addDatatoFirebase(rname, rmob, ruser, rmail);
                        registerNewUser();
                        Intent intent
                                = new Intent(Registration.this,
                                MainActivity.class);
                        intent.putExtra("VendorName",rname);
                        startActivity(intent);

                    }else
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void registerNewUser()
    {

        // show the visibility of progress bar to show loading
        //progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = mail.getText().toString();
        password = password1.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            //progressBar.setVisibility(View.GONE);

                            // if the user created intent to login activity

                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }


    private void addDatatoFirebase(String name, String phone, String iso, String email) {
        // below 3 lines of code is used to set
        // data in our object class.
        vendor.setVendorName(name);
        vendor.setVendorContactNumber(phone);
        vendor.setVendorIso(iso);
        vendor.setVendorEmail(email);
        vendor.setLatitude("0");
        vendor.setLongitude("0");

        // we are use add value event listener method
        // which is called with database reference.

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.child(vendor.getVendorName()).setValue(vendor);
                // after adding this data we are showing toast message.
                Toast.makeText(Registration.this, "data added", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(Registration.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}







