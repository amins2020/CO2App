package com.example.co2monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

//Packages used for Firebase
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Public and Private members used in MainActivity/
    private static final String TAG = "MyActivity";
    private static short counter = -1;       //used in myThread1
    private static short counter2 = -1;      //Used in myThread2 for graphing
    private static int [] array = new int[4];       //used in myThread1
    private static int [] array_g = new int [6]; //Used in thread 2 to hold sensor readings every 5 sec
    private static Queue<Integer> q_graph = new LinkedBlockingQueue<>(360);  //Queue holding averaged data, averaged every 30 seconds


    private TextView co2lable;
    private TextView humidityLable;
    private TextView tempLable;

    private TextView co2Levels;
    private TextView temperature;
    private TextView humidity;

    private Button suggestion;
    private Button info;
    private Button past;

    DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");    //Firebase ref to check App connection to firebase
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();    //Firebase references
    DatabaseReference co2data = mDatabase.child("co2_ppm");
    DatabaseReference tempData = mDatabase.child("temperature");
    DatabaseReference humidityData = mDatabase.child("humidity");

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //Methods:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*------Hooks------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*-------ToolBar------*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*------NavigationMenu------*/

        //Hide or show items

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //
        co2lable = findViewById(R.id.label);//Indicates that the numbers below are CO2 ppms
        humidityLable = findViewById(R.id.humidityLabletextView);
        tempLable = findViewById(R.id.tempLableTextView);

        co2Levels = findViewById(R.id.co2Concentration);//Used to show ppm
        temperature = findViewById(R.id.tempTextView);
        humidity = findViewById(R.id.humidityTextView);

        //Button for SuggestionActivity /
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

        //Button for InfoActivity
        past = findViewById(R.id.past_button);
        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPastActivity();
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }

    }

    //Method used to go to the suggestion activity
    public void openSuggestionActivity() {
        Intent intent = new Intent(this, SuggestionActivity.class);
        startActivity(intent);
    }

    //Method used to go to the info activity
    public void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);

    }
    //Thread1 running with a while loop to check the connection between sensor and the app every 40 seconds
    Runnable myRunnable = new Runnable(){
        @Override
        public void run(){
            while(true){
                while(counter <= 3){            //sample 4 sensor readings
                    try {
                        Thread.sleep(10000);    //sample sensor reading every 10 seconds
                        co2data.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String sensor_data = dataSnapshot.getValue(String.class);
                                int data_int = Integer.parseInt(sensor_data);

                                array[counter] = data_int;
                                if (counter == 3){
                                    int sum = 0;
                                    for (int value : array) {
                                        sum = sum + value;
                                    }
                                    int average = sum/array.length;        //Average readings
                                    //Log.d(TAG, "MESSAGE*____________+++++++++++++++++++++++++++++++++++"+array[0]);
                                    if (average == array[0] && average == array[3]){    //Check if readings are the same
                                        Toast.makeText(MainActivity.this, "Data is out of sync, Check connection!",
                                                Toast.LENGTH_LONG).show();
                                        co2Levels.setText("Loading...");
                                        temperature.setText("Loading");
                                        humidity.setText("Loading");

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter = (short) (counter + 1);
                }
                counter = 0;
            }
        }
    };

    //Thread 2 for GRAPHING data

    Runnable myRunnable2 = new Runnable(){
        @Override
        public void run(){
                while(counter2 <= 5){
                    try {
                            Thread.sleep(5000);     //sample sensor readings every 5 seconds for graphing
                        co2data.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String co2data2 = dataSnapshot.getValue(String.class);
                                int co2data_int = Integer.parseInt(co2data2);
                                array_g[counter2] = co2data_int;
                                //Log.d(TAG, "MESSAGE*____________+++++++++++++++++++++++++++++++++++"+counter2);
                                if (counter2 == 5){
                                    int sum =0;
                                    for(int v:array_g){
                                        sum = sum + v;
                                    }
                                    int average = sum/array_g.length;
                                    //Log.d(TAG, "MESSAGE*____________+++++++++++++++++++++++++++++++++++"+average);
                                    if (q_graph.size() <= 360){
                                        q_graph.add(average);     //Add the average to queue
                                        int p = q_graph.peek();
                                       // Log.d(TAG, "MESSAGE*____________+++++++++++++++++++++++++++++++++++"+q_graph.size());

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter2 = (short) (counter2 + 1);
                    if (counter2 >= 6){
                        counter2 = 0;
                    }
                }
            }
    };

    public void openPastActivity() {
        Intent intent = new Intent(this, PastActivity.class);
        startActivity(intent);
    }

    // CO2 ppm will be shown with onStart after onCreate is called
    @Override
    protected void onStart() {
        super.onStart();
        Thread myThread = new Thread(myRunnable);
        Thread myThread2 = new Thread(myRunnable2);
        myThread.start();
        myThread2.start();

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


        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.d(TAG, "connected");
                    Toast.makeText(MainActivity.this, "Check Wifi connection!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "not connected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Listener was cancelled");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dot_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_tips:
                Intent i1 = new Intent(MainActivity.this, SuggestionActivity.class);
                startActivity(i1);
                break;
            case R.id.nav_info:
                Intent i2 = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(i2);
                break;

        }

        switch (item.getItemId()) {

            case R.id.setup:
                Intent i = new Intent(MainActivity.this, Settings.class);
                startActivity(i);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}