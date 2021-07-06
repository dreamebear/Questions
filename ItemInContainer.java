package QuestionSetA;

/*
Given a string s consisting of items as "*" and closed compartments as an open and close "|",
an array of starting indices startIndices, and an array of ending indices endIndices,
determine the number of items in closed compartments within the substring between the two indices, inclusive. [start, end]

An item is represented as an asterisk ('*' = ascii decimal 42)
A compartment is represented as a pair of pipes that may or may not have items between them ('|' = ascii decimal 124).

Example:
s = '|**|*|*'
startIndices = [1, 1]
endIndices = [5, 6]

The string has a total of 2 closed compartments, one with 2 items and one with 1 item.

For the first pair of indices, (1, 5), the substring is '|**|*'. There are 2 items in a compartment.
For the second pair of indices, (1, 6), the substring is '|**|*|' and there are 2 + 1 = 3 items in compartments.
Both of the answers are returned in an array, [2, 3].

Function Description:
Complete the numberOfItems function in the editor below.
The function must return an integer array that contains the results for each of the startIndices[i] and endIndices[i] pairs.

numberOfItems has three parameters:
s: A string to evaluate
startIndices: An integer array, the starting indices.
endIndices: An integer array, the ending indices.

Constraints:
1 ≤ m, n ≤ 10^5
1 ≤ startIndices[i] ≤ endIndices[i] ≤ n
Each character of s is either '*' or '|'
 */

public class ItemInContainer {

    public static void main(String[] args) {
        ItemInContainer test = new ItemInContainer();

        /*
        s = '|**|*|*'
        startIndices = [1, 1]
        endIndices = [5, 6]

        Output: [2, 3]
         */
        System.out.println(test.numberOfItems("|**|*|*", new int[]{1,1}, new int[]{5,6}));

        /*
        Sample Case 0:
        s = '*|*|'
        startIndices = [1]
        endIndices = [3]

        Output: [0]
         */
        System.out.println(test.numberOfItems("*|*|", new int[]{1}, new int[]{3}));

        /*
        Sample Case 1:
        s = ''*|*|*|'
        startIndices = [1]
        endIndices = [6]

        Output: [2]
         */
        System.out.println(test.numberOfItems("*|*|*|", new int[]{1}, new int[]{6}));
    }

    private int[] numberOfItems(String str, int[] startIndices, int[] endIndices) {

        char[] arr = str.toCharArray();
        int length = arr.length;
        int[] res = new int[startIndices.length];
        int[] leftBoundIndex = new int[length];
        int[] rightBoundIndex = new int[length];
        int[] prefixSum = new int[length];

        int preLeftIndex = -1;
        int count = 0;
        int curSum = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] == '*') {
                if (preLeftIndex > -1) {
                    count++;
                }
            } else {
                // '|'
                if (preLeftIndex == -1) {
                    // first |
                    curSum = 0;
                } else {
                    curSum = count;
                }
                preLeftIndex = i;
            }

            prefixSum[i] = curSum;
            leftBoundIndex[i] = preLeftIndex;
        }

        int preRightIndex = -1;
        for (int i = length-1; i >= 0; i--) {
            if (arr[i] == '|') {
                preRightIndex = i;
            }
            rightBoundIndex[i] = preRightIndex;
        }

        printArray(prefixSum);
        printArray(leftBoundIndex);
        printArray(rightBoundIndex);

        for (int i = 0; i < startIndices.length; i++) {
            int startIndex = startIndices[i]-1;
            int endIndex = endIndices[i]-1;
            int leftBound = rightBoundIndex[startIndex];
            int rightBound = leftBoundIndex[endIndex];
            if (leftBound < 0 || rightBound < 0 || leftBound >= rightBound) {
                res[i] = 0;
            } else {
                res[i] = prefixSum[rightBound] - prefixSum[leftBound];
            }

        }
        printArray(res);
        return res;
    }

    private void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
