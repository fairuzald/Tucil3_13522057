package com.tucilstima3.backend.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriterTest {
    public static void SaveResultTest(ArrayList<String> path, String fileName, long time, int counter, String startWord,
                                      String endWord, String algorithm) {
        try {
            // Append ".txt" extension if not present in the fileName
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }

            // Construct the file path
            String filePath = fileName;

            // Create FileWriter and PrintWriter
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Write results to the file
            printWriter.println("=========== RESULT ===========");
            printWriter.println();
            switch (algorithm) {
                case "1":
                    printWriter.println("Algorithm: UCS");
                    break;
                case "2":
                    printWriter.println("Algorithm: Greedy BFS");
                    break;
                case "3":
                    printWriter.println("Algorithm: A*");
                    break;
                default:
                    break;
            }
            printWriter.println("Start Word: " + startWord);
            printWriter.println("End Word: " + endWord);
            printWriter.println("Time: " + time + " ms");
            printWriter.println("Node visited: " + counter);
            printWriter.println("Path Length: " + path.size());
            printWriter.println("Path: ");
            for (String word : path) {
                printWriter.println(word);
            }

            // Close the PrintWriter
            printWriter.close();
            System.out.println("Path berhasil disimpan ke " + filePath);
        } catch (IOException e) {
            // Handle IO exception
            System.out.println("Gagal menyimpan path ke file: " + e.getMessage());
        }
    }
}
