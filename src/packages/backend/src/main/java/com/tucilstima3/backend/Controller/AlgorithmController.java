package com.tucilstima3.backend.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tucilstima3.backend.Algorithm.PathFindingAlgorithm;
import com.tucilstima3.backend.Algorithm.PathFindingAlgorithm.PathFindingResult;
import com.tucilstima3.backend.Service.PathFindingService;
import com.tucilstima3.backend.Utils.Dictionary;
import com.tucilstima3.backend.Utils.PathRequest;
import com.tucilstima3.backend.Utils.PathResponse;

@RestController
public class AlgorithmController {

    private final PathFindingService pathFindingService;
    private final Dictionary dictionary;

    @Autowired
    public AlgorithmController(PathFindingService pathFindingService) {
        this.pathFindingService = pathFindingService;
        try {
            this.dictionary = new Dictionary("src/main/resources/dictionary.txt");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load dictionary");
        }
    }

    @PostMapping("/algorithm")
    public PathResponse findPath(
            @RequestParam(value = "method", defaultValue = "ucs") String method,
            @RequestBody PathRequest request) {
        try {
            PathFindingAlgorithm algorithm = pathFindingService.getAlgorithm(method);
            long startTime = System.nanoTime();
            PathFindingResult sol = algorithm.findPath(request.getStartWord(), request.getEndWord(), this.dictionary);
            long endTime = System.nanoTime();
            ArrayList<String> path = sol.getPath();
            int counter = sol.getCounter();
            double msruntime = (endTime - startTime) / 1e6;
            if(path == null) {
                return new PathResponse(null, msruntime, counter, "No path found");
            }
            return new PathResponse(path, msruntime, counter, "Path found");
        } catch (IllegalArgumentException e) {
            return new PathResponse(null, 0, 0, "Invalid algorithm method: " + method);
        }
    }
}
