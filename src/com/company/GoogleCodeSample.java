package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class GoogleCodeSample {

    public void question1(){
        String s = "2-4A0r7-4k";
        System.out.println(solution(s, 3));

        String s2 = "r";
        System.out.println(solution(s2, 1));
    }

    public String solution(String S, int K) {
        // write your code in Java SE 8

        // remove the dash
        String s = S.replaceAll("-", "");

        // get char array
        char[] c = s.toCharArray();

        // StringBuilder
        StringBuilder sc = new StringBuilder();

        if(c.length <= K){
            for(char x : c){
                sc.append(Character.toString(getUpperCase(x)));
            }
            return sc.toString();
        }


        // get first part
        int firstPart = c.length % K;

        if(firstPart != 0){
            for(int i = 0; i < firstPart; i++){
                sc.append(Character.toString(getUpperCase(c[i])));
            }
            sc.append("-");
        }

        int p = firstPart;
        while(p < c.length){
            for(int i = p; i < p+K; i++){
                sc.append(Character.toString(getUpperCase(c[i])));
            }
            sc.append("-");
            p += K;
        }

        return sc.toString().substring(0, sc.length() -1);
    }
    // toUpperCase only when (int)c[i] is between [97, 122]
    char getUpperCase(char c){
        if((int)c <= 122 && (int)c >= 97){
            return Character.toUpperCase(c);
        }
        return c;
    }


    public void condingSample1(){
//        String[] L = {"a.b@example.com", "a.b+work@example.com", "x@example.com", "x@ex.ample.com", "y@example.com", "y@example.com","y@example.com"};
        String[] L = {"a.b+work.k@example.com", "a...b@example.com"};

        System.out.println(codingSample1(L));
    }

    private int codingSample1(String[] L){

        HashMap<String, Integer> hashmap = new HashMap<>();

        for(String email : L){
            // Split local name from domain
            String localName = email.substring(0, email.indexOf("@"));
            String domain = email.substring(email.indexOf("@"), email.length());

            // get "pure" value of email (without dots in local name and +)
            String filteredName = filter(localName) + domain;

            // add to hasmap
            if(!hashmap.containsKey(filteredName)){
                hashmap.put(filteredName, 1);
            }
            else {
                hashmap.put(filteredName, hashmap.get(filteredName) + 1);
            }
        }

        return countDuplicates(hashmap);
    }
    public String filter(String s){
        String filtered = s;
        // remove everything after the +
        int plus = s.indexOf("+");
        if(plus > 0){
            filtered = s.substring(0, s.indexOf("+"));
        }

        // remove .
        filtered = filtered.replaceAll("\\.", "");
        return filtered;
    }
    public int countDuplicates(HashMap<String, Integer> hashmap){
        int count = 0;

        for(String key : hashmap.keySet()){
            if(hashmap.get(key) > 1){
                count++;
            }
        }
        return count;
    }

    public void condingSample2(){
        int[] A = {1,2,1,3,4,3,5,3,2};
        int[] B = {1,2,1,2,1,2,1};
        int[] C = {1,0,1,2,2,0,0,0,1};

        System.out.println(codingSample2(B));

    }

    private int codingSample2(int[] A){
        if(A.length < 2){
            return A.length;
        }

        ArrayList<Integer> all_max = new ArrayList<>();

        int max_so_far = 2;
        int index1 = 0;
        int index2 = 1;

        int n1 = A[index1];
        int n2 = A[index2];

//        for(int i = 2; i < A.length; i++){
//            index2++;
//
//            if(belongInBasket(A[index1], A[index2], A[i])){
//                max_so_far++;
//            }
//            else {
//                index1 = A[index1 + 1];
//                index2 = A[i];
//                all_max.add(max_so_far);
//                max_so_far = max_so_far - 1;
//            }
//        }

        while(index1 < A.length && index2 < A.length){
            if(belongInBasket(n1, n2, A[index2])){
                max_so_far++;
            }
            else {
                if(index1 < A.length - 1){
                    index1 = A[index1 + 1];
                }
                all_max.add(max_so_far);
                max_so_far = max_so_far - 1;
            }
            index2++;
        }

        return all_max.isEmpty() ? max_so_far : getMax(all_max);
    }

    public Boolean belongInBasket(int n1, int n2, int num){
        return num == n1 || num == n2;
    }

    public int getMax(ArrayList<Integer> all_max){
        Collections.sort(all_max);
        return all_max.get(all_max.size() -1);
    }

    public void testArrayEquality() {
        int[] a = {0,1,2,3};
        int[] b = {0,1,2,3};
        int[] c = {3,4,5,6};
        arrayEquality(a,b,c);
    }

    public void arrayEquality(int[] a, int[] b, int[] c){
        System.out.println("a.equals(b) : " + a.equals(b));
        System.out.println("a == b : " + (a == b));
        System.out.println("Arrays.equals(a,b) " + Arrays.equals(a,b));
        System.out.println("- - - - ");
        System.out.println("b.equals(c) : " + (b.equals(c)));
        System.out.println("b == c : " + (b == c));
        System.out.println("Arrays.equals(c,b) " + Arrays.equals(c,b));
    }

}
