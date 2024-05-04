package src.CLI.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriterTest {
    public static void SaveResultTest(ArrayList<String> path, String fileName, long time, int counter, String startWord,
            String endWord, String algorithm) {
        try {
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }

            // Menambahkan path folder
            String filePath = "test/" + fileName;
            FileWriter fileWriter = new FileWriter(filePath);

            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("===========RESULT===========");
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
            printWriter.close();
            System.out.println("Path berhasil disimpan ke " + filePath);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan path ke file.");
        }
    }
}
