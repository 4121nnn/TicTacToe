package com.example.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Controller {
    @FXML
    AnchorPane rootPane;
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
    @FXML
    Line topHorLine, bottomHorLine, leftVerLine, rightVerLine;
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
        gridAnimation();
        draw0(1);

    }

    public void startNewGame(){
        initialize();

    }
    public void drawX(int i){
        Line line1 = new Line(50, 50, 50, 50);
        line1.setStroke(Color.BLUE);
        line1.setStrokeWidth(3);

        // Create the second diagonal line (from top-right to bottom-left)
        Line line2 = new Line(100, 50, 100, 50);
        line2.setStroke(Color.BLUE);
        line2.setStrokeWidth(3);

        // Add lines to the root pane
        rootPane.getChildren().addAll(line1, line2);
        Timeline timeline = new Timeline();

        // Add a keyframe to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(600),
                new KeyValue(line1.endYProperty(),  50) ,
                new KeyValue(line1.startYProperty(),  100),
                new KeyValue(line1.endXProperty(),  50) ,
                new KeyValue(line1.startXProperty(),  100),

                new KeyValue(line2.endYProperty(),  50) ,
                new KeyValue(line2.startYProperty(),  100),
                new KeyValue(line2.endXProperty(),  100) ,
                new KeyValue(line2.startXProperty(),  50)


        );
        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Set the cycle count for the animation (indefinite for continuous animation)
        //timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }

    public void draw0(int i){
        // Create an arc (round line)
        Arc arc = new Arc(70, 70, 38, 38, 0, 0);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLUE);
        arc.setStrokeWidth(3);
        arc.setType(ArcType.OPEN);

        // Add the arc to the root pane
        rootPane.getChildren().add(arc);

        // Create a timeline for the animation
        Timeline timeline = new Timeline();

        // Add a keyframe to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(600),
                new KeyValue(arc.lengthProperty(), -360)
        );

        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Play the animation
        timeline.play();
    }
    public void gridAnimation(){
        final int LINELENGTH = 150;
        final double startX = 30.0;
        final double startY = 30.0;
        topHorLine.setStartX(startX);
        topHorLine.setEndX(startX);
        bottomHorLine.setStartX(startX);
        bottomHorLine.setEndX(startX);

        leftVerLine.setStartY(startY);
        leftVerLine.setEndY(startY);
        rightVerLine.setStartY(startY);
        rightVerLine.setEndY(startY);
        Timeline timeline = new Timeline();

        // Add a keyframe to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(700),
                new KeyValue(topHorLine.endXProperty(), LINELENGTH + topHorLine.getEndX()) ,
                new KeyValue(topHorLine.startXProperty(),  -(LINELENGTH - topHorLine.getStartX())),

                new KeyValue(bottomHorLine.endXProperty(), LINELENGTH + bottomHorLine.getEndX()) ,
                new KeyValue(bottomHorLine.startXProperty(),  -(LINELENGTH - bottomHorLine.getStartX())),

                new KeyValue(leftVerLine.endYProperty(), LINELENGTH + leftVerLine.getEndY()) ,
                new KeyValue(leftVerLine.startYProperty(),  -(LINELENGTH - leftVerLine.getStartY())),

                new KeyValue(rightVerLine.endYProperty(), LINELENGTH + rightVerLine.getEndY()) ,
                new KeyValue(rightVerLine.startYProperty(),  -(LINELENGTH - rightVerLine.getStartY()))

        );
        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Set the cycle count for the animation (indefinite for continuous animation)
        //timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }











    private void onButtonPressed(int i){

        if(matrix[i].equals("X") || matrix[i].equals("0") || gameOver) return;

        //buttons[i].setText(player);
        matrix[i] = player;
        drawX(i);
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

