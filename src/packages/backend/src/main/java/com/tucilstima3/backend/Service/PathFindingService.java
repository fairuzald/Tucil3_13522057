package com.tucilstima3.backend.Service;

import org.springframework.stereotype.Service;

import com.tucilstima3.backend.Algorithm.AStar;
import com.tucilstima3.backend.Algorithm.GreedyBFS;
import com.tucilstima3.backend.Algorithm.PathFindingAlgorithm;
import com.tucilstima3.backend.Algorithm.UCS;

@Service
public class PathFindingService {

    public PathFindingAlgorithm getAlgorithm(String method) {
        switch (method) {
            case "ucs":
                return new UCS();
            case "greedy":
                return new GreedyBFS();
            case "astar":
                return new AStar();
            default:
                throw new IllegalArgumentException("Invalid algorithm method: " + method);
        }
    }
}
