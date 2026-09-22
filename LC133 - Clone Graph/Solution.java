import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Solution {
    // map val (id) → object, can thus track if we kave seen a Node before
    private Map<Integer,Node> seenMap = new HashMap<>();

    public Node cloneGraph(Node node) {

        // base case
        if (node == null) {
            return null;
        }

        // if Node seen before, return clone
        if (seenMap.containsKey(node.val)) {
            return seenMap.get(node.val);
        }

        // clone node with empty adjacency list, and store it before traversal
        Node clonedNode = new Node(node.val);
        seenMap.put(node.val, clonedNode);

        // iterate through original Node's neighbors
        for (int i=0; i < node.neighbors.size(); i++) {
            clonedNode.neighbors.add(cloneGraph(node.neighbors.get(i)));
        }

        return clonedNode;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}