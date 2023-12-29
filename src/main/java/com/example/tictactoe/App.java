package com.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        stage.setTitle("TicTacToe");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("D:\\41n\\java\\TicTacToe\\src\\main\\resources\\com\\example\\tictactoe\\tictactoe.png"));
        stage.setScene(scene);
        stage.show();
    }
    private MenuBar createCustomMenuBar() {
        MenuBar menuBar = new MenuBar();

        // Create menus and menu items
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> System.exit(0));

        fileMenu.getItems().add(exitMenuItem);

        // Add menus to the menu bar
        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    public static void main(String[] args) {
        launch();
    }
}