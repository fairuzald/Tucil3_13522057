package com.tucilstima3.backend;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmController {

    private final PathFindingService pathFindingService;

    @Autowired
    public AlgorithmController(PathFindingService pathFindingService) {
        this.pathFindingService = pathFindingService;
    }

    @PostMapping("/algorithm")
    public PathResponse findPath(
            @RequestParam(value = "method", defaultValue = "ucs") String method,
            @RequestBody PathRequest request) {
        try {

            PathFindingAlgorithm algorithm = pathFindingService.getAlgorithm(method);
            long startTime = System.nanoTime();
            Object[] sol =  algorithm.findPath(request.getStartWord(), request.getEndWord());
            @SuppressWarnings("unchecked")
            ArrayList<String> path = (ArrayList<String>) sol[0];
            int counter = (int) sol[1];
            long endTime = System.nanoTime();
            long msruntime = (endTime - startTime) / 1000000;
            return new PathResponse(path, msruntime, counter); // Use path and counter variables here
        } catch (IllegalArgumentException e) {
            return new PathResponse(null, 0, 0);
        }
    }
}
