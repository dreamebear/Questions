package QuestionSetA;

/*
An Amazon Fulfillment Associate has a set of items that need to be packed into two boxes.

Given an integer array of the item weights (arr) to be packed, divide the item weights into two subsets, A and B,
for packing into the associated boxes, while respecting the following conditions:

The intersection of A and B is null.
The union A and B is equal to the original array.
The number of elements in subset Ais minimal.
The sum of A's weights is greater than the sum of B's weights.  sum(A) > sum(B)

Return the subset A in increasing order where the sum of A's weights is greater than the sum of B's weights.
If more than one subset A exists, return the one with the maximal total weight.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimalBoxWeight {

    public static void main(String[] args) {
        OptimalBoxWeight test = new OptimalBoxWeight();
        int[] res = test.maxWeightA(new int[]{5, 3, 2, 4, 1, 2}); // [4,5]
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }

    private int[] maxWeightA(int[] array) {
//        Arrays.sort(Integer[] array, Collections.reverseOrder());

        Arrays.sort(array);

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        List<Integer> possibleA = new ArrayList<>();
        int curSum = 0;

        for (int i = array.length-1; i >= 0; i--) {
            curSum += array[i];
            possibleA.add(array[i]);

            if (curSum > sum / 2) {
                break;
            }
        }

//        possibleA.sort(Collections.reverseOrder());
        int length = possibleA.size();
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[length-i-1] = possibleA.get(i);
        }
        return res;
    }
}
