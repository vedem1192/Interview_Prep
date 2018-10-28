package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class geekForGeeksBacktracking {

    public void nQueen(int n){
        NQueenProblem nq = new NQueenProblem(n);
        nq.solveQueen();
    }

    void sudoku(){
        Sudoku s = new Sudoku();
    }

    void ratInMaze(){
        RatInMaze rat = new RatInMaze();
        int maze[][] = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };
        rat.solve(maze);
    }

    void wordBoggle(){

    }

    void ipGenerator(){

    }


    private class NQueenProblem {
        int N;
        int[][] board;

        public NQueenProblem(int N){
            this.N = N;
            board = new int[N][N];

            for(int i = 0; i < N; i++){
                Arrays.fill(board[i], 0);
            }
        }

        boolean isSafe(int[][] board, int row, int column){
            int i, j;

            // check for left of row
            for(i = 0; i < column; i++){
                if(board[row][i] == 1)
                    return false;
            }

            // check for left upper diagonal
            for(i = row, j = column; i >= 0 && j >= 0; i--, j--){
                if(board[i][j] == 1)
                    return false;
            }

            // check for left lower diagonal
            for(i = row, j = column; i < N && j >= 0; i++, j--){
                if(board[i][j] == 1)
                    return false;
            }

            return true;
        }

        private void printQueen(int[][] board){
            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    System.out.print(board[row][col] + " ");
                }
                System.out.println();
            }
        }

        boolean solveQueenUtil(int[][] board, int col){
            if(col >= N)
                return true;

            for(int i = 0; i < N; i++){

                if(isSafe(board, i, col)){
                    board[i][col] = 1;

                    if(solveQueenUtil(board, col + 1))
                        return true;

                    board[i][col] = 0;
                }
            }
            return false;
        }

        boolean solveQueen(){
            if(!solveQueenUtil(board, 0)){
                System.out.print("Cannot find solution");
                return false;
            }

            printQueen(board);
            return true;
        }

    }

    private class Sudoku {

        public Sudoku() {
            int[][] board = new int[][]
                    {
                            {3, 0, 6, 5, 0, 8, 4, 0, 0},
                            {5, 2, 0, 0, 0, 0, 0, 0, 0},
                            {0, 8, 7, 0, 0, 0, 0, 3, 1},
                            {0, 0, 3, 0, 1, 0, 0, 8, 0},
                            {9, 0, 0, 8, 6, 3, 0, 0, 5},
                            {0, 5, 0, 0, 9, 0, 6, 0, 0},
                            {1, 3, 0, 0, 0, 0, 2, 5, 0},
                            {0, 0, 0, 0, 0, 0, 0, 7, 4},
                            {0, 0, 5, 2, 0, 6, 3, 0, 0}
                    };
            int N = board.length;

            if (solve(board, N)) {
                print(board, N); // print solution
            }
            else {
                System.out.println("No solution");
            }
        }

        boolean isSafe(int[][] board, int row, int col, int num){

            // unique in row
            for(int i = 0; i < board.length; i++){
                if(board[row][i] == num)
                    return false;
            }

            // unique in col
            for(int i = 0; i < board.length; i++){
                if(board[i][col] == num)
                    return false;
            }

            // unique in little square
            int squareSize = (int) Math.sqrt(board.length);
            int boxRowStart = row - row % squareSize;
            int boxColStart = col - col % squareSize;

            for(int i = boxRowStart; i < boxRowStart + squareSize; i++){
                for(int j = boxColStart; j < boxColStart + squareSize; j++){
                    if(board[i][j] == num)
                        return false;
                }
            }
            return true;
        }

        boolean solve(int[][] board, int n){
            int row = -1;
            int col = -1;
            boolean isComplete = true;

            // find empty cell
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(board[i][j] == 0){
                        row = i;
                        col = j;
                        isComplete = false;
                        break;
                    }
                }
                if(!isComplete)
                    break;
            }
            if(isComplete)
                return true;

            // try to complete sudoku with number from 1 to n
            for(int num = 1; num <= n; num++){
                if(isSafe(board, row, col, num)) {
                    board[row][col] = num;

                    if(solve(board, n))
                        return true;

                    board[row][col] = 0;
                }
            }
            return false;
        }

        void print(int[][] board, int N){
            for (int r = 0; r < N; r++)
            {
                for (int d = 0; d < N; d++)
                {
                    System.out.print(board[r][d]);
                    System.out.print(" ");
                }
                System.out.print("\n");

                if ((r + 1) % (int) Math.sqrt(N) == 0)
                {
                    System.out.print("");
                }
            }
        }


    }

    private class RatInMaze {
        int N = 4;

        boolean isSafe(int[][] maze, int row, int col) {
            return (row >= 0 && col >= 0 && row < N && col < N && maze[row][col] == 1);
        }

        boolean solveUtil(int[][] maze, int row, int col, int[][] sol){
            if(row == N -1 && col == N -1){
                sol[row][col] = 1;
                return true;
            }

            if(isSafe(maze, row, col)){
                sol[row][col] = 1;

                if(solveUtil(maze, row + 1, col, sol) ||
                        solveUtil(maze, row, col + 1, sol)){
                    return true;
                }

                sol[row][col] = 0;
            }
            return false;
        }

        boolean solve(int[][] maze) {
            int[][] sol = new int[N][N];
            for(int[] i : sol){
                Arrays.fill(i, 0);
            }

            if(solveUtil(maze, 0,0, sol)){
                print(sol);
                return true;
            }

            System.out.println("No solution found");
            return false;
        }

        void print(int[][] sol) {
            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    System.out.print(sol[row][col] + " ");
                }
                System.out.println();
            }
        }
    }


}
