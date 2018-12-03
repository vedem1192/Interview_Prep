package com.company;

import java.util.Arrays;

public class CoinChange {
    int[] coins = {1,2,3};
    int change = 10;

    public void numberOfWays(){
        // creer mon array de possibilités
        int[] ways = new int[change + 1];

        ways[0] = 1;

        // pour chaque valeur de coins, combien de combinaisons
        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j < ways.length; j++){
                if(coins[i] <= j){
                    ways[j] = ways[j] + ways[j - coins[i]];
                }
            }
        }

        System.out.println("There are " + ways[change] + " to give change");
    }

    public void minCoin(){
        System.out.println("Minimum number of coins : " + minimumCoins(change, coins));
    }

    private int minimumCoins(int amount, int[] coins){
        int[] solutions = new int[amount + 1];
        Arrays.fill(solutions, Integer.MAX_VALUE);
        solutions[0] = 0;

        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j < solutions.length; j++){
                if(coins[i] <= j ){
                    solutions[j] = Math.min(solutions[j], 1 + solutions[j - coins[i]]);
                }
            }
        }

        return solutions[amount];
    }

    private void getCoins(){

    }

}
