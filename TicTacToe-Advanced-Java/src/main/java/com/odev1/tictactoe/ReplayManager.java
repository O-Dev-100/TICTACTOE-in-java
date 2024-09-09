package com.yourname.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class ReplayManager {
    private final List<String[][]> moves = new ArrayList<>();

    public void recordMove(String[][] boardState) {
        String[][] copy = new String[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(boardState[i], 0, copy[i], 0, 3);
        }
        moves.add(copy);
    }

    public void replay(Game game) {
        for (String[][] move : moves) {
            game.setBoardState(move);
            game.displayBoard();
            try {
                Thread.sleep(1000); // Pause for 1 second between moves
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }}}}
