package com.odev1.tictactoe;

public class Board {
    private String[][] board;
    private static final int SIZE = 3;

    public Board() {
        board = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ""; }}}

    public boolean makeMove(int row, int col, String symbol) {
        if (isValidMove(row, col)) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].equals("")) {
                    return false;
                }}}
      
        return true;
    }

    public String checkWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2])) return board[i][0];
            if (checkLine(board[0][i], board[1][i], board[2][i])) return board[0][i];
        }
        if (checkLine(board[0][0], board[1][1], board[2][2])) return board[0][0];
        if (checkLine(board[0][2], board[1][1], board[2][0])) return board[0][2];
        return null;
    }

    private boolean checkLine(String a, String b, String c) {
        return !a.equals("") && a.equals(b) && b.equals(c);
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col].equals("");
    }

    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j].equals("") ? "-" : board[i][j]);
                if (j < SIZE - 1) System.out.print("|");
            }
            System.out.println();
        }}}
