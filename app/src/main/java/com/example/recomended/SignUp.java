package com.example.recomended;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private Button signUpButton;
    private TextView skipTextView;
    private EditText firstNameEditText, lastNameEditText, addressEditText, cityEditText, phoneNumberEditText;
    private FirebaseAuth mAuth;
    private DatabaseReference usersReference;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Init fields
        mAuth = FirebaseAuth.getInstance(); // Authentication variable
        currentUserId = mAuth.getCurrentUser().getUid();// Getting unique id for the User
        usersReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId); //Putting all the users in database.
        signUpButton = findViewById(R.id.postButton);
        skipTextView = findViewById(R.id.skipTextView);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        cityEditText = findViewById(R.id.cityEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);


        //Takes to login page after registering
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registration();
                Intent intent = new Intent(signUpButton.getContext(), HomePage.class);
                startActivity(intent);
            }
        });

        //Takes to home page without login or signUp
        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(skipTextView.getContext(), HomePage.class);
                startActivity(intent);
            }
        });
    }

    private void registration() {

        final String firstName = firstNameEditText.getText().toString();
        final String lastName = lastNameEditText.getText().toString();
        final String address = addressEditText.getText().toString();
        final String city = cityEditText.getText().toString();
        final String phoneNumber = phoneNumberEditText.getText().toString();


        if (firstName.isEmpty()) {
            Toast.makeText(this, "Please enter your first name.", Toast.LENGTH_LONG).show();
        } else if (lastName.isEmpty()) {
            Toast.makeText(this, "Please enter your last name.", Toast.LENGTH_LONG).show();
        } else if (address.isEmpty()) {
            Toast.makeText(this, "Please enter your address.", Toast.LENGTH_LONG).show();
        } else if (city.isEmpty()) {
            Toast.makeText(this, "Please enter your city.", Toast.LENGTH_LONG).show();
        } else if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please enter your phone number.", Toast.LENGTH_LONG).show();
        } else {
            HashMap userMap = new HashMap();
            userMap.put("FirstName", firstName);
            userMap.put("LastName", lastName);
            userMap.put("Address", address);
            userMap.put("City", city);
            userMap.put("PhoneNumber", phoneNumber);
            usersReference.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_LONG).show();
                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(getApplicationContext(), "Error :" + message, Toast.LENGTH_LONG).show();
                    }
                }

            });
        }
    }
}


         /*   updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {*/
/*
@Override
public void onComplete(@NonNull Task task) {
        if(task.isSuccessful()){
        Toast.makeText(getApplicationContext(),"Data added successfully",Toast.LENGTH_LONG).show();
        }else{
        String message = task.getException().getMessage();
        Toast.makeText(getApplicationContext(),"Error :"+message,Toast.LENGTH_LONG).show();
        }*/
