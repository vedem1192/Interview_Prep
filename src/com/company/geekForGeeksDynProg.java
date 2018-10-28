package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class geekForGeeksDynProg {

    public void shortestSuperSequence(){
        String s1 = "abd";
        String s2 = "aXbXXc";
        System.out.print(superSequence(s1, s2, s1.length(), s2.length()));
    }
    public void longestIncreasingSubsequence() {
        int[] i = {50, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.print(LIS(i));
    }
    public void longestIncreasingSubsequence2() {
        int[] i = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.print(LIS_2(i));
    }
    public void longestCommonSubsequence(){
        String s1 = "ABCDGH";
        String s2 = "AEDFHR";

        System.out.print(longestCommonSubsequence(s1, s2));
    }

    private int superSequence(String s1, String s2, int m, int n){
        int[][] dp = new int[m+1][n+1];
        LinkedList<pt> list = new LinkedList<>();

        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){

                if(i == 0) {
                    dp[i][j] = j;
                }
                else if(j == 0){
                    dp[i][j] = i;
                }
                else if(s1.charAt(i -1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    list.add(new pt(i -1, j -1));
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    private void print(int[][] dp, int m, int n, LinkedList<pt> list, String s1, String s2){
        pt p = list.poll();
        pt lastP = p;

        System.out.println(p.toString());
        StringBuilder sb = new StringBuilder();

        for(int i = lastP.x; i < m + 1; i++){
            for(int j = lastP.y; j < n + 1; j++){
                if(p.equal(i, j)){
                    sb.append(s1.charAt(i));
                    lastP = p;
                    p = list.poll();
                    System.out.println(p.toString());
                    break;
                }
                else if(i == p.x && j > lastP.y && j < p.y){
                    sb.append(s2.charAt(j));
                }
            }
        }
        System.out.print(sb.toString());
    }
    private class pt{
        int x = -1;
        int y = -1;

        public pt(int x, int y){
            this.x = x;
            this.y = y;
        }

        public boolean equal(int i, int j){
            return (x == i && y == j);
        }

        @Override
        public String toString(){
            return ("Pt : " + x + " " + y);
        }
    }

    int LIS(int[] arr){
        int max_here = 1;
        int max_so_far = 1;

        for(int i = 1; i < arr.length; i++){
            if(arr[i] > arr[i - 1]){
                max_here = Math.max(max_here, max_so_far) + 1;
            } else {
                max_here = 1;
            }

            max_so_far = Math.max(max_here, max_so_far);
        }
        return max_so_far;
    }
    int LIS_2(int[] arr){
        int mx_here = 1;
        int mx_so_far = 1;

       ArrayList<Integer> list_here = new ArrayList<>();
       ArrayList<Integer> list_so_far = new ArrayList<>();

       list_here.add(arr[0]);
       list_so_far.add(arr[0]);

        for(int i = 1; i < arr.length; i++){
            if(arr[i] > arr[i - 1]){
                mx_here = Math.max(mx_here, mx_so_far) + 1;
                list_here.add(arr[i]);
            } else {
                mx_here = 1;
            }

            if(mx_here > mx_so_far){
                mx_so_far = mx_here;
                list_so_far = (ArrayList<Integer>) list_here.clone();
            }
        }

        for(int i = 0; i < list_so_far.size(); i++){
            System.out.print(list_so_far.get(i) + " ");
        }
        System.out.println();
        return mx_so_far;
    }

    int longestCommonSubsequence(String s1, String s2){
        ArrayList<pt> points = new ArrayList<>();

        for(int i = 0; i < s1.length(); i++){
            for(int j = 0; j < s2.length(); j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    points.add(new pt(i, j));
                }
            }
        }

        for(int i = 0; i < points.size(); i++){
            System.out.print(String.valueOf(s1.charAt(points.get(i).x)));
        }
        System.out.println();

        return points.size();
    }




}
