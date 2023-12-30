package com.example.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Draw {
    public Draw(Pane[] X0panes, Pane winDrawPane, Pane infoPane, Button newGameButton) {
        winDrawPane.getChildren().clear();
        winDrawPane.setVisible(false);

        infoPane.getChildren().clear();
        infoPane.setVisible(false);

        newGameButton.setVisible(false);

        for (Pane pane : X0panes) {
            pane.getChildren().clear();
        }
    }
    public void gridAnimation(Line topHorLine,Line bottomHorLine, Line leftVerLine, Line rightVerLine) {
        final int LINE_LENGTH = 150;
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
                new KeyValue(topHorLine.endXProperty(), LINE_LENGTH + topHorLine.getEndX()),
                new KeyValue(topHorLine.startXProperty(), -(LINE_LENGTH - topHorLine.getStartX())),

                new KeyValue(bottomHorLine.endXProperty(), LINE_LENGTH + bottomHorLine.getEndX()),
                new KeyValue(bottomHorLine.startXProperty(), -(LINE_LENGTH - bottomHorLine.getStartX())),

                new KeyValue(leftVerLine.endYProperty(), LINE_LENGTH + leftVerLine.getEndY()),
                new KeyValue(leftVerLine.startYProperty(), -(LINE_LENGTH - leftVerLine.getStartY())),

                new KeyValue(rightVerLine.endYProperty(), LINE_LENGTH + rightVerLine.getEndY()),
                new KeyValue(rightVerLine.startYProperty(), -(LINE_LENGTH - rightVerLine.getStartY()))

        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    public void showGameResult(Pane infoPane, Pane winDrawPane, String s, Button newGameButton){
        winDrawPane.setVisible(true);
        infoPane.setVisible(true);
        Label gameResult = new Label();
        gameResult.setPrefSize(360.0, 360.0);
        gameResult.setId("gameResult");
        gameResult.setText(s);
        infoPane.getChildren().add(gameResult);
        newGameButton.setVisible(true);
    }
    public void drawWinLine(int[] winLine, Pane winDrawPane){
        Line line = new Line(winLine[0], winLine[1], winLine[0], winLine[1]);
        line.setStrokeWidth(7);
        line.setId("winLine");


        // Add lines to the root pane
        winDrawPane.getChildren().addAll(line);
        Timeline timeline = new Timeline();

        // Add a keyframe to the timeline
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                new KeyValue(line.endYProperty(), winLine[3]),
                new KeyValue(line.startYProperty(), winLine[1]),
                new KeyValue(line.endXProperty(), winLine[2]),
                new KeyValue(line.startXProperty(), winLine[0])
        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    public void drawX0 ( int i, int player, Pane[] X0panes){
        if (player == 1) {
            drawX(i, X0panes);
        } else {
            draw0(i, X0panes);
        }
    }
    public void drawX ( int i, Pane[] X0panes){
        final int START = 25;
        final int END = 75;
        Line line1 = new Line(START, START, START, START);
        line1.setId("XDraw1");
        Line line2 = new Line(END, START, END, START);
        line2.setId("XDraw2");

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
    public void draw0 ( int i, Pane[] X0panes){
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
}
