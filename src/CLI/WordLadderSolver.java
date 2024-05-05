package src.CLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.CLI.Algorithms.AStar;
import src.CLI.Algorithms.GreedyBFS;
import src.CLI.Algorithms.PathFindingAlgorithm;
import src.CLI.Algorithms.PathFindingAlgorithm.PathFindingResult;
import src.CLI.Algorithms.UCS;
import src.CLI.Utils.Dictionary;
import src.CLI.Utils.FileWriterTest;

public class WordLadderSolver {
    public static void Option() {
        System.out.println("Masukkan algoritma yang ingin digunakan: ");
        System.out.println("1. UCS");
        System.out.println("2. GreedyBFS");
        System.out.println("3. AStar");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java WordLadderSolver <dictionary_file>");
            System.exit(0);
        }

        String dictionaryFile = args[0];
        if (!dictionaryFile.endsWith(".txt")) {
            System.out.println("File dictionary harus berformat .txt");
            System.exit(0);
        }
        Dictionary dictionary = null;
        try {
			dictionary = new Dictionary(dictionaryFile);
		} catch (Exception e) {
			System.err.println("Error when reading file " + dictionaryFile + " for dictionary");
			System.exit(1);
		}


        try (Scanner scanner = new Scanner(System.in)) {
            String algorithm;
            String startWord;
            String endWord;

            // Get and validate start word
            System.out.print("Masukkan start word: ");
            startWord = scanner.nextLine().toLowerCase();
            while (!dictionary.isValidWord(startWord)) {
                System.out.println("Start word bukan kata bahasa Inggris yang valid.");
                System.out.print("Masukkan start word: ");
                startWord = scanner.nextLine().toLowerCase();
            }

            // Get and validate end word
            System.out.print("Masukkan end word: ");
            endWord = scanner.nextLine().toLowerCase();
            while (!dictionary.isValidWord(endWord)) {
                System.out.println("End word bukan kata bahasa Inggris yang valid.");
                System.out.print("Masukkan end word: ");
                endWord = scanner.nextLine().toLowerCase();
            }

            if (startWord.length() != endWord.length()) {
                System.out.println("Panjang kata tidak sama.");
                System.exit(0);
            }

            // Get and validate algorithm choice
            Option();
            algorithm = scanner.nextLine().toLowerCase();
            while (!validateAlgorithm(algorithm)) {
                System.out.println("Algoritma yang dimasukkan tidak valid.");
                Option();
                algorithm = scanner.nextLine().toLowerCase();
            }

            // Initialize solver objects
            PathFindingAlgorithm solver = createSolver(algorithm, startWord, endWord);
            if (solver == null) {
                System.out.println("Algoritma yang dimasukkan tidak valid.");
                return;
            }

            // Find and validate solution
            System.out.println("Mencari path dari " + startWord + " ke " + endWord + "...");
            try {
                // Measure execution time
                long startTime = System.nanoTime();

                // Find path
                PathFindingResult res = findPath(solver, startWord, endWord, dictionary);

                long endTime = System.nanoTime();
                double totalTime = (endTime - startTime) / 1e6;

                List<String> path = res.getPath();
                int counter = res.getCounter();

                // Print the number of nodes visited
                System.out.println("Jumlah node yang dikunjungi : " + counter);

                // Run time
                System.out.println("Execution time : " + totalTime + " ms");

                validateSolution(path);

                System.out.println("Path ditemukan:");
                for (String word : path) {
                    System.out.println(word);
                }

                // Want to save ?
                System.out.println("Apakah Anda ingin menyimpan path ke file? (y/n)");
                String save = scanner.nextLine().toLowerCase();
                while (!save.equals("y") && !save.equals("n")) {
                    System.out.println("Masukkan y atau n");
                    save = scanner.nextLine().toLowerCase();
                }

                if (save.equals("y")) {
                    System.out.print("Masukkan nama file: ");
                    String fileName = scanner.nextLine();
                    FileWriterTest.SaveResultTest((ArrayList<String>) path, fileName, (long) totalTime, counter, startWord,
                            endWord, algorithm);
                }else{
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    System.exit(0);
                }

            } catch (NoSolutionFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean validateAlgorithm(String algorithm) {
        try {
            Integer algorithmNumber = Integer.parseInt(algorithm);
            return algorithmNumber >= 1 && algorithmNumber <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static PathFindingAlgorithm createSolver(String algorithm, String startWord, String endWord) {
        switch (algorithm) {
            case "1":
                return new UCS();
            case "2":
                return new GreedyBFS();
            case "3":
                return new AStar();
            default:
                return null;
        }
    }

    private static PathFindingResult findPath(PathFindingAlgorithm solver, String startWord, String endWord, Dictionary dictionary)
            throws NoSolutionFoundException {
        if (solver != null) {
            return solver.findPath(startWord, endWord, dictionary);
        } else {
            throw new NoSolutionFoundException("Solver tidak valid.");
        }
    }

    private static void validateSolution(List<String> path) throws NoSolutionFoundException {
        if (path == null || path.isEmpty()) {
            throw new NoSolutionFoundException("Tidak ada solusi yang ditemukan.");
        }
    }
}

class NoSolutionFoundException extends Exception {
    public NoSolutionFoundException(String message) {
        super(message);
    }
}
