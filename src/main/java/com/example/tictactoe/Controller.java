package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    Label infoLabel;

    @FXML
    private Button  btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private final int LENGTH = 9;
    Button[] buttons = new Button[LENGTH];
    boolean gameOver;
    String[] matrix = new String[LENGTH];
    String player ;
    int count = 0;

    public void initialize() {
        buttons[0] = btn1;
        buttons[1] = btn2;
        buttons[2] = btn3;
        buttons[3] = btn4;
        buttons[4] = btn5;
        buttons[5] = btn6;
        buttons[6] = btn7;
        buttons[7] = btn8;
        buttons[8] = btn9;
        gameOver = false;
        player = "X";

        for(int i = 0; i < matrix.length; i++){
            matrix[i] = "" + i;
            buttons[i].setText("");
        }
        infoLabel.setText(player + " turn");
    }

    public void startNewGame(){
        initialize();
    }


    private void onButtonPressed(int i){

        if(!buttons[i].getText().isEmpty() || gameOver) return;

        buttons[i].setText(player);
        matrix[i] = player;
        if(isWin()){
            gameOver = true;
            infoLabel.setText(player + " win!!!!");
        }else{
            nextTurn();
            infoLabel.setText(player + " turn");
        }


    }
    public boolean isWin(){
        return (matrix[0].equals(matrix[1]) && matrix[1].equals(matrix[2]) ||
                matrix[3].equals(matrix[4]) && matrix[4].equals(matrix[5]) ||
                matrix[6].equals(matrix[7]) && matrix[7].equals(matrix[8]) ||

                matrix[0].equals(matrix[3]) && matrix[3].equals(matrix[6]) ||
                matrix[1].equals(matrix[4]) && matrix[4].equals(matrix[7]) ||
                matrix[2].equals(matrix[5]) && matrix[5].equals(matrix[8]) ||

                matrix[0].equals(matrix[4]) && matrix[4].equals(matrix[8]) ||
                matrix[2].equals(matrix[4]) && matrix[4].equals(matrix[6]) );
    }

    private void nextTurn(){
        player = player.equals("X") ? "0" : "X";
    }
    public void btn1() {
        onButtonPressed(0);
    }
    public void btn2() {
        onButtonPressed(1);
    }
    public void btn3() {
        onButtonPressed(2);
    }
    public void btn4() {
        onButtonPressed(3);
    }
    public void btn5() {
        onButtonPressed(4);
    }
    public void btn6() {
        onButtonPressed(5);
    }
    public void btn7() {
        onButtonPressed(6);
    }
    public void btn8() {
        onButtonPressed(7);
    }
    public void btn9() {
        onButtonPressed(8);
    }



}

