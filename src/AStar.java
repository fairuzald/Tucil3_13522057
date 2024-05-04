import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {
    private PriorityQueue<Node> queue;
    private Set<String> visited;
    private String startWord;
    private String endWord;

    public AStar(String startWord, String endWord) {
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        this.visited = new HashSet<>();
        this.startWord = startWord;
        this.endWord = endWord;
    }

    public List<String> findPath() {
        queue.add(new Node(startWord));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.getWord().equals(endWord)) {
                // Solution found
                return buildPath(current);
            }

            // Search for neighbors of the current word with different character at one
            // position
            for (String neighbor : getNeighbors(current.getWord())) {
                queue.add(new Node(neighbor, current.getCost() + 1, current));
            }
        }

        return null; // No solution found
    }

    private List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            // Try to change the character to all possible characters
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    chars[i] = c;
                    String neighbor = new String(chars);
                    // Append the neighbor to the list if it is a valid word and has not been
                    if (Dictionary.isValidWord(neighbor) && !visited.contains(neighbor)) {
                        neighbors.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            // Restore the original character
            chars[i] = originalChar;
        }

        return neighbors;
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
