package com.tucilstima3.backend;

import org.springframework.stereotype.Service;

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
