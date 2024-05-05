package src.CLI.Algorithms;

import java.util.HashSet;
import java.util.PriorityQueue;

import src.CLI.Utils.Dictionary;
import src.CLI.Utils.Node;

public class AStar implements PathFindingAlgorithm {
    @Override
    public PathFindingResult findPath(String startWord, String endWord, Dictionary dictionary) {
        HashSet<String> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startWord, Heuristic.getDistance(startWord, endWord)));

        int counter = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            String currentWord = currentNode.getWord();

            counter++;
            visited.add(currentWord);

            // Solution found
            if (currentWord.equalsIgnoreCase(endWord)) {
                return new PathFindingResult(Node.buildPath(currentNode), counter);
            }

            // Search for neighbors of the currentNode word with different character at one
            // position
            for (int i = 0; i < currentWord.length(); i++) {
                // Try to change the chdaracter to all possible characters
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != currentWord.charAt(i)) {
                        String neighbor = currentWord.substring(0, i) + c + currentWord.substring(i + 1);
                        // Append the neighbor to the list if it is a valid word and has not been
                        if (dictionary.isValidWord(neighbor) && !visited.contains(neighbor)) {
                            visited.add(neighbor);
                            int cost = currentNode.getCost() + endWord.length() +  Heuristic.getDistance(neighbor, endWord);
                            queue.add(new Node(neighbor, cost, currentNode));
                        }
                    }
                }
            }

        }

        return new PathFindingResult(null, counter); // No solution found
    }
}
