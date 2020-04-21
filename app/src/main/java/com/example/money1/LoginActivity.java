package com.example.money1;

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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editTextEMail;
    private EditText editTextPassport;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextEMail = findViewById(R.id.editTextEMail);
        editTextPassport = findViewById(R.id.editTextPassport);


    }

    public void OnCliclLogin(View view) {
        String email = editTextEMail.getText().toString().trim();
        String passport = editTextPassport.getText().toString().trim();
        if (email.isEmpty() || passport.isEmpty()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, passport).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "ошибка" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
