package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView)findViewById(R.id.listViews);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();



        ArrayList<String> arrayList = new ArrayList<>();
        myRef.child("user").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Map<String, Object> map = (Map<String, Object>)  dataSnapshot.getValue();

                    String  name = (String) map.get("username");
                    String  pass  = (String) map.get("password");
                    Toast.makeText(MainActivity2.this, "The user clicked is: "+""+ pass, Toast.LENGTH_SHORT).show();

                    arrayList.add(name + pass);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });
        arrayList.add("Username :" + "Password :");
        arrayList.add("Username :" + "Password :");

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        Button button2 = findViewById(R.id.back_button);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity2.this, "The user clicked is: "+ i + "" +arrayList.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(
                        intent
                );
            }
        });

    }
}