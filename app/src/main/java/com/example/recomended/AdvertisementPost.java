package com.example.recomended;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class AdvertisementPost extends AppCompatActivity {

    private EditText advertisementTitleEditText , advertisementCategoryEditText, typeAdvertisementEditText, advertisementDescriptionEditText, costEditText, datePostedEditText;
    private Button postButton;
    private Calendar myCalendar;
    private FirebaseAuth mAuth;
    private DatabaseReference advertisementReference, userReference;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement_post);

        //Init
        mAuth = FirebaseAuth.getInstance(); // Authentication variable
        currentUserId = mAuth.getCurrentUser().getUid();// Getting unique id for the User
        userReference = FirebaseDatabase.getInstance().getReference().child("User"); // Putting value form user tabel
        advertisementReference = FirebaseDatabase.getInstance().getReference().child("Advertisement").child(currentUserId); //Putting all the users in database.
        myCalendar = Calendar.getInstance();
        advertisementTitleEditText = findViewById(R.id.advertisementTitleEditText);
        advertisementCategoryEditText = findViewById(R.id.advertisementCategoryEditText);
        typeAdvertisementEditText= findViewById(R.id.typeAdvertisementEditText);
        advertisementDescriptionEditText = findViewById(R.id.advertisementDescriptionEditText);
        costEditText= findViewById(R.id.costEditText);
        datePostedEditText = findViewById(R.id.datePostedEditText);
        postButton = findViewById(R.id.postButton);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        datePostedEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AdvertisementPost.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adPost();
            }
        });

    }

    private void adPost() {
        String title = advertisementTitleEditText.getText().toString();
        String category = advertisementCategoryEditText.getText().toString();
        String type = typeAdvertisementEditText.getText().toString();
        String description = advertisementDescriptionEditText.getText().toString();
        String cost = costEditText.getText().toString();
        String date = datePostedEditText.getText().toString();

        if(title.isEmpty()){
            Toast.makeText(this,"Please enter advertisement title.",Toast.LENGTH_LONG).show();
        }else if(category.isEmpty()){
            Toast.makeText(this,"Please enter advertisement category.",Toast.LENGTH_LONG).show();
        }else if(type.isEmpty()){
            Toast.makeText(this,"Please enter advertisement type.",Toast.LENGTH_LONG).show();
        }else if(description.isEmpty()){
            Toast.makeText(this,"Please enter advertisement description.",Toast.LENGTH_LONG).show();
        }else if(cost.isEmpty()){
            Toast.makeText(this,"Please enter your advertisement cost.",Toast.LENGTH_LONG).show();
        }else if (date.isEmpty()){
            Toast.makeText(this,"Please enter your advertisement date.",Toast.LENGTH_LONG).show();
        }else{
            HashMap userMap = new HashMap();
            userMap.put("AdvertisementTitle",title);
            userMap.put("AdvertisementCategory",category);
            userMap.put("AdvertisementType", type);
            userMap.put("AdvertisementDescription", description);
            userMap.put("AdvertisementCost", cost);
            userMap.put("AdvertisementDate",date);
            String postRandomAd = "";
            advertisementReference.child(postRandomAd).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void updateLabel(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datePostedEditText.setText(sdf.format(myCalendar.getTime()));
    }


}
