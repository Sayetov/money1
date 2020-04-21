package com.example.money1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerViewMesseges;
    private MessegesAdapter adapter;

    private EditText editTextMessege;
    private EditText editTextSum;

    private Button buttonSendMessege;

    private String athor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewMesseges = findViewById(R.id.recyclerViewMesseges);
        editTextMessege = findViewById(R.id.editTextMessege);
        editTextSum =findViewById(R.id.editTextSum);
        buttonSendMessege = findViewById(R.id.buttonSendMessege);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        adapter = new MessegesAdapter();
        recyclerViewMesseges.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMesseges.setAdapter(adapter);

        athor = "Anry";
        buttonSendMessege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessege();
            }
        });

        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(this, "Logged", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        }

        }



    private void sendMessege() {
        String textOfMessege = editTextMessege.getText().toString().trim();
        String textSum = editTextSum.getText().toString().trim();

        if (!textOfMessege.isEmpty()){


            db.collection("messeges").add(new Messeige(athor, textOfMessege, textSum, System.currentTimeMillis())).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    editTextMessege.setText("");
                    editTextSum.setText("");
                    recyclerViewMesseges.scrollToPosition(adapter.getItemCount() - 1);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT);
                }
            })
            ;


        }
    }
    @Override
    protected  void onResume(){
        super.onResume();
        db.collection("messeges").orderBy("date").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    List<Messeige>messeiges = queryDocumentSnapshots.toObjects(Messeige.class);
                    adapter.setMesseiges(messeiges);
                }


            }
        });
    }

}
