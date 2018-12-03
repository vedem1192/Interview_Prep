package com.company;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Stack;


public class Graph {
    int V; // number of nodes
    LinkedList<Integer>[] adjacent;

    Graph(int v){
        V = v;
        adjacent = new LinkedList[v];

        for(int i = 0; i < v; i++){
            adjacent[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w){
        adjacent[v].add(w);
    }

    void depthFirstTraversal(int v, Boolean[] visited){
        visited[v] = true;
        System.out.println(v);

        LinkedList<Integer> voisins = adjacent[v];

        for(int i = 0; i < voisins.size(); i++){
            int voisin = voisins.get(i);

            if(!visited[voisin]){
                depthFirstTraversal(voisin, visited);
            }
        }
    }

    void breadthFirstTraversal(int s){
        Boolean[] visited = new Boolean[V];
        Arrays.fill(visited, false);

        LinkedList<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.add(s);

        while(!queue.isEmpty()){
            int node = queue.poll();
            System.out.println(node);

            LinkedList<Integer> voisins = adjacent[node];
            for(int i = 0; i < voisins.size(); i++){
                int voisin = voisins.get(i);

                if(!visited[voisin]){
                    queue.add(voisin);
                    visited[voisin] = true;
                }
            }
        }


    }

//    void topologicalSort() {
//        Stack<Integer> stack = new Stack<>();
//        boolean[] visited = new boolean[V];
//        Arrays.fill(visited, false);
//
//        for(int i = 0; i < V; i++){
//            if(!visited[i]){
//                topologicalSortUtil(i, visited, stack);
//            }
//        }
//
//        while (!stack.isEmpty())
//            System.out.println(stack.pop() + " ");
//    }

//    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
//        visited[v] = true;
//        LinkedList<Integer> voisins = adjacent[v];
//
//        for(int voisin : voisins){
//            if(!visited[voisin]){
//                topologicalSortUtil(voisin, visited, stack);
//            }
//        }
//        stack.push(v);
//    }

    // need to be a Directed Acyclic Graph (DAG)
    void topologicalSort() {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < V; i++){
            if(!visited[i])
                topologicalSort(i, visited, stack);
        }
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }

    void topologicalSort(int v, boolean[] visited, Stack<Integer> stack){
        visited[v] = true;
        LinkedList<Integer> voisins = adjacent[v];

        for(int voisin : voisins){
            if(!visited[voisin])
                topologicalSort(voisin, visited, stack);
        }

        stack.push(v);
    }
}
