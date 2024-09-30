package com.system.demo.controllers;

import com.system.demo.controllers.dashboard.TableViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        BorderPane mainLayout = loader.load();

        if (loader.getController() instanceof TableViewController) {
            TableViewController controller = loader.getController();
            controller.setUserRole("admin");
        }

        Scene scene = new Scene(mainLayout);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
