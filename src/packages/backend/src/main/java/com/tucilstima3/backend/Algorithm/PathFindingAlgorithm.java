package com.tucilstima3.backend.Algorithm;

import java.util.ArrayList;

import com.tucilstima3.backend.Utils.Dictionary;

public interface PathFindingAlgorithm {
    public class PathFindingResult {
        private ArrayList<String> path;
        private int counter;

        public PathFindingResult(ArrayList<String> path, int counter) {
            this.path = path;
            this.counter = counter;
        }

        public ArrayList<String> getPath() {
            return path;
        }

        public int getCounter() {
            return counter;
        }
    }

     public PathFindingResult findPath(String startWord, String endWord, Dictionary dictionary);


}