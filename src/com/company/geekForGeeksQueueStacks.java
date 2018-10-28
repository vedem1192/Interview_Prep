package com.company;

import java.util.LinkedList;
import java.util.Stack;

public class geekForGeeksQueueStacks {

    void printNLE_n(){
        int[] i = {10, 2, 3, 6};
        printNLE_naif(i);
    }
    void myStackQueue(){
        MyStackQueue q = new MyStackQueue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);

        while (!q.isEmpty()){
            System.out.print(q.pop());
        }
    }
    void myQueueStack() {
        MyQueueStack s = new MyQueueStack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);

        while (!s.isEmpty()){
            System.out.print(s.pop());
        }
    }
    void myLinkedListQueue(){
        MyLinkedListQueue q = new MyLinkedListQueue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);

        while (!q.isEmpty()){
            System.out.print(q.pop());
        }
    }
    void myLinkedListStack(){
        MyLinkedListStack s = new MyLinkedListStack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);

        while (!s.isEmpty()){
            System.out.print(s.pop());
        }
    }

    private void printNLE_naif(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int y = i + 1; y < arr.length; y++){
                if(arr[y] > arr[i]){
                    System.out.print(arr[y] + " ");
                    break;
                } else if (y == arr.length - 1){
                    System.out.print("-1 ");
                }
            }
        }
        System.out.print("-1 ");
    }

    class MyStackQueue {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        void add(int x){
            s1.push(x);
        }

        int pop() {
            if(!s2.isEmpty())
                return s2.pop();

            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            return s2.pop();
        }

        boolean isEmpty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }
    class MyQueueStack {
        MyStackQueue q1 = new MyStackQueue();
        MyStackQueue q2 = new MyStackQueue();

        void push(int x) {
            if(q1.isEmpty())
                q1.add(x);
            else {
                while(!q1.isEmpty()){
                    q2.add(q1.pop());
                }
                q1.add(x);
                while (!q2.isEmpty()){
                    q1.add(q2.pop());
                }
            }
        }

        int pop() {
            return q1.pop();
        }

        boolean isEmpty(){
            return q1.isEmpty();
        }
    }
    class MyLinkedListQueue {
        LinkedList<Integer> list = new LinkedList<>();

        void add(int x){
            list.add(x);
        }

        int pop(){
            return list.poll();
        }

        boolean isEmpty(){
            return list.size() == 0;
        }
    }
    class MyLinkedListStack{
        LinkedList<Integer> list = new LinkedList<>();

        void push(int x){
            list.addFirst(x);
        }
        int pop(){
            return list.poll();
        }
        boolean isEmpty(){
            return list.size() == 0;
        }
    }

}
