package com.example.co2monitor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Used to show info on CO2 levels
public class InfoActivity extends AppCompatActivity {

    private Button ppm250;
    private Button ppm400;
    private Button ppm1000;
    private Button ppm2000;
    private Button ppm5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// Used in order to make a back arrow past_button to go back to the MainActivity

        ppm250 = findViewById(R.id.ppm250);
        ppm250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("250-400ppm");
                builder.setMessage("Normal background concentration in outdoor ambient air");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });



        ppm400 = findViewById(R.id.ppm400);
        ppm400.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("400-1000ppm");
                builder.setMessage("Typical concentration level of occupied indoor spaces with good air exchange.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });


        ppm1000 = findViewById(R.id.ppm1000);
        ppm1000.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("1000-2000ppm");
                builder.setMessage("Level associated with complaints of drowsiness and poor air.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        ppm2000 = findViewById(R.id.ppm2000);
        ppm2000.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("2000-5000ppm");
                builder.setMessage("Headaches, sleepiness and stagnant, stale, stuffy air. Poor " +
                        " concentration, loss of attention, increased heart rate and slight nausea may also be " +
                        " present.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        ppm5000 = findViewById(R.id.ppm5000);
        ppm5000.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("5000ppm");
                builder.setMessage(" Indication of unusual air conditions where high levels of other gases could also" +
                        " be present. Toxicity or oxygen deprivation could occur. This is the permissible exposure " +
                        " limit for daily workplace exposures. It is recommended that the average concentration over " +
                        " an 8-hour period should not exceed 5,000 ppm.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();

                    }
                });
                builder.show();
            }
        });

    }
}