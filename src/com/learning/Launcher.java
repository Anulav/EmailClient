package com.learning;

import com.learning.view.EmailManager;
import com.learning.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
       /* Button btn = new Button();
        btn.setText("Hello World");
        btn.setOnAction(event -> System.out.println("Hello world"));
*/
//      Parent parent = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));

/*
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(btn);
*/

/*      Scene scene = new Scene(parent);  //The JavaFX Scene class is the container for all content in a scene graph. Here parent is root node of scene graph.
        stage.setTitle("EmailClient");
        stage.setScene(scene);
        stage.show();*/

        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLogin();
    }
}
