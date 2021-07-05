package QuestionSetA;

/*
Given 2 lists a and b.
Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value.
Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible.
Return a list of ids of selected elements. If no pair is possible, return an empty list.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimalUtilization {

    public static void main(String[] args) {

        OptimalUtilization test = new OptimalUtilization();
        // [[2, 1]]
//        List<int[]> res = test.maxShippingDist(new int[][]{new int[]{1,2}, new int[]{2,4}, new int[]{3,6}}, new int[][]{new int[]{1,2}}, 7);
//        printList(res);


        /*
        Input:a = [[1, 3], [2, 5], [3, 7], [4, 10]]
                b = [[1, 2], [2, 3], [3, 4], [4, 5]] target = 10
        Output: [[2, 4], [3, 2]]
         */
//        List<int[]> res1 = test.maxShippingDist(new int[][]{new int[]{1,3}, new int[]{2,5}, new int[]{3,7}, new int[]{4,10}},
//                new int[][]{new int[]{1,2}, new int[]{2,3}, new int[]{3,4}, new int[]{4,5}}, 10);
//        printList(res1);

        /*
        Input:
            a = [[1, 8], [2, 7], [3, 14]]
            b = [[1, 5], [2, 10], [3, 14]]
            target = 20

            Output: [[3, 1]]
         */
        List<int[]> res2 = test.maxShippingDist(new int[][]{new int[]{1,8}, new int[]{2,7}, new int[]{3,14}},
                new int[][]{new int[]{1,5}, new int[]{2,10}, new int[]{3,14}}, 20);
        printList(res2);

        /*
        Input:
            a = [[1, 8], [2, 15], [3, 9]]
            b = [[1, 8], [2, 11], [3, 12]]
            target = 20

            Output: [[1, 3], [3, 2]]
         */
//        List<int[]> res3 = test.maxShippingDist(new int[][]{new int[]{1,8}, new int[]{2,15}, new int[]{3,9}},
//                new int[][]{new int[]{1,8}, new int[]{2,11}, new int[]{3,12}}, 20);
//        printList(res3);

        // not exist
//        List<int[]> res4 = test.maxShippingDist(new int[][]{new int[]{1,8}, new int[]{2,15}, new int[]{3,9}},
//                new int[][]{new int[]{1,8}, new int[]{2,11}, new int[]{3,12}}, 15);
//        printList(res4);
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

    private List<int[]> maxShippingDist(int[][] a, int[][] b, int target) {
        List<int[]> res = new ArrayList<>();

        // sort A,B based on value
        Arrays.sort(a, (int[] pair1, int[] pair2) -> pair1[1] - pair2[1]);
        Arrays.sort(b, (int[] pair1, int[] pair2) -> pair1[1] - pair2[1]);

        int max = Integer.MIN_VALUE;

        for (int i = a.length-1; i >= 0; i--) {
            // a[i] = [index, val]
            int valA = a[i][1];
            int targetB = target - valA;
            int indexB = findIndexOfSmallerOrEqualToTarget(targetB, b);
            // if index != -1: 存在<= target的组合
            if (indexB > -1) {
                int curSum = valA + b[indexB][1];
                if (curSum >= max) {
                    res.add(new int[]{a[i][0], b[indexB][0]});
                    max = curSum;
                } else {
                    break;
                }
            }
        }

        return res;
    }

    private int findIndexOfSmallerOrEqualToTarget(int target, int[][] b) {

        int left = 0;
        int right = b.length-1;

        while (left+1 < right) {
            int mid = left + (right - left) / 2;
            if (b[mid][1] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (b[right][1] <= target) {
            return right;
        } else if (b[left][1] <= target) {
            return left;
        }
        return -1;
    }
}
