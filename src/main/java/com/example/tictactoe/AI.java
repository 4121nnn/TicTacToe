package com.example.tictactoe;

public class AI {

    public static int AIplayer(GameLogic game){
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < game.board.length; i++) {
            if ( !game.isEmptyCell(i)) continue;

            game.board[i] = 0;
            int score = minimax(game,  false);
            game.board[i] = -1;

            if (score > bestScore) {
                bestScore = score;
                bestMove = i;
            }
        }
        return bestMove;
    }
    public static int minimax(GameLogic game,  boolean isMax) {
        if (game.isWin() != -1) {
            return !isMax ? 1 : -1;
        }
        if (game.isDraw()) {
            return 0;
        }
        if (isMax) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < game.board.length; i++) {
                if (game.isEmptyCell(i)) {
                    game.board[i] = 0;
                    int score = minimax(game, false);
                    game.board[i] = -1;
                    bestScore = Math.max(bestScore, score);
                }

            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < game.board.length; i++) {
                if (game.board[i] == -1) {
                    game.board[i] = 1;
                    int score = minimax(game, true);
                    game.board[i] = -1;
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }
}
