package com.yourname.tictactoe;

public class AIPlayer extends Player {
    public AIPlayer(String name, String symbol) {
        super(name, symbol);
    }

    public void makeMove(Game game) {
        int[] bestMove = findBestMove(game);
        game.makeMove(bestMove[0], bestMove[1], this.getSymbol());
    }

    private int[] findBestMove(Game game) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.isCellEmpty(i, j)) {
                    game.makeMove(i, j, this.getSymbol());
                    int moveVal = minimax(game, 0, false);
                    game.undoMove(i, j);

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }}}}
      
        return bestMove;
    }

    private int minimax(Game game, int depth, boolean isMax) {
        int score = game.evaluate();

        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (!game.isMovesLeft()) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.isCellEmpty(i, j)) {
                        game.makeMove(i, j, this.getSymbol());
                        best = Math.max(best, minimax(game, depth + 1, false));
                        game.undoMove(i, j); }}}
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.isCellEmpty(i, j)) {
                        game.makeMove(i, j, game.getOpponentSymbol(this.getSymbol()));
                        best = Math.min(best, minimax(game, depth + 1, true));
                        game.undoMove(i, j); }}}
            return best;
        }}}
