package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private ListView listView;

    // creating a new array list.
    ArrayList<String> arrayList;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView)findViewById(R.id.listViews);

        arrayList =  new ArrayList<String>();



        Button button2 = findViewById(R.id.back_button);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(
                        intent
                );
            }

        });
        initializeListView();


    }
    private void initializeListView() {
        // creating a new array adapter for our list view.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrayList);

        // below line is used for getting reference
        // of our Firebase Database.
        reference = FirebaseDatabase.getInstance().getReference();

        // in below line we are calling method for add child event
        // listener to get the child of our database.

        int count = 1;

        while (count < 6){
            int finalCount = count;
            reference.child("user"+ count).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Map<String, Object> map = (Map<String, Object>)  snapshot.getValue();

                    arrayList.add(" " + finalCount + " ."+ "Username : " + map.get("username") + "         " + "Password : " + " " +map.get("password"));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            count++ ;

        }

        // below line is used for setting
        // an adapter to our list view.
        listView.setAdapter(adapter);
    }


}


//        myRef.child("user").addListenerForSingleValueEvent(new ValueEventListener(){
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        if(dataSnapshot.exists()){
//        Map<String, Object> map = (Map<String, Object>)  dataSnapshot.getValue();
//
//        String  name = (String) map.get("username");
//        String  pass  = (String) map.get("password");
//        arrayList.add("Casdcas");
//
//        arrayAdapter.notifyDataSetChanged();
//        }
//        }
//
//
//@Override
//public void onCancelled(@NonNull DatabaseError databaseError) {
//        // Getting Post failed, log a message
//        Toast.makeText(MainActivity2.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
//
//        }
//        });
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//@Override
//public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(MainActivity2.this, "The user clicked is: "+ i + "" +arrayList.get(i).toString(), Toast.LENGTH_SHORT).show();
//        }
//        });