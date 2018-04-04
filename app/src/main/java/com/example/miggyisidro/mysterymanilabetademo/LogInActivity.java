package com.example.miggyisidro.mysterymanilabetademo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private Button logIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView signUp;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), OSHomeActivity.class));
        }

        logIn = (Button) findViewById(R.id.btnLogIn);
        editTextEmail = (EditText) findViewById(R.id.et_username);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        signUp = (TextView) findViewById(R.id.tv_signUp);

        progressDialog = new ProgressDialog(this);


        logIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    private void userLogIn(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(LogInActivity.this, "Signed in Successfully!", Toast.LENGTH_SHORT).show();
                    Intent goToOShome = new Intent(LogInActivity.this, OSHomeActivity.class);
                    startActivity(goToOShome);
                }
                else{
                    Toast.makeText(LogInActivity.this, "Could not Sign In. Please try again.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == logIn){
            userLogIn();
        }

        if(v == signUp){
            finish();
            startActivity(new Intent(this, RegisterUserActivity.class));
        }
    }
}
