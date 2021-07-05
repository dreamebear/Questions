package QuestionSetA;

import java.util.Arrays;

public class LC1465_MaximumAreaCakeAfterHorizontalAndVerticalCuts {

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        // h w can be very large, which cause TLE
        // but size of horizontalCuts and horizontalCuts is not that large

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int preH = 0;
        long maxH = 0; // long avoid int overflow

        int preV = 0;
        long maxV = 0;

        for (int horizontalCut : horizontalCuts) {
            maxH = Math.max(maxH, horizontalCut - preH);
            preH = horizontalCut;
        }
        maxH = Math.max(maxH, h - horizontalCuts[horizontalCuts.length-1]);

        for (int verticalCut : verticalCuts) {
            maxV = Math.max(maxV, verticalCut - preV);
            preV = verticalCut;
        }
        maxV = Math.max(maxV, w - verticalCuts[verticalCuts.length-1]);

        return (int) ((maxH * maxV) % (1000000007));

    }
}
