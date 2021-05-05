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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegestrationPage extends AppCompatActivity {

    private Button register;
    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration_page);

        //Init fields
        mAuth = FirebaseAuth.getInstance(); // Authentication variable
        register = findViewById(R.id.register);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confrimPassword = confirmPasswordEditText.getText().toString();


        if(email.isEmpty()){
            Toast.makeText(this,"Please enter your email.",Toast.LENGTH_LONG).show();
        }else if(password.isEmpty()){
            Toast.makeText(this,"Please enter your password.",Toast.LENGTH_LONG).show();
        }else if(password.length() < 8){
            Toast.makeText(this,"Your password is too short.",Toast.LENGTH_LONG).show();
        }else if(confrimPassword.isEmpty()){
            Toast.makeText(this,"Your password is too short.",Toast.LENGTH_LONG).show();
        }else if(!password.equals(confrimPassword)){
            Toast.makeText(this,"Your passwords doesn't match",Toast.LENGTH_LONG).show();
        }else{

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Your account has been created",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(register.getContext(), SignUp.class);
                        startActivity(intent);

                    }else{
                        String message = task.getException().getMessage();
                        Toast.makeText(getApplicationContext(),"Error :"+message,Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }
}
