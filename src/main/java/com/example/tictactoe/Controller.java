package com.example.tictactoe;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
    @FXML
    Pane rootPane, gridPane, infoPane, winDrawPane, X01, X02, X03, X04, X05, X06, X07, X08, X09;
    @FXML
    Label infoLabel;
    @FXML
    Button newGameButton, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    Line topHorLine, bottomHorLine, leftVerLine, rightVerLine;
    @FXML
    CheckBox playAgainstAI;
    Button[] buttons;
    Pane[] X0panes;
    Draw draw;
    GameLogic game;

    double xOffset, yOffset;

    @FXML
    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    @FXML
    private void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    public void initialize() {
        buttons = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        X0panes = new Pane[]{X01, X02, X03, X04, X05, X06, X07, X08, X09};
        draw = new Draw(X0panes, winDrawPane, infoPane, newGameButton);
        game = new GameLogic();
        draw.gridAnimation(topHorLine, bottomHorLine, leftVerLine, rightVerLine);
    }
    public void onButtonPressed ( int i){

            if(!game.isEmptyCell(i)) return ;

            game.board[i] = game.player;
            draw.drawX0(i, game.player, X0panes);

            int result = game.isWin();
            if (result != -1) {
                draw.drawWinLine(Constants.WIN_LINE[result], winDrawPane);
                gameOver((game.player ==  1 ? "X" : 0 )+ " win!");
            }else if (game.isDraw()) {
                gameOver("Draw!");
            } else {
                game.nextTurn();
                if(playAgainstAI.isSelected()) {
                    if (game.player == 0) {
                        int nextMove = AI.AIplayer(game);
                        PauseTransition pause = new PauseTransition(Duration.millis(500));
                        pause.setOnFinished(event -> onButtonPressed(nextMove));
                        pause.play();
                    }
                }
            }
        }
    public void gameOver(String s){
        draw.showGameResult(infoPane, winDrawPane, s, newGameButton);
    }
    public void btn1 () {
        onButtonPressed(0);
    }
    public void btn2 () {
        onButtonPressed(1);
    }
    public void btn3 () {
        onButtonPressed(2);
    }
    public void btn4 () {
        onButtonPressed(3);
    }
    public void btn5 () {
        onButtonPressed(4);
    }
    public void btn6 () {
        onButtonPressed(5);
    }
    public void btn7 () {
        onButtonPressed(6);
    }
    public void btn8 () {
        onButtonPressed(7);
    }
    public void btn9 () {
        onButtonPressed(8);
    }
    public void exitGame(){
        System.exit(0);
    }

}

