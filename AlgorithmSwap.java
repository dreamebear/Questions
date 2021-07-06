package QuestionSetA;

/*
For the input array arr of size n do:
Try to find the smallest pair of indices 0 <= i < j <= n-1 such that arr[i] > arr[j].
Here smallest means usual alphabetical ordering of pairs, i.e. (i1, j1) < (i2, j2) if and only if i1 < i2 or (i1 = i2 and j1 <j2).
If there is no such pair, stop.
Otherwise, swap a[i] and a[j] and repeat finding the next pair.

The algorithm seems to be correct, but the question is how efficient is it?
Write a function that returns the number of swaps performed by the above algorithm.

For example, if the initial array is [5,1,4,2], then the algorithm first picks pair (5,1) and swaps it to produce array [1,5,4,2].
Next, it picks pair (5,4) and swaps it to produce array [1,4,5,2].
Next, pair (4,2) is picked and swapped to produce array [1,2,5,4],
and finally, pair (5,4) is swapped to produce the final sorted array [1,2,4,5],
so the number of swaps performed is 4.

Complete the function howManySwaps in the editor below.
The function should return an integer that denotes the number of swaps performed by the proposed algorithm on the input array.

The function has the following parameter(s):
arr: integer array of size n with all unique elements

Constraints:
1 <= n <= 10^5
1 <= arr[i] <= 10^9
all elements of arr are unique
 */

import java.util.Arrays;

public class AlgorithmSwap {

    public static void main(String[] args) {
        AlgorithmSwap test = new AlgorithmSwap();

        // 4
        System.out.println(test.howManySwaps2(new int[]{5,1,4,2}));

//        int[] arr = {7,1,2};
//        System.out.println(mergeSortAndCount(arr, 0, arr.length - 1));

        /*
        [7,1,2]
        2
         */
        System.out.println(test.howManySwaps2(new int[]{7,1,2}));
    }

    /*
    Algorithm:
    1. The idea is similar to merge sort, divide the array into two equal or almost equal halves in each step until the base case is reached.
    2. Create a function merge that counts the number of inversions when two halves of the array are merged, create two indices i and j, i is the index for the first half, and j is an index of the second half.
    if a[i] is greater than a[j], then there are (mid – i) inversions.
    because left and right subarrays are sorted, so all the remaining elements in left-subarray (a[i+1], a[i+2] … a[mid]) will be greater than a[j].

    3. Create a recursive function to divide the array into halves and find the answer by summing the number of inversions is the first half,
    the number of inversion in the second half and the number of inversions by merging the two.

    4. The base case of recursion is when there is only one element in the given half.
    5. Print the answer
     */

    private int howManySwaps2(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        return helper(arr, 0, arr.length-1);
    }

    private int helper(int[] arr, int start, int end) {
        int count = 0;

        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        count += helper(arr, start, mid);
        count += helper(arr, mid+1, end);

        count += merge(arr, start, mid, end);

        return count;


    }

    private int merge(int[] arr, int start, int mid, int end) {

        // merge
        int[] leftCopy = Arrays.copyOfRange(arr, start, mid+1);
        int[] rightCopy = Arrays.copyOfRange(arr, mid+1, end+1);

        int i = 0, j = 0, k = start;
        int swap = 0;

        while (i < leftCopy.length && j < rightCopy.length) {
            if (leftCopy[i] <= rightCopy[j]) {
                arr[k] = leftCopy[i];
                i++;
                k++;
            } else {
                arr[k] = rightCopy[j];
                j++;
                k++;
                swap += (mid+1) - (start+i);
            }
        }

        while (i < leftCopy.length)
            arr[k++] = leftCopy[i++];
        while (j < rightCopy.length)
            arr[k++] = rightCopy[j++];

        return swap;
    }



    private int howManySwaps1(int[] arr) {
        int n = arr.length;

        int inv_count = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] > arr[j])
                    inv_count++;

        return inv_count;
    }



    private int howManySwaps(int[] array) {

        int res = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    res++;
                    swap(array, i, j);
                }
            }
        }
        return res;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    private static int mergeAndCount(int[] arr, int l,
                                     int m, int r)
    {

        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }
        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
        return swaps;
    }

    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l,
                                         int r)
    {

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }

}
