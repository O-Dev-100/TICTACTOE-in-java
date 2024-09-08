package com.odev1.tictactoe;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        GameUI gameUI = new GameUI();
        Customization customization = new Customization();

        // Apply a custom theme
        customization.setTheme(Color.BLACK, Color.GREEN, Color.YELLOW);
        customization.applyTheme(gameUI);

        frame.add(gameUI);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
