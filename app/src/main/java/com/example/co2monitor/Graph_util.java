package com.example.co2monitor;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Graph_util implements Serializable {
    Queue<Integer> q_graph;
    public Graph_util(){
         q_graph= new LinkedBlockingQueue<>(360);
    }
    public boolean is_empty(){
        if (q_graph.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
