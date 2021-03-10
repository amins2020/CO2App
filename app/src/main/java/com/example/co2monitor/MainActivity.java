package com.example.co2monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference co2data = mDatabase.child("co2_ppm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.label);
        textView2 = findViewById(R.id.co2Concentration);
    }

    @Override
    protected void onStart() {
        super.onStart();

        co2data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String co2data = dataSnapshot.getValue(String.class);
                textView2.setText(co2data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
    }
}