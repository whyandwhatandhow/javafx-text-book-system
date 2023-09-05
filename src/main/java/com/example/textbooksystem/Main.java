package com.example.textbooksystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("index.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(load,701,501));
        //stage.setScene(new Scene(load,1013,700));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
