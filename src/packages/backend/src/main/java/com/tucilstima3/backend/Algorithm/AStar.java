package com.tucilstima3.backend.Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import com.tucilstima3.backend.Utils.Dictionary;
import com.tucilstima3.backend.Utils.Node;

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

            if (!visited.contains(currentWord)) {
                counter++;
                visited.add(currentWord);

                // Solution found
                if (currentWord.equalsIgnoreCase(endWord)) {
                    return new PathFindingResult(Node.buildPath(currentNode), counter);
                }

                // Search for neighbors of the currentNode word with different character at one
                // position
                List<String> neighbors = new ArrayList<>();

                for (int i = 0; i < currentWord.length(); i++) {
                    // Try to change the character to all possible characters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != currentWord.charAt(i)) {
                            String neighbor = currentWord.substring(0, i) + c + currentWord.substring(i + 1);
                            // Append the neighbor to the list if it is a valid word and has not been
                            if (dictionary.isValidWord(neighbor) && !visited.contains(neighbor)) {
                                neighbors.add(neighbor);
                            }
                        }
                    }
                }

                for (String neighbor : neighbors) {
                    int cost = currentNode.getCost() + 1 + Heuristic.getDistance(neighbor, endWord);
                    queue.add(new Node(neighbor, cost, currentNode));
                }
            }
        }

        return new PathFindingResult(null, counter); // No solution found
    }
}
