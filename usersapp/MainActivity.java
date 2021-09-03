package com.example.usersapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlgn,btnreg;
    String mobile,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String vname = getIntent().getStringExtra("VendorName");

        mAuth = FirebaseAuth.getInstance();
        username = (EditText)findViewById(R.id.loginuser);
        //username = (EditText)findViewById(R.id.umail);
        password = (EditText)findViewById(R.id.loginPassword);
        btnlgn = (Button)findViewById(R.id.loginbutton);
        btnreg = (Button)findViewById(R.id.registerbutton);


        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //progressbar.setVisibility(View.VISIBLE);
                String email = username.getText().toString();
                String pass = password.getText().toString();
                Boolean val = validate(email,pass);
                if(val){
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(
                                                @NonNull Task<AuthResult> task)
                                        {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(),
                                                        "Login successful!!",
                                                        Toast.LENGTH_LONG)
                                                        .show();

                                                // hide the progress bar
                                                //progressBar.setVisibility(View.GONE);

                                                // if sign-in is successful
                                                // intent to home activity
                                                Intent intent
                                                        = new Intent(MainActivity.this,
                                                        Home.class);
                                                intent.putExtra("VendorName",vname);
                                                startActivity(intent);
                                            }

                                            else {

                                                // sign-in failed
                                                Toast.makeText(getApplicationContext(),
                                                        "Login failed!!",
                                                        Toast.LENGTH_LONG)
                                                        .show();

                                                // hide the progress bar
                                                //progressbar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                }

            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Registration.class);
                startActivity(i);
            }
        });
    }

    public boolean validate(String user,String pass){
        if(user.equals("") || pass.equals("")){
            Toast.makeText(getApplicationContext(),"Please enter all the credentials",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
}