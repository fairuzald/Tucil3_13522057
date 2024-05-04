package com.tucilstima3.backend;

import java.util.ArrayList;

public class PathResponse {
    private ArrayList<String> path;
    private long runtime;
    private int counter;

    public PathResponse(ArrayList<String> path, long runtime, int counter) {
        this.path = path;
        this.runtime = runtime;
        this.counter = counter;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
