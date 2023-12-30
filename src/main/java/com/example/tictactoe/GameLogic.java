package com.example.tictactoe;



public class GameLogic {
    int[] board ;
    int player ;
    public GameLogic(){
        board = new int[Constants.LENGTH];
        player = 1;
        for (int i = 0; i < Constants.LENGTH; i++) {
            board[i] = -1;
        }
    }

    public boolean isEmptyCell(int i){
        return board[i] == -1;
    }
    public int isWin () {
        if((board[0] == 1 || board[0] == 0) && board[0] == board[1] && board[1] == board[2])
            return 5;
        if((board[3] == 1 || board[3] == 0) && board[3] == board[4] && board[4] == board[5])
            return 6;
        if((board[6] == 1 || board[6] == 0) &&board[6] == board[7] && board[7] == board[8])
            return 7;


        if((board[0] == 1 || board[0] == 0) && board[0] == board[3] && board[3] == board[6])
            return 2;
        if((board[1] == 1 || board[1] == 0)  && board[1] == board[4] && board[4] == board[7])
            return 3;
        if((board[2] == 1 || board[2] == 0)  && board[2] == board[5] && board[5] == board[8])
            return 4;

        if((board[0] == 1 || board[0] == 0)  && board[0] == board[4] && board[4] == board[8])
            return 0;
        if((board[2] == 1 || board[2] == 0)  && board[2] == board[4] && board[4] == board[6])
            return 1;

        return -1;
    }
    public boolean isDraw (){
        for(int i : board){
            if(i == -1) return false;
        }
        return true;
    }
    void nextTurn () {
        player = player == 1 ? 0 : 1;
    }
}
