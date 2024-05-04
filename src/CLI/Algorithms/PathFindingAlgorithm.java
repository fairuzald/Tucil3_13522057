package src.CLI.Algorithms;

import java.util.List;

public interface PathFindingAlgorithm {
    public class PathFindingResult {
        private List<String> path;
        private int counter;
    
        public PathFindingResult(List<String> path, int counter) {
            this.path = path;
            this.counter = counter;
        }
    
        public List<String> getPath() {
            return path;
        }
    
        public int getCounter() {
            return counter;
        }
    }

    public PathFindingResult findPath(String startWord, String endWord);

}