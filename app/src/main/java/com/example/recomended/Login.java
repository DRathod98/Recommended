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

public class Login extends AppCompatActivity {

    EditText emailEditText,passwordEditText;
    TextView signUptextView, skipTextView,forgotPasssword;
    Button loginButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //InitComponents
        mAuth = FirebaseAuth.getInstance(); // Authentication variable
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUptextView = findViewById(R.id.signUpTextView);
        loginButton = findViewById(R.id.loginButton);
        skipTextView = findViewById(R.id.skipTextView);
        forgotPasssword = findViewById(R.id.forgotPassword);

        //Takes to Register Page
        signUptextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(signUptextView.getContext(), RegestrationPage.class);
                startActivity(intent);
            }
        });

        forgotPasssword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgotPasssword.getContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });

        //Takes to home page after successful login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
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

    private void login() {
        String email = emailEditText.getText().toString();
        String password  =  passwordEditText.getText().toString();

        if(email.isEmpty()){
            Toast.makeText(this,"Please enter your email", Toast.LENGTH_LONG).show();
        }else if(password.isEmpty()){
            Toast.makeText(this,"Please enter your password", Toast.LENGTH_LONG).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
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
















/*  private ImageView recommendedIconImageView;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                recommendedIconImageView.setVisibility(View.GONE);
                loadingProgressBar.setVisibility(View.GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(Login.this, R.color.colorSplashText));
                recommendedIconImageView.setImageResource(R.drawable.blacklogo);
                startAnimation();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void initViews() {
        recommendedIconImageView = findViewById(R.id.recommendedIconImageView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);

    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = recommendedIconImageView.animate();
        viewPropertyAnimator.x(50f);
        viewPropertyAnimator.y(100f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });*/