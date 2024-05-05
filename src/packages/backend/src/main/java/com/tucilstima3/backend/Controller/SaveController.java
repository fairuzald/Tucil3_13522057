package com.tucilstima3.backend.Controller;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tucilstima3.backend.Model.SaveRequest;
import com.tucilstima3.backend.Model.SaveResponse;
import com.tucilstima3.backend.Utils.FileWriterTest; 

@RestController
public class SaveController {

    @PostMapping("/save")
    public ResponseEntity<SaveResponse> save(@RequestBody SaveRequest request) {
        // Extract data from the SaveRequest object
        String startWord = request.getStartWord();
        String endWord = request.getEndWord();
        long runtime = (long) request.getRuntime(); 
        int counter = request.getCounter();
        ArrayList<String> path = request.getPath();
        String algorithm = request.getMethod();
        String fileName = request.getFileName();
        
        // Save the results using FileWriterTest
        FileWriterTest.SaveResultTest(path, fileName, runtime, counter, startWord, endWord, algorithm);

        String message = "File saved successfully";
        SaveResponse response = new SaveResponse(message);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
