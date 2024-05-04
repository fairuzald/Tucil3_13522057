package src.CLI.Utils;
import java.util.ArrayList;

public class Node implements Comparable<Node> {
    private int cost;
    private String word;
    private Node parent;

    public Node(String word, int cost) {
        this.word = word;
        this.cost = cost;
    }

    public Node(String word, int cost, Node parent) {
        this.word = word;
        this.cost = cost;
        this.parent = parent;
    }

    public Node(String word) {
        this.word = word;
        this.cost = 0;
    }

    public int getCost() {
        return cost;
    }

    public String getWord() {
        return word;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.getCost(), other.getCost());
    }

    // Build the path from the start node to the current node
    public static ArrayList<String> buildPath(Node node) {
        ArrayList<String> path = new ArrayList<>();
        Node current = node;
        while (current != null) {
            path.addFirst(current.getWord());
            current = current.getParent();
        }
        return path;
    }
}
