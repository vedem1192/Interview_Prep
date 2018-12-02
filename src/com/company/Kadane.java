package com.company;

public class Kadane {

    // https://www.puzzlr.org/understanding-kadanes-algorithm-maximum-subarray-problem/

    public void maxSumNoIndex(){
        int[] a = {-2, 1, 3,-1, 4, -20};
        System.out.println("Max is : " + maxSubArray(a));
    }

    public void maxSumWithIndex(){
        int[] a = {-2, -1, 3,-1, 4, -20};
        maxSubArrayIndexes(a);
        System.out.println("Max is : " + maxSubArray(a));
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

    private int maxSubArrayIndexes(int[] a){
        int max_ending_here = a[0];
        int max_so_far = a[0];
        int start = 0;
        int end = 0;
        int m_start = 0;
        int m_end = 0;

        for(int i = 1; i < a.length; i++){
            if(a[i] > a[i] + max_ending_here){
                max_ending_here = a[i];
                start = i;
                end = i;
            } else {
                max_ending_here = max_ending_here + a[i];
                end = i;
            }

            if(max_so_far < max_ending_here){
                max_so_far = max_ending_here;
                m_start = start;
                m_end = end;
            }
        }

        System.out.println("Start : " + m_start + " , End : " + m_end);
        return max_so_far;
    }

//    public int getMaxSum(int[] arr) {
//        int max = arr[0], start = 0, end = 0, mstart = 0, mend = 0, currmax = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            if (arr[i] > arr[i] + currmax) {
//                currmax = arr[i];
//                start = i;
//                end = i;
//            } else {
//                currmax = currmax + arr[i];
//                end = i;
//            }
//
//            if (currmax > max) {
//                max = currmax;
//                mstart = start;
//                mend = end;
//            }
//        }
//        System.out.println("start:" + mstart + " end:" + mend);
//        return max;
//    }
//}

}
