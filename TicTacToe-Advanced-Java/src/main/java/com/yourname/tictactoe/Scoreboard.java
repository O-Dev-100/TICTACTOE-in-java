package com.yourname.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
    private final Map<Player, Integer> wins = new HashMap<>();
    private int draws;

    public void incrementWins(Player player) {
        wins.put(player, wins.getOrDefault(player, 0) + 1);
    }

    public void incrementDraws() {
        draws++;
    }

    public int getWins(Player player) {
        return wins.getOrDefault(player, 0);
    }

    public int getDraws() {
        return draws; }}
