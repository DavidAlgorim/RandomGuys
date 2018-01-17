package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

public class Main extends Application {

    private Button buyTicket = new Button();
    private Label titleLabel = new Label();
    private Button loginButton = new Button();
    private HBox topHbox = new HBox();
    private VBox centerVbox = new VBox();

    @Override
    public void start(Stage primaryStage){
        loginButton.setText("Login");
        topHbox.setPadding(new Insets(20));
        buyTicket.setText("Nákup lístků");
        titleLabel.setText("Ticketstream");
        titleLabel.setFont(Font.font("Arial",32));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topHbox);
        borderPane.setCenter(centerVbox);

        topHbox.setAlignment(Pos.BASELINE_RIGHT);
        topHbox.getChildren().add(loginButton);

        centerVbox.getChildren().addAll(titleLabel,buyTicket);
        centerVbox.setAlignment(Pos.CENTER);
        centerVbox.setSpacing(200);

        primaryStage.setTitle("RandomGuys");
        primaryStage.setScene(new Scene(borderPane, 1400, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
