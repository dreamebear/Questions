package QuestionSetA;

/*
You have a paper box with dividers for holding wine bottles.
The box is divided by m x n dividers into (m + 1) x (n + 1) cells. Assuming the depth of the box is 1, each cell has a volume of 1.

Now we want to remove a number of dividers. Find the largest space after removing the dividers.
Example 1:
Input:
n = 5 Number of dividers in the horizontal direction
m = 5 Number of dividers in the vertical direction
h = [2, 3] Horizontal dividers to remove
v = [3] Vertical dividers to remove
Output: 6
Explanation: We want to remove the 2nd and 3rd horizontal divider and the 3rd vertical divider. The largest space after removing the dividers has a volume of (4 - 1) * (4 - 2) * 1 = 6.

Time: O(N + M)
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StorageOptimization {

    public static void main(String[] args) {
        StorageOptimization test = new StorageOptimization();

        System.out.println(test.largestSpace(6, 6, new int[]{4}, new int[]{2})); //4
        System.out.println(test.largestSpace(3, 3, new int[]{2}, new int[]{2})); //4
        System.out.println(test.largestSpace(2, 2, new int[]{1}, new int[]{2})); //4
        System.out.println(test.largestSpace(3, 2, new int[]{1,2,3}, new int[]{1,2})); //12
        System.out.println(test.largestSpace(4, 3, new int[]{1,2,4}, new int[]{1,3})); //6

    }

    private int largestSpace(int numHorizontalDivider, int numVerticallDivider, int[] horizontalToRemove, int[] verticalToRemove) {

        Set<Integer> horizontalSet = new HashSet<>();
        for (int h : horizontalToRemove) {
            horizontalSet.add(h);
        }

        Set<Integer> verticalSet = new HashSet<>();
        for (int v : verticalToRemove) {
            verticalSet.add(v);
        }
        int preH = 0, maxH = 0;
        int preV = 0, maxV = 0;

        for (int i = 1; i <= numHorizontalDivider+1; i++) {
            if (!horizontalSet.contains(i)) {
                maxH = Math.max(maxH, i - preH);
                preH = i;
            }
        }

        for (int j = 1; j <= numVerticallDivider+1; j++) {
            if (!verticalSet.contains(j)) {
                maxV = Math.max(maxV, j - preV);
                preV = j;
            }
        }

        return maxH * maxV;
    }

    /*
    def storage(n, m, h, v):
        total_rows = n + 2
        total_column = m + 2
        row_set = set(i for i in range(total_rows))
        col_set = set(i for i in range(total_column))

        for removed_row in h:
            row_set.remove(removed_row)

        for removed_column in v:
            col_set.remove(removed_column)

        row_list = sorted(list(row_set))
        col_list = sorted(list(col_set))

        row_gap_max, col_gap_max = 0, 0

        for i in range(1, len(row_list)):
            row_gap_max = max(row_gap_max, row_list[i] - row_list[i - 1])

       for i in range(1, len(col_list)):
            col_gap_max = max(col_gap_max, col_list[i] - col_list[i - 1])

       return row_gap_max * col_gap_max
     */

    private void test() {
        Integer[] arr = { 2, 6, 4, 2, 3, 3, 1, 7 };
        Set<Integer> set = new HashSet<>(Arrays.asList(arr));
        System.out.println(set);

        int[] arr1 = { 2, 6, 4, 2, 3, 3, 1, 7 };
        Set<Integer> set1 = new HashSet<>();
        for (int v : arr1) {
            set1.add(v);
        }
        System.out.println(set1);
    }

}
