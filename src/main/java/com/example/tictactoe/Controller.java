package com.example.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    @FXML
    AnchorPane rootPane;

    @FXML
    Pane gridPane,infoPane,winDrawPane, X01, X02, X03, X04, X05, X06, X07, X08, X09;
    @FXML
    Label infoLabel;

    @FXML
    private Button newGame, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private final int LENGTH = 9;
    private final int TOP = 20, BOTTOM = 280, LEFT = 50, MIDDLE = 150, RIGHT = 250 ;
    Button[] buttons;
    Pane[] X0panes;
    boolean gameOver;
    String[] matrix = new String[LENGTH];
    String player ;
    private static final String SOUND_FILE = "D:\\41n\\java\\TicTacToe\\src\\main\\resources\\com\\example\\tictactoe\\tap.mp3"; // Replace with the path to your sound file

    int[][] WIN_LINE = new int[8][4];
    int count = 0;
    @FXML
    Line topHorLine, bottomHorLine, leftVerLine, rightVerLine;

    Set<Integer> isDraw ;

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
        gameOver = false;
        player = "X";
        WIN_LINE[0] = new int[]{TOP, TOP, BOTTOM, BOTTOM};
        WIN_LINE[1] = new int[]{BOTTOM, TOP, TOP, BOTTOM};
        WIN_LINE[2] = new int[]{LEFT, TOP, LEFT, BOTTOM};
        WIN_LINE[3] = new int[]{MIDDLE, TOP, MIDDLE, BOTTOM};
        WIN_LINE[4] = new int[]{RIGHT, TOP, RIGHT, BOTTOM};
        WIN_LINE[5] = new int[]{TOP, LEFT, BOTTOM, LEFT};
        WIN_LINE[6] = new int[]{TOP, MIDDLE, BOTTOM, MIDDLE};
        WIN_LINE[7] = new int[]{TOP, RIGHT, BOTTOM, RIGHT};
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = "z" + i;
        }
        for (int i = 0; i < LENGTH; i++) {
            X0panes[i].getChildren().clear();
        }

        infoPane.getChildren().clear();
        winDrawPane.getChildren().clear();
        infoLabel.setText(player + " turn");
        gridAnimation();
        isDraw = new HashSet<>();
        winDrawPane.setVisible(false);
        infoPane.setVisible(false);
        newGame.setVisible(false);

    }

    public void startNewGame () {
        initialize();


    }
    public void drawX0 ( int i){
        if (player.equals("X")) {
            drawX(i);
        } else {
            draw0(i);
        }
    }
    public void drawX ( int i){
        final int START = 25;
        final int END = 75;
        Line line1 = new Line(START, START, START, START);
        line1.setStrokeWidth(5);
        line1.setId("XDraw1");        // Create the second diagonal line (from top-right to bottom-left)
        Line line2 = new Line(END, START, END, START);
        line2.setStrokeWidth(5);
        line2.setId("XDraw2");

        // Add lines to the root pane
        X0panes[i].getChildren().addAll(line1, line2);
        Timeline timeline = new Timeline();

        // Add a keyframe to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(200),
                new KeyValue(line1.endYProperty(), END),
                new KeyValue(line1.startYProperty(), START),
                new KeyValue(line1.endXProperty(), END),
                new KeyValue(line1.startXProperty(), START),

                new KeyValue(line2.endYProperty(), END),
                new KeyValue(line2.startYProperty(), START),
                new KeyValue(line2.endXProperty(), START),
                new KeyValue(line2.startXProperty(), END)


        );
        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Set the cycle count for the animation (indefinite for continuous animation)
        //timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }
    public void draw0 ( int i){
        // Create an arc (round line)
        Arc arc = new Arc(50, 50, 33, 33, 0, 0);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.web("#F2EBD3"));
        arc.setStrokeWidth(7);
        arc.setType(ArcType.OPEN);

        // Add the arc to the root pane
        X0panes[i].getChildren().add(arc);

        // Create a timeline for the animation
        Timeline timeline = new Timeline();

        // Add a keyframe to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(300),
                new KeyValue(arc.lengthProperty(), -360)
        );

        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Play the animation
        timeline.play();
    }
    public void drawWinLine(int[] winLine){
        Line line1 = new Line(winLine[0], winLine[1], winLine[0], winLine[1]);
        line1.setStrokeWidth(7);
        line1.setId("winLine");


        // Add lines to the root pane
        winDrawPane.getChildren().addAll(line1);
        Timeline timeline = new Timeline();

        // Add a keyframe to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                new KeyValue(line1.endYProperty(), winLine[3]),
                new KeyValue(line1.startYProperty(), winLine[1]),
                new KeyValue(line1.endXProperty(), winLine[2]),
                new KeyValue(line1.startXProperty(), winLine[0])
        );
        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Set the cycle count for the animation (indefinite for continuous animation)
        //timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }
    private void playSound() {
/*
        Media sound = new Media(new File(SOUND_FILE).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

 */
    }
    public void gridAnimation() {
        final int LINELENGTH = 150;
        final double startX = 0.0;
        final double startY = 0.0;
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
                new KeyValue(topHorLine.endXProperty(), LINELENGTH + topHorLine.getEndX()),
                new KeyValue(topHorLine.startXProperty(), -(LINELENGTH - topHorLine.getStartX())),

                new KeyValue(bottomHorLine.endXProperty(), LINELENGTH + bottomHorLine.getEndX()),
                new KeyValue(bottomHorLine.startXProperty(), -(LINELENGTH - bottomHorLine.getStartX())),

                new KeyValue(leftVerLine.endYProperty(), LINELENGTH + leftVerLine.getEndY()),
                new KeyValue(leftVerLine.startYProperty(), -(LINELENGTH - leftVerLine.getStartY())),

                new KeyValue(rightVerLine.endYProperty(), LINELENGTH + rightVerLine.getEndY()),
                new KeyValue(rightVerLine.startYProperty(), -(LINELENGTH - rightVerLine.getStartY()))

        );
        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Set the cycle count for the animation (indefinite for continuous animation)
        //timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }

    private void onButtonPressed ( int i){

            if (matrix[i].equals("X") || matrix[i].equals("0") || gameOver) return;
            playSound();
            matrix[i] = player;
            drawX0(i);
            int result = isWin();
            if (result != -1) {
                drawWinLine(WIN_LINE[result]);
                gameOver(player + " win!");
                //infoLabel.setText(player + " win!!!!");
            }else if (isDraw(i)) {
                gameOver("Draw!");
                infoLabel.setText("Draw!");

            } else {
                nextTurn();
                infoLabel.setText(player + " turn");
            }


        }

        public void gameOver(String s){
            gameOver = true;
            winDrawPane.setVisible(true);
            infoPane.setVisible(true);
            newGame.setVisible(true);
            Label gameResult = new Label();
            gameResult.setPrefSize(360.0, 360.0);
            gameResult.setId("gameResult");
            gameResult.setText(s);
            infoPane.getChildren().add(gameResult);

        }

        public int isWin () {
            if(matrix[0].equals(matrix[1]) && matrix[1].equals(matrix[2]))
                return 5;
            if(matrix[3].equals(matrix[4]) && matrix[4].equals(matrix[5]))
                return 6;
            if(matrix[6].equals(matrix[7]) && matrix[7].equals(matrix[8]))
                return 7;


            if(matrix[0].equals(matrix[3]) && matrix[3].equals(matrix[6]))
                return 2;
            if(matrix[1].equals(matrix[4]) && matrix[4].equals(matrix[7]))
                return 3;
            if(matrix[2].equals(matrix[5]) && matrix[5].equals(matrix[8]))
                return 4;

            if(matrix[0].equals(matrix[4]) && matrix[4].equals(matrix[8]))
                return 0;
            if(matrix[2].equals(matrix[4]) && matrix[4].equals(matrix[6]))
                return 1;
            return -1;
        }
        public boolean isDraw ( int i){
            isDraw.add(i);
            return isDraw.size() == 9;
        }


        private void nextTurn () {
            player = player.equals("X") ? "0" : "X";
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

