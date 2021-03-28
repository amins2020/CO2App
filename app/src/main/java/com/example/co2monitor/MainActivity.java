package com.example.co2monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Packages used for Firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  MainActivity extends AppCompatActivity {

// Public and Private members used in MainActivity
    private TextView co2lable;
    private TextView humidityLable;
    private TextView tempLable;

    private TextView co2Levels;
    private TextView temperature;
    private TextView humidity;

    private Button suggestion;
    private Button info;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference co2data = mDatabase.child("co2_ppm");
    DatabaseReference tempData = mDatabase.child("temperature");
    DatabaseReference humidityData = mDatabase.child("humidity");

//Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        co2lable = findViewById(R.id.label);//Indicates that the numbers below are CO2 ppms
        humidityLable = findViewById(R.id.humidityLabletextView);
        tempLable = findViewById(R.id.tempLableTextView);

        co2Levels = findViewById(R.id.co2Concentration);//Used to show ppm
        temperature = findViewById(R.id.tempTextView);
        humidity = findViewById(R.id.humidityTextView);

        //Button for SuggestionActivity
        suggestion = findViewById(R.id.suggestion_button);
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSuggestionActivity();
            }
        });

        //Button for InfoActivity
        info = findViewById(R.id.info_button);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInfoActivity();
            }
        });
    }

    //Method used to go to the suggestion activity
    public void openSuggestionActivity(){
        Intent intent = new Intent(this, SuggestionActivity.class);
        startActivity(intent);
    }

    //Method used to go to the info activity
    public void openInfoActivity(){
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);

    }

    // CO2 ppm will be shown with onStart after onCreate is called
    @Override
    protected void onStart() {
        super.onStart();

       co2data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String co2data = dataSnapshot.getValue(String.class);
                co2Levels.setText(co2data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       tempData.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String temp = dataSnapshot.getValue(String.class);
               temperature.setText(temp + " ˚C");
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       humidityData.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String hum = dataSnapshot.getValue(String.class);
               humidity.setText(hum + " %");
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
}