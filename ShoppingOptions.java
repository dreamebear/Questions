package QuestionSetA;

/*
A customer wants to buy a pair of jeans, a pair of shoes, a skirt, and a top but has a limited budget in dollars.
Given different pricing options for each product, determine how many options our customer has to buy 1 of each product.
You cannot spend more money than the budgeted amount.

Example:
priceOfJeans = [2, 3]
priceOfShoes = [4]
priceOfSkirts = [2, 3]
priceOfTops = [1, 2]
budgeted = 10

The customer must buy shoes for 4 dollars since there is only one option.
This leaves 6 dollars to spend on the other 3 items.
Combinations of prices paid for jeans, skirts, and tops respectively that add up to 6 dollars or less are [2, 2, 2], [2, 2, 1], [3, 2, 1], [2, 3, 1].
There are 4 ways the customer can purchase all 4 items.

Function Description:
Complete the getNumberOfOptions function in the editor below.
The function must return an integer which represents the number of options present to buy the four items.

getNumberOfOptions has 5 parameters:
int[] priceOfJeans: An integer array, which contains the prices of the pairs of jeans available.
int[] priceOfShoes: An integer array, which contains the prices of the pairs of shoes available.
int[] priceOfSkirts: An integer array, which contains the prices of the skirts available.
int[] priceOfTops: An integer array, which contains the prices of the tops available.
int dollars: the total number of dollars available to shop with.

Constraints:
1 ≤ a, b, c, d ≤ 103
1 ≤ dollars ≤ 109
1 ≤ price of each item ≤ 109
Note: a, b, c and d are the sizes of the four price arrays
 */

import java.util.ArrayList;
import java.util.List;

public class ShoppingOptions {
    public static void main(String[] args) {

        ShoppingOptions test = new ShoppingOptions();
        System.out.println(test.getNumberOfOptions(new int[]{3,2}, new int[]{4}, new int[]{2,3}, new int[]{1,2}, 10)); // 4
        System.out.println(test.getNumberOfOptions(new int[]{3,2}, new int[]{4}, new int[]{2,3}, new int[]{1,2}, 9)); // 1
        System.out.println(test.getNumberOfOptions(new int[]{3,2}, new int[]{4}, new int[]{2,3}, new int[]{1,2}, 8)); // 0

        System.out.println(test.getNumberOfOptions(new int[]{6}, new int[]{1,1,1,1}, new int[]{4,5,6}, new int[]{1}, 12)); // 4
        System.out.println(test.getNumberOfOptions(new int[]{6}, new int[]{1,1,1,1}, new int[]{4,5,6}, new int[]{1}, 13)); // 8
        System.out.println(test.getNumberOfOptions(new int[]{6}, new int[]{1,1,1,1}, new int[]{4,5,6}, new int[]{1}, 14)); // 12
        System.out.println(test.getNumberOfOptions(new int[]{100}, new int[]{1,1,1,1}, new int[]{4,5,6}, new int[]{1}, 99)); // 0

        System.out.println(test.getNumberOfOptions(new int[]{1}, new int[]{1}, new int[]{1}, new int[]{1}, 4)); // 1
        System.out.println(test.getNumberOfOptions(new int[]{1}, new int[]{1}, new int[]{1}, new int[]{1}, 3)); // 0
    }

    private int getNumberOfOptions(int[] priceOfJeans, int[] priceOfShoes, int[] priceOfSkirts, int[] priceOfTops, int dollars) {

        int res =  0;
        List<Integer> possibleJeanShoeComb = new ArrayList<>();
        List<Integer> possibleSkirtTopComb = new ArrayList<>();

        // O(ab)
        for (int i = 0; i < priceOfJeans.length; i++) {
            for (int j = 0; j < priceOfShoes.length; j++) {
                if (priceOfJeans[i] + priceOfShoes[j] < dollars) {
                    possibleJeanShoeComb.add(priceOfJeans[i] + priceOfShoes[j]);
                }
            }
        }

        // O(cd)
        for (int i = 0; i < priceOfSkirts.length; i++) {
            for (int j = 0; j < priceOfTops.length; j++) {
                if (priceOfSkirts[i] + priceOfTops[j] < dollars) {
                    possibleSkirtTopComb.add(priceOfSkirts[i] + priceOfTops[j]);
                }
            }
        }

        possibleJeanShoeComb.sort(Integer::compareTo);  // O(ab log(ab))
        possibleSkirtTopComb.sort(Integer::compareTo); // O(cd log(cd))

        for (int i = 0; i < possibleJeanShoeComb.size(); i++) {
            int target = dollars - possibleJeanShoeComb.get(i);
            int index = findLargetIndexSmallerOrEqualToTarget(target, possibleSkirtTopComb);
            if (index == -1) {
                break;
            } else {
                res += index+1;
            }
        }

        return res;
    }

    private int findLargetIndexSmallerOrEqualToTarget(int target, List<Integer> possibleSkirtTopComb) {
        int left = 0;
        int right = possibleSkirtTopComb.size()-1;
        while (left+1 < right) {
            int mid = left + (right - left) / 2;
            if (possibleSkirtTopComb.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (possibleSkirtTopComb.get(right) <= target) {
            return right;
        } else if (possibleSkirtTopComb.get(left) <= target) {
            return left;
        }
        return -1;
    }
}
