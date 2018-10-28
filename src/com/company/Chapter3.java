package com.company;


import java.util.Stack;

public class Chapter3 {

    public void questionFive(){
        Stack<Integer> s = new Stack<>();
        int[] test = {3,6,7,9,1,35,7,6,4,2,6,8,13,54};
        for(int i : test){
            s.push(i);
        }
        sortStack(s);
    }

    // Question 5
    public void sortStack(Stack<Integer> stack) {
        Stack<Integer> sortedStack = new Stack<Integer>();

        while(!stack.isEmpty()){
            int value = stack.pop();

            if(sortedStack.isEmpty()){
                sortedStack.push(value);
            }
            else {
                Stack<Integer> temp = new Stack<>();
                while(sortedStack.peek() <= value){
                    temp.push(sortedStack.pop());
                }
                sortedStack.push(value);
                while (!temp.isEmpty()){
                    sortedStack.push(temp.pop());
                }
            }
        }
        stackPrint(sortedStack);
    }

    private void stackPrint(Stack<Integer> stack) {
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

}
