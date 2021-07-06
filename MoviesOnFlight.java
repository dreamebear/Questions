package QuestionSetA;

/*
Question:
You are on a flight and wanna watch two movies during this flight.
You are given List<Integer> movieDurations which includes all the movie durations.
You are also given the duration of the flight which is d in minutes.
Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).

Find the pair of movies with the longest total duration and return they indexes.
If multiple found, return the pair with the longest movie.

Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
Output: [0, 6]
Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215 is the maximum number within 220 (250min - 30min)
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MoviesOnFlight {

    public static void main(String[] args) {
        MoviesOnFlight test = new MoviesOnFlight();
        /*
        # [90, 85, 75, 60, 120, 150, 125], 250 | [0,6]
        # [90, 85, 75, 60, 120, 150, 125], 50 | []
        # [90, 85, 75, 60, 120, 150, 125], 220 | [3,6]
        # [10, 50, 60] , 120 | [0,2]
        # [90, 85, 75, 60, 120,110,110, 150, 125] , 250 | [5, 6]
         */
        test.printArray(test.movieDurations(new int[]{90, 85, 65, 60, 120, 150, 125}, 250));

        test.printArray(test.movieDurations(new int[]{90, 85, 75, 60, 120, 150, 125}, 250));// [0,6]
        test.printArray(test.movieDurations(new int[]{90, 85, 75, 60, 120, 150, 125}, 50));// []
        test.printArray(test.movieDurations(new int[]{90, 85, 75, 60, 120, 150, 125}, 220));// [3,6]

        test.printArray(test.movieDurations(new int[]{10, 50, 60}, 120));// [0,2]

        test.printArray(test.movieDurations(new int[]{90, 85, 75, 60, 120,110,110, 150, 125}, 120));// []


    }

    private int[] movieDurations(int[] movieDurations, int d) {

        int[] originalArray = Arrays.copyOf(movieDurations, movieDurations.length);
        System.out.println(originalArray);

        int[] movieDurationsCopy = new int[movieDurations.length];
        for (int i = 0; i < movieDurations.length; i++) {
            movieDurationsCopy[i] = movieDurations[i];
        }

        // {val: index}
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < movieDurations.length; i++) {
            map.put(movieDurations[i], i);
        }
        // find the largest 2 sum in movieDurations <= d-30

        int maxDuration = Integer.MIN_VALUE;
        int timeLeft = d-30;
        int[] res = new int[2];

        Arrays.sort(movieDurations);
        for (int i = movieDurations.length-1; i >= 1; i--) {
            int index = findLargestDurationSmallerOrEqualTo(movieDurations,timeLeft-movieDurations[i], 0, i-1);
//            System.out.println(index);
            if (index == -1) {
                continue;
            } else {

                // find a pair that sum is smaller or equal to timeLeft
                int movie1 = movieDurations[index];
                int movie2 = movieDurations[i];
                int curDuration = movieDurations[index] + movieDurations[i];
//                if (curDuration == maxDuration) {
//                    if (movieDurations[index] > movieDurationsCopy[res[0]] || movieDurations[i] > movieDurationsCopy[res[i]]){
//                        res[0] = map.get(movieDurations[index]);
//                        res[1] = map.get(movieDurations[i]);
//                    }
//
//                } else
                    if (curDuration > maxDuration) {
                    res[0] = map.get(movieDurations[index]);
                    res[1] = map.get(movieDurations[i]);
                    maxDuration = curDuration;
                }
            }
        }
        // if not exist, return what?
        return res;
    }

    private int findLargestDurationSmallerOrEqualTo(int[] array, int target, int start, int end) {

        while (start+1 < end) {
            int mid = start + (end - start)/2;
            if (array[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (array[end] <= target) {
            return end;
        } else if (array[start] <= target) {
            return start;
        }
        return -1;
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
