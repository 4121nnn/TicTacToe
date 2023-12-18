package com.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TicTacToe");
        stage.setResizable(false);
        stage.getIcons().add(new Image("D:\\41n\\java\\TicTacToe\\src\\main\\resources\\com\\example\\tictactoe\\tictactoe.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}