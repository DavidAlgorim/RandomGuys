package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1400, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
