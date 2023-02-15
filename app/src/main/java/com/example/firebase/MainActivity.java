package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private int count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Button submitButton;
        submitButton = findViewById(R.id.button);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String _username = username.getText().toString();
                String _password = password.getText().toString();

                HashMap hashMap = new HashMap();
                hashMap.put("username", _username);
                hashMap.put("password", _password);

                if (_username.isEmpty()  || _password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Kindly fill in the required fields" , Toast.LENGTH_SHORT).show();

                }
                else {

                    myRef.child("user"+ count).setValue(hashMap).addOnSuccessListener((OnSuccessListener<? super Void>) (aVoid) ->{
                        Toast.makeText(MainActivity.this, "Success" , Toast.LENGTH_SHORT).show();
                        username.setText("");
                        password.setText("");
                        if (count < 5){
                            Toast.makeText(MainActivity.this, "Add another User" , Toast.LENGTH_SHORT).show();

                        }
                        count++;

                    }).addOnFailureListener((e)->{
                        Toast.makeText(MainActivity.this, "Unsuccessful" , Toast.LENGTH_SHORT).show();

                    });

                    if (count == 5) {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                        startActivity(intent);
                    }
                }
            }
        });
    }
}