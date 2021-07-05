package QuestionSetA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
You have a graph of n nodes.
You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.
 */
public class LC323_NumberOfConnectedComponentsinUndirectedGraph {

    public static void main(String[] args) {

    }

    public int countComponents(int n, int[][] edges) {

        int res = 0;

        // convert to graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        // dfs traverse
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                res++;
                dfs(i, graph, visited);
            }
        }
        return res;
    }

    private void dfs(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited);
            }
        }
    }


/*
T: O(E+V)
S: O(E+V)
*/
}


