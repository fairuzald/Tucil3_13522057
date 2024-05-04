public class Node implements Comparable<Node> {
    private int cost;
    private String word;
    private Node parent;
    private int heuristicCost;

    public Node(String word, int cost) {
        this.word = word;
        this.cost = cost;
        this.heuristicCost = 0;
    }

    public Node(String word, int cost, Node parent) {
        this.word = word;
        this.cost = cost;
        this.parent = parent;
        this.heuristicCost = 0;
    }

    public Node(String word) {
        this.word = word;
        this.cost = 0;
        this.heuristicCost = 0;
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

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public int getTotalCost() {
        return cost + heuristicCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.getTotalCost(), other.getTotalCost());
    }
}
