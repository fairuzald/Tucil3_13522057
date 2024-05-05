package com.tucilstima3.backend.Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import com.tucilstima3.backend.Utils.Dictionary;
import com.tucilstima3.backend.Utils.Node;

public class UCS implements PathFindingAlgorithm {
    @Override
    public PathFindingResult findPath(String startWord, String endWord, Dictionary dictionary) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        HashSet<String> visited = new HashSet<>();
        queue.add(new Node(startWord));
        int counter = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            String currentWord = currentNode.getWord();

            // Check if the currentNode word has been visited
            if (!visited.contains(currentWord)) {
                counter++;
                visited.add(currentWord);

                // goalNode found
                if (currentWord.equalsIgnoreCase(endWord)) {
                    return new PathFindingResult(Node.buildPath(currentNode), counter);
                }

                // Find neighbors of the currentNode word with different character at one
                // position
                ArrayList<String> neighbors = new ArrayList<>();

                for (int i = 0; i < currentWord.length(); i++) {
                    // Try to change the character to all possible characters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != currentWord.charAt(i)) {
                            String neighbor = currentWord.substring(0, i) + c + currentWord.substring(i + 1);
                            // Append the neighbor to the ArrayList if it is a valid word
                            if (dictionary.isValidWord(neighbor)) {
                                neighbors.add(neighbor);
                            }
                        }
                    }
                }

                // Search for neighbors of the currentNode word with different character at one
                // position
                for (String neighbor : neighbors) {
                    queue.add(new Node(neighbor, currentNode.getCost() + 1, currentNode));
                }
            }
        }
        return new PathFindingResult(null, counter); // No solution found
    }
}
