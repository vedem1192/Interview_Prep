package com.company.Trees;

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

    public void insert(int value){
        root = insert(root, value);
    }

    public boolean contains(int value){
        return contains(root, value);
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



}
