package com.company;

public class Kadane {

    // https://www.puzzlr.org/understanding-kadanes-algorithm-maximum-subarray-problem/

    public void maxSumNoIndex(){
        int[] a = {-2, 1, 3,-1, 4, -20};
        System.out.println("Max is : " + maxSubArray(a));
    }

    public void maxSumWithIndex(){
        int[] a = {-2, -1, -3,-1, 4, 20};
        int[] result = maxSubArrayIndexes(a);
        System.out.println("Start index : " + result[0]);
        System.out.println("End index : " + result[1]);
        System.out.println("Max : " + result[2]);
    }

    // no indexes
    private int maxSubArray(int[] a){
        int max_ending_here = a[0];
        int max_so_far = a[0];

        for(int i = 1; i < a.length; i++){
            max_ending_here = Math.max(a[i], max_ending_here + a[i]);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }

        return max_so_far;
    }

    private int[] maxSubArrayIndexes(int[] a){
        int max_ending_here = a[0];
        int max_so_far = a[0];
        int start = 0, end = 0;

        for(int i = 1; i < a.length; i++){
            // still wanna get meh = mx(x, meh + x)
            int temp_max_ending_here = max_ending_here + a[i];

            if(temp_max_ending_here > a[i]){
                max_ending_here = temp_max_ending_here;
            }
            else {
                max_ending_here = a[i];
                start = i;
                end = i;
            }

            // still wanna get msf = mx(msf, meh)
            if(max_ending_here > max_so_far) {
                end = i;
                max_so_far = max_ending_here;
            }
        }

        int[] result = {start, end, max_so_far};
        return result;
    }

}
