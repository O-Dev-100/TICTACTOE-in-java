package com.yourname.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    private final JButton[][] buttons = new JButton[3][3];
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Scoreboard scoreboard;

    public Game(Player player1, Player player2, Scoreboard scoreboard) {
        this.player1 = player1;
        this.player2 = player2;
        this.scoreboard = scoreboard;
        this.currentPlayer = player1;
        initUI();
    }

    private void initUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }}
        setVisible(true); }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        buttonClicked.setText(currentPlayer.getSymbol());
        buttonClicked.setEnabled(false);

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer.getName() + " wins!");
            scoreboard.incrementWins(currentPlayer);
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            scoreboard.incrementDraws();
            resetBoard();
        } else {
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }}

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayer.getSymbol()) &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][0].getText().equals(buttons[i][2].getText())) {
                return true;
            }
            if (buttons[0][i].getText().equals(currentPlayer.getSymbol()) &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[0][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }}
      
        if (buttons[0][0].getText().equals(currentPlayer.getSymbol()) &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[0][0].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if (buttons[0][2].getText().equals(currentPlayer.getSymbol()) &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[0][2].getText().equals(buttons[2][0].getText())) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }}}
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true); }}
        currentPlayer = player1;
    }

    public static void main(String[] args) {
        Player player1 = new Player("Player 1", "X");
        Player player2 = new Player("Player 2", "O");
        Scoreboard scoreboard = new Scoreboard();
        new Game(player1, player2, scoreboard);
    }}
