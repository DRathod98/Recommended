package com.example.recomended;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button forgotButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //init
        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);
        forgotButton = findViewById(R.id.forgotButton);

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmial = emailEditText.getText().toString();

                if(userEmial.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter valid email address", Toast.LENGTH_LONG).show();
                }else{

                    mAuth.sendPasswordResetEmail(userEmial).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Please check your email to reset your password", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ForgotPassword.this, Login.class));
                            }else{
                                String message = task.getException().getMessage();

                                Toast.makeText(getApplicationContext(), "Please try again" + message, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        }


}
