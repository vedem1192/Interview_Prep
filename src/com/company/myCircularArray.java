package com.company;

import java.util.HashSet;

public class myCircularArray {

    int[] arr = {-2,2,-1,1};
    public void read(){
        read(arr);
    }

    private void read(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        int i = 0;

        while (true) {
            System.out.println(arr[i]);

            if(set.contains(i)){
                if (set.size() == arr.length){
                    System.out.println("This array has one single cycle");
                    return;
                }
                else {
                    System.out.println("This array does not have one single cycle");
                    return;
                }
            } else {
                set.add(i);
            }

            i = nextIndex(arr, i);;
        }
    }

    private int nextIndex(int[] arr, int current){
        int next = (current + arr[current]) % arr.length;
        return next < 0 ? arr.length + next : next;
    }

//    private boolean



}
