package com.tucilstima3.backend.Utils;

import java.util.ArrayList;

public class PathResponse {
    private ArrayList<String> path;
    private double runtime;
    private int counter;
    private String message;

    public PathResponse(ArrayList<String> path, double runtime, int counter, String message) {
        this.path = path;
        this.runtime = runtime;
        this.counter = counter;
        this.message = message;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(double runtime) {
        this.runtime = runtime;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getMessage() {
        return message;
    }
}
