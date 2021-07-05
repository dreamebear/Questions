package QuestionSetA;

/*
Amazon is trying to understand customer shopping patterns and offer items that are regularly bought together to new customers.
Each item that has been bought together can be represented as an undirected graph where edges join often bundled products.

A group of n products is uniquely numbered from 1 of product_nodes.
A trio is defined as a group of three related products that all connected by an edge.
Trios are scored by counting the number of related products outside of the trio, this is referred as a product sum.
Given product relation data, determine the minimum product sum for all trios of related products in the group.
If no such trio exists, return -1.



Function Description Complete the function getMinScore in the editor below.

getMinScore has the following parameter(s):
int products_nodes: the total number of products
int products_edges the total number of edges representing related products
int products_from[products_nodes]: each element is a node of one side of an edge.
int products_to[products edges]: each products_to[i] is a node connected to products_from[i]

Returns:
int: the minimum product sum for all trios of related products in the group. If no such trio exists, return -1.

Constraints
1 <= products_nodes <= 500
1 <= products_edges <= min(500, (products_nodes * (products_nodes - 1)) / 2)
1 <= products_from[i], products to[i]


products_nodes = 6
products_edges = 6
products_from = [1,2,2,3,4,5]
products_to =   [2,4,5,5,5,6]
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShoppingPatterns {

    public static void main(String[] args) {
        ShoppingPatterns test = new ShoppingPatterns();
        System.out.println(test.getMinScore(6,6, new int[]{1,2,2,3,4,5}, new int[]{2,4,5,5,5,6})); // 3
        System.out.println(test.getMinScore(5,6, new int[]{1,1,2,2,3,4}, new int[]{2,3,3,4,4,5})); // 2
        System.out.println(test.getMinScore(6,6, new int[]{1,1,2,1,2,3}, new int[]{2,3,3,4,5,6})); // 3
        System.out.println(test.getMinScore(7,8, new int[]{1,1,3,2,5,6,5,2}, new int[]{3,4,4,5,6,7,7,6})); // 0

    }

    private int getMinScore(int products_nodes, int products_edges, int[] products_from, int[] products_to) {
        Integer res = Integer.MAX_VALUE;

        // convert to graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < products_from.length; i++) {
            int node1 = products_from[i];
            int node2 = products_to[i];
            addToGraph(graph, node1, node2);
            addToGraph(graph, node2, node1);
        }

        // edge = products_from[i] - products_to[i]
        System.out.println(graph);

        Set<Integer> visitedNodes = new HashSet<>();
        // find trio
        // iterate edge: find if two nodes have same neighbor -> trio
        for (int i = 0; i < products_from.length; i++) {
            int node1 = products_from[i];
            int node2 = products_to[i];

            Set<Integer> neighbors1 = graph.getOrDefault(node1, new HashSet<>());
            Set<Integer> neighbors2 = graph.getOrDefault(node2, new HashSet<>());


            for (int neighbor : neighbors1) {
                if (neighbors2.contains(neighbor)) {
//                    System.out.println("node1 " + node1);
//                    System.out.println("node2 " + node2);
                    // find trio

                    int degrees = neighbors1.size() + neighbors2.size() + graph.getOrDefault(neighbor, new HashSet<>()).size() - 6;
                    res = Math.min(res, degrees);

                    /*
                    flag = true;
                    // wrong: find all outsides
                    if (!visitedNodes.contains(node1)) {
                        degrees += neighbors1.size() - 2;
                        visitedNodes.add(node1);
                    }
                    if (!visitedNodes.contains(node2)) {
                        degrees += neighbors2.size() - 2;
                        visitedNodes.add(node2);
                    }

                     */
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void addToGraph(Map<Integer, Set<Integer>> graph, int node1, int node2) {
        Set<Integer> neighbors = graph.getOrDefault(node1, new HashSet<>());
        neighbors.add(node2);
        graph.put(node1, neighbors);
    }
}
