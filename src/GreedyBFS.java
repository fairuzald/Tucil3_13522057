import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreedyBFS {
    private Set<String> visited;
    private String startWord;
    private String endWord;

    public GreedyBFS(String startWord, String endWord) {
        this.visited = new HashSet<>();
        this.startWord = startWord;
        this.endWord = endWord;
    }

    public List<String> findPath() {
        Node startNode = new Node(startWord, Heuristic.calculateDistance(startWord, endWord));
        Node currentNode = startNode;
        while (currentNode != null) {
            visited.add(currentNode.getWord());

            if (currentNode.getWord().equals(endWord)) {
                return buildPath(currentNode);
            }

            String neighbor = getNeighbors(currentNode.getWord());
            if (neighbor != null) {
                Node neighborNode = new Node(neighbor, Heuristic.calculateDistance(neighbor, endWord), currentNode);
                currentNode = neighborNode;
            }
        }

        return Collections.emptyList(); // No path found
    }

    private String getNeighbors(String word) {
        int minCost = Integer.MAX_VALUE;
        String minWord = null;
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if(word.charAt(i) == c) continue;

                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, c);

                String newWord = sb.toString();
                
                if(!Dictionary.isValidWord(newWord)) continue;

                if(visited.contains(newWord)) continue;

                visited.add(newWord);

                int cost = Heuristic.calculateDistance(newWord, endWord);
                if(cost < minCost) {
                    minCost = cost;
                    minWord = newWord;
                }
            }
        }
        return minWord;
    }

    private List<String> buildPath(Node node) {
        List<String> path = new ArrayList<>();
        Node current = node;
        while (current != null) {
            path.add(current.getWord());
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}
