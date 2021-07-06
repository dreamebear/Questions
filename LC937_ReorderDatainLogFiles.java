package QuestionSetA;

import java.util.Arrays;

/*
You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.

There are two types of logs:
Letter-logs: All words (except the identifier) consist of lowercase English letters.
Digit-logs: All words (except the identifier) consist of digits.
Reorder these logs so that:

The letter-logs come before all digit-logs.
The letter-logs are sorted lexicographically by their contents.
If their contents are the same, then sort them lexicographically by their identifiers.

The digit-logs maintain their relative ordering.

Return the final order of the logs.
 */

public class LC937_ReorderDatainLogFiles {

    public static void main(String[] args) {
        LC937_ReorderDatainLogFiles test = new LC937_ReorderDatainLogFiles();

        /*
        Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
        Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
         */
        String[] logs1 = new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        printStringArray(test.reorderLogFiles(logs1));

        /*
        Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
        Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
         */
        String[] logs2 = new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
        printStringArray(test.reorderLogFiles(logs2));
    }

    private String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (str1, str2) -> {
            String[] arr1 = str1.split(" ", 2); // arr1 = ["dig1", "8 1 5 1"]
            String[] arr2 = str2.split(" ", 2); // arr2 = ["let1", "art can"]

            boolean isDigit1 = Character.isDigit(arr1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(arr2[1].charAt(0));

            // 4 conditions
            // str1 & str2 both digit: keep relative order => 0
            // str1 & str2 both letter: compare(arr1[1], arr2[1]; if==0: compare(arr1[0], arr2[0]
            // str1 is digit & str2 is letter: str2 < str1
            // str1 is letter & str2 is digit: str1 < str2

            if (isDigit1 && isDigit2) {
                return 0;
            } else if (!isDigit1 && isDigit2) {
                return -1;
            } else if (isDigit1 && !isDigit2) {
                return 1;
            } else {
                int letterCompare = arr1[1].compareTo(arr2[1]);
                return letterCompare == 0 ? arr1[0].compareTo(arr2[0]) : arr1[1].compareTo(arr2[1]);
            }
        });

        return logs;
    }

    private static void printStringArray(String[] reorderLogFiles) {
        System.out.print("[");
        for (String word : reorderLogFiles) {
            System.out.print("\"");
            System.out.print(word);
            System.out.print("\", ");
        }
        System.out.print("]");
        System.out.println();
    }
}
