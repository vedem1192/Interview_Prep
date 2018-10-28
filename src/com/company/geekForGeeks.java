package com.company;

import java.util.HashMap;
import java.util.Stack;
import java.util.Arrays;

public class geekForGeeks {

    // missing number in array
    public void missingNumberInArray(){
        int[] a = {0,1,2,3,4,6,7,8};
        int[] b = {0,1,3,4,5,6,7,8};
        int[] c = {0,2,3,4,5,6,7,8};
        int[] d = {0,1,2,3,4,5,6,8};

        System.out.println("Missing number should be 5 : " + missingNumber(a));
        System.out.println("Missing number should be 2 : " + missingNumber(b));
        System.out.println("Missing number should be 1 : " + missingNumber(c));
        System.out.println("Missing number should be 7 : " + missingNumber(d));
    }
    public void subArrayWithGivenSum(){
        int[] a = {1,2,6,7,12,1,4,2};
        int[] result = subArray(a, 17);

        for(int i = result[0]; i <= result[1]; i++){
            System.out.print(a[i] + " " );
        }


    }
    public void parenthesisChecker(){
        String ok = "([{}[()]])";
        String notOk = "{()kkk]}";

        System.out.println(ok + " parenthesis are ok : " + parenthesisChecker(ok));
        System.out.println(notOk + " parenthesis are ok : " + parenthesisChecker(notOk));
    }
    public void reverseSentence(){
        String s = "i.like.this.program.very.much";
        System.out.println(s + " reverses to : " + reverseSentence(s));
    }


    // methods for missing number in array
    private int missingNumber(int[] arr){
        return find_ms(arr, 0, arr.length -1);
    }
    private int find_ms(int[] arr, int s, int e){
        int middle = (s + e)/2;

        if(s == e){
            return arr[middle] == middle ? s + 1 : s;
        }

        if(arr[middle] == middle){
            return find_ms(arr, middle + 1, e);
        }
        else {
            return find_ms(arr, s, middle - 1);
        }
    }


    // methods for finding a sub array which adds to a given number
    private int[] subArray(int[] arr, int sum){
        int head = 0;
        int tail = 0;
        int localSum = arr[0];

        while(localSum != sum && tail < arr.length && head < arr.length){
            if(localSum < sum){
                head++;
                localSum += arr[head];
            }
            else {
                localSum -= arr[tail];
                tail++;
            }
        }

        int[] indexes = {tail, head};
        return indexes;
    }


    // methods for checking if parenthesis in expresison are correct
    HashMap<Character, Character> map = new HashMap<>();
    private Boolean parenthesisChecker(String s){
        Stack<Character> stack = new Stack<>();
        setMyMap();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(isOpening(c)){
                stack.push(c);
            }
            else if(isClosing(c)){
                if(stack.empty())
                    return false;
                char poped = stack.pop();
                if(map.get(c) != poped)
                    return false;
            }
        }
        return stack.empty();
    }
    private void setMyMap(){
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
    }
    private Boolean isOpening(char c){
        return map.containsValue(c);
    }
    private Boolean isClosing(char c){
        return map.containsKey(c);
    }


    //methods for reversing a sentence
    private String reverseSentence(String s){
        String[] words = s.split("\\.");
        Stack<String> stack = new Stack<>();

        for(String word : words){
            stack.push(word);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.empty()){
            String word = stack.pop() + ".";
            sb.append(word);
        }

        return sb.toString().substring(0, sb.length() - 1);
    }

    // GRAPHS
    private Graph makeGraph(){
        Graph g = new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(1, 0);

        g.addEdge(0, 4);
        g.addEdge(4, 0);

        g.addEdge(0, 5);
        g.addEdge(5, 0);

        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 1);
        g.addEdge(3, 1);
        g.addEdge(4, 1);

        g.addEdge(2, 3);
        g.addEdge(3, 2);

        return g;
    }

    // Depth first traversal
    public void depthFirstTraversal() {
        Graph g = makeGraph();

        Boolean[] visited = new Boolean[6];
        Arrays.fill(visited, false);

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 2)");

        g.depthFirstTraversal(2, visited);
    }

    public void breadthFirstTraversal(){
        Graph g = makeGraph();
        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 0)");
        g.breadthFirstTraversal(0);
    }




}
