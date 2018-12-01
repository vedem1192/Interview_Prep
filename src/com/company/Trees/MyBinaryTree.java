package com.company.Trees;

import java.rmi.NotBoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyBinaryTree {

    Node root;


    public MyBinaryTree(){
        setupTree();
    }

    private void setupTree(){
        insert(6);
        insert(4);
        insert(8);
        insert(3);
        insert(5);
        insert(7);
        insert(9);
    }

    private void mirrorTree(){
        root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(2);

        root.left.left = new Node(3);
        root.left.right = new Node(4);

        root.right.left = new Node(4);
        root.right.right = new Node(3);
    }

    public void insert(int value){
        root = insert(root, value);
    }

    public boolean contains(int value){
        return contains(root, value);
    }

    public void delete(int value){
        root = delete(root, value);
        print(Type.order);
    }

    public void BFS(){
        BFS(root);
    }

    public boolean isMirrorTree() {
        return isMirrorTree(root);
    }

    public void print(Type traversal){
        switch (traversal){
            case order:
                orderTraversal(root);
                break;
            case preorder:
                preorderTraversal(root);
                break;
            case postorder:
                postorderTraversal(root);
                break;
            default:
                break;
        }
    }

    private void orderTraversal(Node node){
        //GND
        if(node != null){
            orderTraversal(node.left);
            System.out.print(node.value + " ");
            orderTraversal(node.right);
        }
    }

    private void preorderTraversal(Node node){
        //NGD
        if(node != null){
            System.out.print(node.value + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    private void postorderTraversal(Node node){
        //GDN
        if(node != null){
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.value + " ");
        }

    }

    private Node insert(Node current, int value){
        if(current == null)
            return new Node(value);

        if(value < current.value){
            current.left = insert(current.left, value);
        }
        else if(value > current.value){
            current.right = insert(current.right, value);
        }

        // if value == current
        return current;
    }

    private boolean contains(Node node, int value){
        if(node == null)
            return false;

        if(node.value == value)
            return true;

        return value < node.value ? contains(node.left, value) : contains(node.right, value);
    }

    private Node delete(Node node, int value){
        if(node == null){
            return null;
        }

        if(value < node.value){
            node.left = delete(node.left, value);
            return node;
        }
        if(value > node.value){
            node.right = delete(node.right, value);
            return node;
        }

        node = remove(node);
        return node;
    }

    private Node remove(Node node){
        // has no children
        if(node.left == null && node.right == null){
            return null;
        }

        // has one child
        if(node.left == null)
            return node.right;

        if(node.right == null)
            return node.left;

        // has two children
        int smallestValue = findSmallestValue(node.right);
        node.value = smallestValue;
        node.right = delete(node.right, smallestValue);

        return node;
    }

    private int findSmallestValue(Node node){
        return node.left == null ? node.value : findSmallestValue(node.left);
    }

    private void BFS(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()){
            Node current = queue.poll();
            if(current.left != null)
                queue.add(current.left);
            if(current.right != null)
                queue.add(current.right);

            System.out.print(current.value + " ");
        }
    }

    // TODO : figure it out, you got this
    private boolean isMirrorTree(Node node){
        Queue<Node> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        queue.add(node);
        stack.add(node.value);
        int level = 0;

        while (!queue.isEmpty()){
            Node popped = queue.poll();

            if(popped.left != null)
                queue.add(popped.left);
            if(popped.right != null)
                queue.add(popped.right);

            if(!stack.isEmpty() && stack.peek() == popped.value){
                stack.pop();
            } else {
                stack.add(popped.value);
            }
        }
        return stack.isEmpty();
    }







}
