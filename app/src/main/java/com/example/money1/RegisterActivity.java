package com.example.money1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editTextEMail;
    private EditText editTextPassport;

    private TextView textViewHaveAnAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEMail = findViewById(R.id.editTextEMail);
        editTextPassport = findViewById(R.id.editTextPassport);
        textViewHaveAnAccount = findViewById(R.id.textViewHaveAnAccount);
        textViewHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public void OnclickRegister(View view) {
        String email = editTextEMail.getText().toString().trim();
        String passport = editTextPassport.getText().toString().trim();
        if (email.isEmpty() || passport.isEmpty()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, passport).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent  = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "ошибка" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
