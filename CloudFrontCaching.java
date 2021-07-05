package QuestionSetA;

/*
AWS CloudFront wants to build an algo to measure the efficiency of its caching network.
The network is represented as a number of nodes and a list of connected pairs. =>graph: int numOfNodes; List<edge>;

The efficiency of this network can be estimated by first summing the cost of each isolated set of nodes where each individual node has a cost of 1.
To account for the increase in efficiency as more nodes are connected, update the cost of each isolated set to be the ceiling of the square root of the original cost and return the final sum of all costs.

Example:
n = 10 nodes
edges = [[1 2] , [1 3] , [2 4] , [3 5] , [7 8]]

There are 2 isloated sets with more than one node {1,2,3,4,5} and {7,8}. The ceilings of their square roots are:

5^1/2 = 2.236 and ceil(2.236) = 3
2^1/2 = 1.414 and ceil(1.414) = 2

The other three isolated nodes are separate and the square root of their weights is 1^1/2 = 1 respectively.

The sum is 3+2+(3*1) = 8

Function Description
Complete the function connectedSum in the editor below:

connectedSum has the following parameter(s):
int n: the number of nodes str
edges[m]: an array of strings that consist of a space-separated integer pair that denotes two connected nodes, p and q

Returns:
int: an integer that denotes the sum of the values calculated

Constraints:
2 <= n <= 10^5
1 <= m <=10^5
1 <= p,q <= n
p != n
 */


import java.util.*;

public class CloudFrontCaching {

    public static void main(String[] args) {
        CloudFrontCaching test = new CloudFrontCaching();

        /*
        n = 10 nodes
        edges = [[1 2] , [1 3] , [2 4] , [3 5] , [7 8]]

        The sum is 3+2+(3*1) = 8
         */
//        System.out.println(test.connectedSum(10, new int[][]{new int[]{1,2}, new int[]{1,3}, new int[]{2,4}, new int[]{3,5}, new int[]{7,8}}));
        List<String> edges1 = new ArrayList<>();
        edges1.add("1 2");
        edges1.add("1 3");
        edges1.add("2 4");
        edges1.add("3 5");
        edges1.add("7 8");
        System.out.println(test.connectedSum(10, edges1));

        /*
        Sample Input 0:
            n = 4 nodes
            edges[] size m = 2
            edges[] = [[1 2], [1 4]]

        Sample Output 0:
            3
         */
//        System.out.println(test.connectedSum(4, new int[][]{new int[]{1,2}, new int[]{1,4}}));
        List<String> edges2 = new ArrayList<>();
        edges2.add("1 2");
        edges2.add("1 4");
        System.out.println(test.connectedSum(4, edges2));

        /*
        Sample Input 1:
            n = 8 nodes
            edges[] size m = 4
            edges[] = [[8 1], [5 8], [7 3], [8 6]]

        Sample Output 1:
            6
         */
//        System.out.println(test.connectedSum(8, new int[][]{new int[]{8,1}, new int[]{5,8}, new int[]{7,3}, new int[]{8,6}}));
        List<String> edges3 = new ArrayList<>();
        edges3.add("8 1");
        edges3.add("5 8");
        edges3.add("7 3");
        edges3.add("8 6");
        System.out.println(test.connectedSum(8, edges3));
    }

    private int connectedSum(int n, List<String> edgesString) {

        int res = 0;

        // convert List<String> to List<int[]>
        List<int[]> edges = new ArrayList<>();
        for (String edge : edgesString) {
            String[] str = edge.split(" ");
            int node1 = Integer.parseInt(str[0]);
            int node2 = Integer.parseInt(str[1]);
            edges.add(new int[]{node1, node2});
        }

        // convert to graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashSet<>());
        }

        // T: O(E)
        for (int i = 0; i < edges.size(); i++) {
            graph.get(edges.get(i)[0]).add(edges.get(i)[1]);
            graph.get(edges.get(i)[1]).add(edges.get(i)[0]);
        }
//        System.out.println(graph);

        Set<Integer> visited = new HashSet<>();
        // T: O(V)
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                int numConnected = dfs(i, graph, visited);
                res += Math.ceil(Math.sqrt(numConnected));
            }
        }

        return res;
    }


    private int connectedSum(int n, int[][] edges) {
        int res = 0;

        // convert to graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashSet<>());
        }

        // T: O(E)
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
//        System.out.println(graph);

        Set<Integer> visited = new HashSet<>();
        // T: O(V)
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                int numConnected = dfs(i, graph, visited);
                res += Math.ceil(Math.sqrt(numConnected));
            }
        }

        return res;
    }

    private int dfs(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visited) {
        int count = 1; // node itself
        visited.add(node);

        // dfs traverse all neighbors of node
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                count += dfs(neighbor, graph, visited);
            }
        }
        return count;
    }

    private static void printList(List<int[]> res) {
        System.out.print("[");
        for (int i = 0; i < res.size(); i++) {
            System.out.print("[");
            for (int j = 0; j < res.get(i).length; j++) {
                System.out.print(res.get(i)[j] + " ");
            }
            System.out.print("], ");
        }
        System.out.print("]");
        System.out.println();
    }
}

/*
Here E = Number of edges, V = Number of vertices.

Time complexity: O(E+V).
Building the adjacency list will take O(E) operations, as we iterate over the list of edges once, and insert each edge into two lists.
During the DFS traversal, each vertex will only be visited once.
This is because we mark each vertex as visited as soon as we see it, and then we only visit vertices that are not marked as visited.
In addition, when we iterate over the edge list of each vertex, we look at each edge once.

This has a total cost of O(E+V).

Space complexity: O(E+V).
Building the adjacency list will take O(E) space.
To keep track of visited vertices, an array of size O(V) is required.
Also, the run-time stack for DFS will use O(V) space.
 */
