package com.odev1.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {
    private final Button[][] buttons = new Button[3][3];
    private final Player player1 = new Player("Player 1", "X");
    private final Player player2 = new Player("Player 2", "O");
    private Player currentPlayer = player1;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic-Tac-Toe");

        GridPane gridPane = new GridPane();

        // initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(100, 100);
                buttons[i][j].setStyle("-fx-font-size: 24px;");
                buttons[i][j].setOnAction(event -> handleMove((Button) event.getSource()));
                gridPane.add(buttons[i][j], j, i);
            }}

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMove(Button button) {
        if (!button.getText().isEmpty()) {
            return;
        }

        button.setText(currentPlayer.getSymbol());
        if (checkForWin()) {
            // display win message 
            System.out.println("Player " + currentPlayer.getName() + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            // displaying draw message
            System.out.println("It's a draw!");
            resetBoard();
        } else {
            // switching players
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }}

    private boolean checkForWin() {
        // check rows, columns, and diagonals for a win
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
            }
        }

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
                if (buttons[i][j].getText().isEmpty()) {
                    return false; }}}
        
        return true; }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }}
        currentPlayer = player1;
    }

    public static void main(String[] args) {
        launch(args);
    }}
