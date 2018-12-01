package com.company;

public class Hanoi {

    int n;

    public static void resolveFor(int n){
        hanoi(n, 'A', 'B', 'C');
    }

    private static void hanoi(int n, char from_rod, char to_rod, char using_rod){
        if(n == 1){
            print(n, from_rod, to_rod);
            return;
        }

        hanoi(n-1, from_rod, using_rod, to_rod);
        print(n, from_rod, to_rod);
        hanoi(n-1, using_rod, to_rod, from_rod);
    }

    private static void print(int n, char from, char to){
        System.out.println("Moving disk " + n + " from " + from + " to " + to);
    }


}
