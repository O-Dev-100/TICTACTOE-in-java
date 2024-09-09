package com.odev1.tictactoe;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Multiplayer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'host' to host a game or 'join' to join a game:");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("host")) {
            hostGame();
        } else if (choice.equalsIgnoreCase("join")) {
            joinGame();
        } else {
            System.out.println("Invalid choice."); }}

    private static void hostGame() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Hosting game... Waiting for a player to join.");
            Socket socket = serverSocket.accept();
            System.out.println("Player joined!");

            playGame(socket, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void joinGame() {
        try (Socket socket = new Socket("localhost", PORT)) {
            System.out.println("Connected to the host!");

            playGame(socket, false);
        } catch (IOException e) {
            e.printStackTrace(); }}

    private static void playGame(Socket socket, boolean isHost) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            Board board = new Board();
            String symbol = isHost ? "X" : "O";
            String opponentSymbol = isHost ? "O" : "X";

            while (true) {
                board.displayBoard();
                if (isHost) {
                    System.out.println("Your move (row and column): ");
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();
                    if (board.makeMove(row, col, symbol)) {
                        out.println(row + " " + col);
                        if (board.checkWinner() != null || board.isFull()) break;
                        isHost = false;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                } else {
                    System.out.println("Waiting for opponent's move...");
                    String[] move = in.readLine().split(" ");
                    int row = Integer.parseInt(move[0]);
                    int col = Integer.parseInt(move[1]);
                    board.makeMove(row, col, opponentSymbol);
                    if (board.checkWinner() != null || board.isFull()) break;
                    isHost = true;
                }
            }

            board.displayBoard();
            String winner = board.checkWinner();
            if (winner != null) {
                System.out.println("Winner: " + winner);
            } else {
                System.out.println("It's a draw!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }}}
