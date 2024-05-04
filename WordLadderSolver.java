import java.util.List;
import java.util.Scanner;

public class WordLadderSolver {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String algorithm;
            String startWord;
            String endWord;

            // Get and validate start word
            System.out.print("Masukkan start word: ");
            startWord = scanner.nextLine().toLowerCase();
            while (!Dictionary.isValidWord(startWord)) {
                System.out.println("Start word bukan kata bahasa Inggris yang valid.");
                System.out.print("Masukkan start word: ");
                startWord = scanner.nextLine().toLowerCase();
            }

            // Get and validate end word
            System.out.print("Masukkan end word: ");
            endWord = scanner.nextLine().toLowerCase();
            while (!Dictionary.isValidWord(endWord)) {
                System.out.println("End word bukan kata bahasa Inggris yang valid.");
                System.out.print("Masukkan end word: ");
                endWord = scanner.nextLine().toLowerCase();
            }

            if (startWord.length() != endWord.length()) {
                System.out.println("Panjang kata tidak sama.");
                System.exit(0);
            }

            // Get and validate algorithm choice
            System.out.println("Masukkan algoritma yang ingin digunakan: ");
            System.out.println("1. UCS");
            System.out.println("2. GreedyBFS");
            System.out.println("3. AStar");
            algorithm = scanner.nextLine().toLowerCase();
            while (!validateAlgorithm(algorithm)) {
                System.out.println("Algoritma yang dimasukkan tidak valid.");
                System.out.println("Masukkan algoritma yang ingin digunakan: ");
                System.out.println("1. UCS");
                System.out.println("2. GreedyBFS");
                System.out.println("3. AStar");
                algorithm = scanner.nextLine().toLowerCase();
            }

            // Initialize solver objects
            Object solver = createSolver(algorithm, startWord, endWord);
            if (solver == null) {
                System.out.println("Algoritma yang dimasukkan tidak valid.");
                return;
            }

            // Find and validate solution
            System.out.println("Mencari path dari " + startWord + " ke " + endWord + "...");
            long startTime = System.nanoTime();
            try {
                List<String> path = findPath(solver);
                validateSolution(path);

                System.out.println("Path ditemukan:");
                path.forEach(System.out::println);
            } catch (NoSolutionFoundException e) {
                System.out.println(e.getMessage());
            }
            long endTime = System.nanoTime();
            double totalTime = (endTime - startTime) / 1e6;
            System.out.println("Execution time in ms  : " + totalTime);
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

    private static Object createSolver(String algorithm, String startWord, String endWord) {
        switch (algorithm) {
            case "1":
                return new UCS(startWord, endWord);
            case "2":
            System.out.println("GreedyBFS");
                return new GreedyBFS(startWord, endWord);
            case "3":
            return new AStar(startWord, endWord);
            default:
                return null; // Return null for invalid algorithm
        }

    }

    private static List<String> findPath(Object solver) throws NoSolutionFoundException {
        if (solver instanceof UCS) {
            return ((UCS) solver).findPath(); 
        } else if (solver instanceof GreedyBFS) {
            return ((GreedyBFS) solver).findPath(); }
        else if (solver instanceof AStar) {
            return ((AStar) solver).findPath(); 
        
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

class InputValidationException extends Exception {
    public InputValidationException(String message) {
        super(message);
    }
}

class NoSolutionFoundException extends Exception {
    public NoSolutionFoundException(String message) {
        super(message);
    }
}
