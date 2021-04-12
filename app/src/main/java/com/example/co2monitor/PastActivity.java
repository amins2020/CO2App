package com.example.co2monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

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

    private static final String TAG = "PastActivity";
    private GraphView graph;
    private DataPoint ppmseries;
    private static Queue<Integer> queue = new LinkedBlockingQueue<>(360);


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
        queue = intent.getParcelableExtra("key");

    }
    Runnable myRunnable2 = new Runnable() {
        @Override
        public void run() {

            //ppmseries = new LineGraphSeries<>();
            int counter = queue.size();
            int [] x_array= {0, 40, 80, 120, 160, 200, 240, 280, 320, 360};
            int i= 0;
            int value= 0;
            while (!queue.isEmpty()) {
                counter++;
                value = queue.remove();
                i++;

                //ppmseries.appendData(new DataPoint(x_array[i++], value), false, 10);

            }
            try {
                Thread.sleep(42000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onStart() {
        super.onStart();

        Thread myThread = new Thread(myRunnable2);
        myThread.start();
        //graph.addSeries(ppmseries);



    }
}

