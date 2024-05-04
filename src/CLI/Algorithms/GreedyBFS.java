package src.CLI.Algorithms;

import java.util.HashSet;

import src.CLI.Utils.Dictionary;
import src.CLI.Utils.Node;

public class GreedyBFS implements PathFindingAlgorithm {

    @Override
    public PathFindingResult findPath(String startWord, String endWord) {
        Node currentNode = new Node(startWord, Heuristic.getDistance(startWord, endWord));
        HashSet<String> visited = new HashSet<>();
        int counter = 0;

        // Search until the end word is found
        while (currentNode != null) {
            counter++;
            String currentWord = currentNode.getWord();

            // If the current word is the end word, return the path
            if (currentWord.equalsIgnoreCase(endWord)) {
                return new PathFindingResult(Node.buildPath(currentNode), counter);
            }

            int minimumCost = Integer.MAX_VALUE;
            String greedyWord = null;

            // Find the neighbor with the minimum cost
            for (int i = 0; i < currentWord.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (currentWord.charAt(i) != c) {
                        // Replace the character at index i with c
                        String newWord = currentWord.substring(0, i) + c + currentWord.substring(i + 1);
                        // Check if the new word is a valid English word and has not been visited
                        if (Dictionary.isValidWord(newWord) && !visited.contains(newWord)) {
                            visited.add(newWord);
                            // Calculate the cost of the new word
                            int cost = Heuristic.getDistance(newWord, endWord);
                            if (cost <= minimumCost) {
                                minimumCost = cost;
                                greedyWord = newWord;
                            }
                        }
                    }
                }
            }

            if (greedyWord != null) {
                Node neighborNode = new Node(greedyWord, minimumCost, currentNode);
                currentNode = neighborNode;
            } else {
                currentNode = null;
                break;
            }
        }

        return new PathFindingResult(null, counter);

    }
}
