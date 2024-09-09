package com.odev1.tictactoe;

public class Player {
    private final String name;
    private final String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol; }}
