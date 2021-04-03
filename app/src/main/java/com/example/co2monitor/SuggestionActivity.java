package com.example.co2monitor;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.style.TypefaceSpan;

import android.view.View;
import android.widget.Button;

//Used to show tips on improving air quality
public class SuggestionActivity extends AppCompatActivity {

    private Button sugg1;
    private Button sugg2;
    private Button sugg3;
    private Button sugg4;
    private Button sugg5;
    private Button sugg6;
    private Button sugg7;
    private Button sugg8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Used in order to make a back arrow past_button to go back to the MainActivity

        sugg1= findViewById(R.id.sugg1);
        sugg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("Smoking Habits");
                builder.setMessage("Avoid smoking indoors to improve your indoor air quality. Ban usage of tobacco and " +
                        "smoking products, which releases CO2 and other chemicals in the air.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });


        sugg2= findViewById(R.id.sugg2);
        sugg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("Control of Humidity Levels");
                builder.setMessage("Make sure to check around the house for water leaks to avoid and stop the growth of mould due to dampness.\n" +
                        "Use a bathroom or kitchen exhaust fan when showering or cooking to reduce humidity. Keep the fan on for at least 30 minutes afterward.\n"+
                        "Use a dehumidifier to remove water from the air, especially in the summer. Remove water from dehumidifiers when not in use so bacteria do not grow.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        sugg3= findViewById(R.id.sugg3);
        sugg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("Air Circulation");
                builder.setMessage("If the weather permits and if the room possesses windows, open them to let fresh air " +
                        " in and circulate around your rooms.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        sugg4= findViewById(R.id.sugg4);
        sugg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("Household Appliance Maintenance");
                builder.setMessage("If the home layout permits, make sure household appliances are installed, maintained, " +
                        " and working according to the manufacturers' instructions: gas water heaters,gas or " +
                        " oil furnaces, gas or wood stoves, and gas or wood fireplaces, which are all sources of CO2.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        sugg5= findViewById(R.id.sugg5);
        sugg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("House Cleaning");
                builder.setMessage("Clean floors with a damp mop or cloth.\n\n"+
                        " Clean the lint tray every time you use the dryer and check for lint build-up on the outside vent. \n\n" +
                        " Use a mattress and pillow protector. Also, vacuum mattresses and wash sheets once a week in hot water. \n\n" +
                        " Vacuum often. Install a central vacuum that is vented outdoors.\n\n");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        sugg6= findViewById(R.id.sugg6);
        sugg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("Attached Garage");
                builder.setMessage("If the house has an attached garage, avoid idling your car, gas-powered snow blower "+
                        "in your garage. Carbon monoxide produced by theses objects will reduce the quality of "+
                        "breathable air. Pollutants can enter your home from the garage whether the garage door is "+
                        "is open or closed.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        sugg7= findViewById(R.id.sugg7);
        sugg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("Air Quality Items");
                builder.setMessage("<li>Investing in an air purifier reduces indoor allergens when placed in" +
                        " the most commonly used area of the house. However, it does not completely remove allergens.\n" +
                        "Beeswaxcandles, which act like air purifiers.\n" +
                        "A salt lamp, which is a light source put into a" +
                        " large mass of Himalayan salt, another object to possess properties that reduce" +
                        " allergies and asthma symptoms.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        sugg8= findViewById(R.id.sugg8);
        sugg8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                builder.setTitle("Indoor Plants");
                builder.setMessage("Plants are natural purifiers too that can recycle the air from CO2 to oxygen. Here are " +
                        "several examples of plants that are good for their air filter capabilities: " +
                        "red-edged dracaena, weeping fig, and bamboo palm.");

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