package com.example.co2monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.*;
import java.text.*;

public class PastActivity extends AppCompatActivity {



    private GraphView graph;
    private LineGraphSeries<DataPoint> ppmseries;

    Date Time = new Date();
    SimpleDateFormat time_am_pm = new SimpleDateFormat( "hh:mm aa"); // Formats the time as Hour:min am/pm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// Used in order to make a back arrow past_button to go back to the MainActivity

        graph = (GraphView) findViewById(R.id.graph);

        graph.getViewport().setScrollable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScrollableY(true); // enables vertical zooming and scrolling

        graph.getGridLabelRenderer().setNumHorizontalLabels(12); // 12 labels for the horizontal labels (planning to do 1 Data Point every 15 mins)
        graph.getGridLabelRenderer().setNumHorizontalLabels(10);// Sets the number of label on the x-axis

        //LineGraphSeries<DataPoint> ppmseries = new LineGraphSeries<>(points);
        //graph.addSeries(ppmseries);

/*        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() { // Properly formats the x-label of the graph
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    return time_am_pm.format(new Date ((long) value));
                }
                else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });


    }*/

}

// Section of code which makes the datapoints for the graph
/*
    private DataPoint[] getDataPoint(){
        DataPoint[] datapts = new DataPoint[] {
                new DataPoint( new Date().getTime(), MainActivity.getQ_graph().remove())// Testing for one data point. x value should be time and y value should be ppm readings
        };
        return datapts;
    }*/
}
