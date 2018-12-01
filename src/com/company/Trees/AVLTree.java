package com.company.Trees;

public class AVLTree extends MyBinaryTree {

    public int height(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    public int getBalanced(Node node){
        if(node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    public Node rightRotation(Node node){
        Node x = node.left;
        Node y = x.right;

        // rotation
        node.left = y;
        x.right = node;

        // change height of nodes
        node.height = 1 + Math.max(height(node.left), height(node.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    public Node leftRotation(Node node){
        Node x = node.right;
        Node y = x.left;

        // rotation
        node.right = y;
        x.left = node;

        // change height of nodes
        node.height = 1 + Math.max(height(node.left), height(node.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    @Override
    public void insert(int value){
        root = insert(root, value);
    }

    private Node insert(Node current, int value){
        if(current == null)
            return new Node(value);

        if(value < current.value)
            current.left = insert(current.left, value);
        else if(value > current.value)
            current.right = insert(current.right, value);
        else
            return current;

        // update height of given node
        current.height = 1 + Math.max(height(current.left), height(current.right));

        // check if unbalanced
        int balance = getBalanced(current);

        // 4 cases if unbalanced
        // left left
        if(balance > 1 && value < current.left.value) {
            return rightRotation(current);
        }
        // left right
        else if (balance > 1 && value > current.left.value){
            current.left = leftRotation(current.left);
            return rightRotation(current);
        }
        // right right
        else if (balance < -1 && value > current.right.value) {
            return leftRotation(current);
        }
        // right left
        else if (balance < -1 && value < current.right.value){
            current.right = rightRotation(current.right);
            return leftRotation(current);
        }

        return current;
    }
}
