package com.example.co2monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.*;
import java.text.*;

public class PastActivity extends AppCompatActivity {

    private GraphView graph;
    private LineGraphSeries<DataPoint> ppmseries;
    private Graph_util graph_inst;


    // Date Time = new Date();
    // SimpleDateFormat time_am_pm = new SimpleDateFormat( "hh:mm aa"); // Formats the time as Hour:min am/pm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// Used in order to make a back arrow past_button to go back to the MainActivity

        graph = findViewById(R.id.graph);

        graph.getViewport().setScrollable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScrollableY(true); // enables vertical zooming and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.getGridLabelRenderer().setNumHorizontalLabels(12); // 12 labels for the horizontal labels (planning to do 1 Data Point every 15 mins)
        graph.getGridLabelRenderer().setNumVerticalLabels(10);// Sets the number of label on the x-axis
        graph.getGridLabelRenderer().setLabelsSpace(20); // adds more space between labels

        Intent intent = getIntent();
        graph_inst = intent.getParcelableExtra("queueObj");

        }

    Runnable myRunnable = new Runnable(){
        @Override
        public void run(){
                    try {
                        DataPoint[] points = new DataPoint[12]; // Up to 12 readings can be shown in the graph (covering up to 3hrs)
                        double[] ppmreadings =new double[30]; // Array used to store 30 readings in order to do an averaging over 15 mins
                        int counter = 0; // keeps counts of the number of elements before doing an average for 15 mins
                        double sumreadings = 0; // sum of 30 readings
                        double avg = 0; // 15 min average that will be used for a data point
                        Thread.sleep(5100);

                        while(!graph_inst.q_graph.isEmpty()){ //loops as long as the
                            for(int i=0; i < ppmreadings.length; i++) { //
                                ppmreadings[i] = graph_inst.q_graph.remove();
                                counter++;

                                if(counter == ppmreadings.length){ // if the counter is equal to 30, reset it, then sum all the elements and do an average
                                    counter = 0;
                                    for(int j = 0; j < 30; j++){
                                        sumreadings = sumreadings + ppmreadings[j];
                                    }
                                    avg = sumreadings/30;
                                }
                            }
                            int i = 0;
                            points[i+1] = new DataPoint(i,avg);
                            i++;
                            ppmseries = new LineGraphSeries<>(points);
                            graph.addSeries(ppmseries);
                            Thread.sleep(5100);

                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
    };
        @Override
        protected void onStart(){
        super.onStart();

        Thread aThread = new Thread(myRunnable);

        }

}

