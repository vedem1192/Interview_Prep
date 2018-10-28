package com.company;

import java.util.*;


public class Chapter1 {

    static void questionOne() {
        String s = "abcdefg";
        System.out.println(s + " has all unique characters? ");
        System.out.println("a) With hashSet : " + isUnique(s));
        System.out.println("b) No additional data structure : " + isUniqueOneDataStruct(s));
    }

    static void questionTwo(){
        String s1 = "xx de kle";
        String s2 = "lke x edx";
        System.out.println(s1 + " and " + s2 + " are permutations : " + checkPerm(s1, s2));
    }

    static void questionThree(){
        String s = "Mr John Smith    ";
        System.out.println("urlify " + s);
        System.out.println("result : " + urlify(s, 13));
    }

    static void questionFour(){
        String s = "aabbdf40";
        System.out.println(s + " is a palindrom perm? : " + isPalindromePermutation(s));
    }

    static void questionFive(){
        String str1 = "babe";
        String str2 = "bae";

        System.out.println(str1 + " and " + str2 + " are one char away : " + oneAway(str1, str2));
    }

    static void questionSix() {
        String s1 = "aaabbggggeeDDDeWWiiJ";
        System.out.println("Compressed string : " + s1 + " is " + stringCompression(s1));
        String s2 = "abcddef";
        System.out.println("Compressed string : " + s2 + " is " + stringCompression(s2));

    }

    static void questionEight(){
        int[][] arr = { {1,2,3,4,5,6,7}, {2,0,1,1,1,1,1}, {1,1,1,1,1,1,1}, {2,3,4,5,0,3,5}, {1,2,3,4,5,6,7} };
        zeroMatrix(arr);

    }

    // Question 1
    static Boolean isUnique(String s){
        HashSet<Character> hs = new HashSet<>();
        char[] myChar = s.toCharArray();

        for(char c : myChar){
            if(hs.contains(c)){
                return false;
            }
            else {
                hs.add(c);
            }
        }
        return true;
    }

    static Boolean isUniqueOneDataStruct(String s){
        if(s.length() == 1){
            return true;
        }

        char[] c = s.toCharArray();
        Arrays.sort(c);

        int index = 0;
        int runner = 1;

        while(index < c.length && runner < c.length){
            if(c[index] == c[runner]){
                return false;
            }
            else {
                runner++;
                index++;
            }
        }
        return true;
    }

    // Question 2
    static Boolean checkPerm(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        String sortedS1 = sortString(s1);
        String sortedS2 = sortString(s2);

        return sortedS1.equals(sortedS2);
    }

    private static String sortString(String s){
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    // Question 3
    static String urlify(String s, int slength){
        char[] c = s.toCharArray();
        int sIndex = slength - 1;

        for(int i = c.length - 1; i >= 0; i--){
            if(c[sIndex] != ' '){
                c[i] = c[sIndex];
            }
            else {
                c[i] = '0';
                c[i - 1] = '2';
                c[i - 2] = '%';
                i -= 2;
            }
            sIndex--;
        }
        return new String(c);
    }

    // Question 4
    static Boolean isPalindromePermutation(String s){
        HashMap<Character, Integer> hm = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            if(!hm.containsKey(s.charAt(i))){
                hm.put(s.charAt(i), 1);
            }
            else {
                hm.put(s.charAt(i), hm.get(s.charAt(i)) + 1);
            }
        }
        return checkForCompliance(hm);
    }

    private static Boolean checkForCompliance(HashMap<Character, Integer> hm){
        Iterator it = hm.entrySet().iterator();
        int count = 0;

        while(it.hasNext()){
            Map.Entry<Character, Integer> elem = (Map.Entry<Character, Integer>)it.next();
            if(elem.getValue()%2 != 0){
                count++;
                if(count > 1){
                    return false;
                }
            }
        }
        return true;
    }

    // Question 5
    static Boolean oneAway(String str1, String str2){
        String _long = str1.length() >= str2.length() ? str1 : str2;
        String _short = _long.equals(str1) ? str2 : str1;
        int lengthDiff = _long.length() - _short.length();

        // check if there's at the most 1 char diff between the strings
        if(lengthDiff > 1){
            return false;
        }

        // case same length
        int diffCount = 0;

        if(lengthDiff == 0){
            for(int i =0 ; i< _long.length() ; i++ ){
                if(_long.charAt(i) != _short.charAt(i)){
                    diffCount++;
                    if(diffCount > 1){
                        return false;
                    }
                }
            }
            return true;
        }

        // case one letter difference
        int indexLong = 0;
        int indexShort = 0;

        while(indexLong < _long.length() && indexShort < _short.length()){
            if(_long.charAt(indexLong) != _short.charAt(indexShort)){
                diffCount++;
                if(diffCount > 1){
                    return false;
                }
                indexLong++;
            }
            else {
                indexLong++;
                indexShort++;
            }
        }
        return true;
    }

    // Question 6
    static String stringCompression(String s){
        String compressed = String.valueOf(s.charAt(0));
        int charCount = 1;

        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) != compressed.charAt(compressed.length() - 1)){
                compressed += String.valueOf(charCount) + String.valueOf(s.charAt(i));
                charCount = 1;
            }
            else {
                charCount++;
            }
        }
        compressed += String.valueOf(charCount);

        return compressed.length() < s.length() ? compressed : s;
    }

    // Question 8
    static void zeroMatrix(int[][] arr){
        HashSet<Integer> changedCol = new HashSet<>();

        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++){
                if(arr[row][col] == 0 && !changedCol.contains(col)){
                    change(row, col, changedCol, arr);
                    break;
                }
            }
        }

        print(arr);
    }

    private static void change(int row, int col, HashSet<Integer> hash, int[][] arr){
        //change row
        for(int i = 0; i < arr[row].length; i++){
            arr[row][i] = 0;
        }

        // change col
        for(int i = 0; i < arr.length; i++){
            arr[i][col] = 0;
        }

        // add to hashset
        hash.add(col);
    }

    private static void print(int[][] arr){
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++){
                System.out.print( arr[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Question 9



}
