package com.odev1.tictactoe;

import fr.avianey.minimax4j.IA;
import fr.avianey.minimax4j.Move;
import fr.avianey.minimax4j.impl.Minimax;
import java.util.ArrayList;
import java.util.List;

public class AIPlayer extends Player {

    public AIPlayer(String name, String symbol) {
        super(name, symbol);
    }

    public int[] makeMove(String[][] board) {
        TicTacToeIA ia = new TicTacToeIA(board, this.symbol);
        Move bestMove = ia.getBestMove();
        return new int[] { bestMove.getX(), bestMove.getY() };
    }

    private class TicTacToeIA extends Minimax {

        private String[][] board;
        private String symbol;

        public TicTacToeIA(String[][] board, String symbol) {
            this.board = board;
            this.symbol = symbol;
        }

        @Override
        public boolean isOver() {
            return checkWin(board) || checkDraw(board);
        }

        @Override
        public int evaluate() {
            return evaluateBoard(board);
        }

        @Override
        public Iterable<Move> getPossibleMoves() {
            List<Move> moves = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals("")) {
                        moves.add(new Move(i, j));
                    }}}
            
            return moves;
        }

        @Override
        public void makeMove(Move move) {
            board[move.getX()][move.getY()] = symbol;
        }

        @Override
        public void unmakeMove(Move move) {
            board[move.getX()][move.getY()] = "";
        }

        private boolean checkWin(String[][] board) {
            for (int i = 0; i < 3; i++) {
                if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                    return true;
                }
                if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                    return true;
                }
            }
            if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
                return true;
            }
            if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
                return true;
            }
            return false;
        }

        private boolean checkDraw(String[][] board) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals("")) {
                        return false;
                    }}}
            
            return true;
        }

        private int evaluateBoard(String[][] board) {
            if (checkWin(board)) {
                return symbol.equals("X") ? 10 : -10;
            }
            return 0;
        }}}
