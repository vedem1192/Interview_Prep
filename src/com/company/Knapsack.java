package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Knapsack {

    int[] value = {23, 38, 15, 42, 21, 53};
    int[] weight = {2,4,3,3,8,7};
    int W = 10;
    ArrayList<Item> bag = new ArrayList<>();

    private class Item implements Comparable<Item> {
        int value;
        int weigth;
        double density;

        public Item(int value, int weigth) {
            this.value = value;
            this.weigth = weigth;
            this.density = (value/weigth);
        }

        public int compareTo(Item item){
            return this.density < item.density ? 1 : -1;
        }

        @Override
        public String toString() {
            return "Item value : " + value + " , weight : " + weigth;
        }
    }

    public void knapsack(){
        Item[] items = new Item[value.length];

        for(int i = 0 ; i < value.length; i++){
            items[i] = new Item(value[i], weight[i]);
        }

        System.out.println("Max value is " + kn(W, items, items.length - 1));
        knapsackWithIndexes(items);
    }

    private int kn(int W, Item[] items, int n){
        if(n < 0 || W == 0)
            return 0;

        if(items[n].weigth > W)
            return kn(W, items, n-1);

        return Math.max(items[n].value + kn(W-items[n].weigth, items, n-1), kn(W, items, n-1));
    }

    public void knapsackWithIndexes(Item[] items){
        int[][] matrix = new int[value.length + 1][ W + 1];
        for(int i = 0; i < value.length + 1; i++){
            Arrays.fill(matrix[i], 0);
        }

        for(int i = 1; i < matrix.length; i++){
            for(int w = 1; w < matrix[0].length ; w++){

                if(items[i-1].weigth <= w){
                    int value1 = matrix[i-1][w];
                    int value2 = items[i-1].value + matrix[i-1][w - items[i-1].weigth];

                    matrix[i][w] = Math.max(value1, value2);
                }
                else {
                    matrix[i][w] = matrix[i - 1][w];
                }
            }
        }

//        print(matrix);
        checkItems(matrix.length - 1, matrix[0].length - 1, items, matrix);
        printBag();
    }

    private void print(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++ ){
                System.out.print(arr[i][j] + " , " );
            }
            System.out.println();
        }
    }

    private void printBag(){
        for(Item item : bag){
            System.out.println(item.toString());
        }
    }

    private void checkItems(int i, int w, Item[] items, int[][] matrix){
        if(i <= 0 || w <= 0){
            return;
        }

        int picked = matrix[i][w];

        if(picked != matrix[i-1][w]){
            bag.add(items[i-1]);
            checkItems(i-1, w-items[i-1].weigth, items, matrix);
        }
        else {
            checkItems(i-1, w, items, matrix);
        }
    }
}
