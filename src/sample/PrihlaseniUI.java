package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PrihlaseniUI {

    private Scene scene;
    private TextField email = new TextField();
    private Label emailLabel = new Label();
    private TextField password = new TextField();
    private Label passwordLabel = new Label();
    private BorderPane borderPane = new BorderPane();
    private VBox centerVbox = new VBox();
    private HBox topHbox = new HBox();
    private HBox emailHbox = new HBox();
    private HBox passwordHbox = new HBox();
    private Label titleLabel = new Label();
    private Button loginButton = new Button();
    private Button loginBackButton = new Button();
    private Hyperlink registerText = new Hyperlink("Registrace");

    //registrace
    private Scene registerScene;
    private BorderPane registerBorderPane = new BorderPane();
    private VBox registerCenterVbox = new VBox();
    private HBox registerTopHbox = new HBox();
    private Label registerTitleLabel = new Label();
    private TextField registerName = new TextField();
    private TextField registerSurname = new TextField();
    private TextField registerEmail = new TextField();
    private TextField registerPassword = new TextField();
    private HBox registerNameHbox = new HBox();
    private HBox registerSurNameHbox = new HBox();
    private HBox registerEmailHbox = new HBox();
    private HBox registerPasswordHbox = new HBox();
    private Label registerNameLabel = new Label();
    private Label registerSurnameLabel = new Label();
    private Label registerEmailLabel = new Label();
    private Label registerPasswordLabel = new Label();
    private Button registerButton = new Button();
    private Button registerBackButton = new Button();

    public void nactiUI(Stage stage){
        prihlaseniUI();
        registraceUI();

        stage.setScene(scene);
        stage.show();

        registerText.setOnMouseClicked(event -> {
            stage.setScene(registerScene);
        });

        registerBackButton.setOnMouseClicked(event -> {
            stage.setScene(scene);
        });
    }


    private void prihlaseniUI(){
        borderPane.setCenter(centerVbox);
        borderPane.setTop(topHbox);
        topHbox.getChildren().add(titleLabel);
        topHbox.setAlignment(Pos.CENTER);
        topHbox.setPadding(new Insets(20));
        titleLabel.setFont(Font.font("Arial",32));
        titleLabel.setText("Přihlášení");
        loginButton.setText("Přihlásit");
        loginButton.setPrefWidth(220);
        centerVbox.getChildren().addAll(emailHbox, passwordHbox, loginButton, loginBackButton, registerText);
        centerVbox.setSpacing(10);
        centerVbox.setAlignment(Pos.CENTER);
        centerVbox.setMaxWidth(220);
        emailHbox.getChildren().addAll(emailLabel,email);
        passwordHbox.getChildren().addAll(passwordLabel,password);
        emailLabel.setText("Email:  ");
        emailLabel.setPrefWidth(70);
        passwordLabel.setText("Heslo:  ");
        passwordLabel.setPrefWidth(70);
        loginBackButton.setText("Zpět");
        loginBackButton.setPrefWidth(220);
        scene = new Scene(borderPane, 1200,720);
    }


    public String getEmail() {
        return email.getText();
    }

    public String getPassword() {
        return password.getText();
    }


    private void registraceUI(){
        registerBorderPane.setCenter(registerCenterVbox);
        registerBorderPane.setTop(registerTopHbox);
        registerTitleLabel.setFont(Font.font("Arial",32));
        registerTitleLabel.setText("Registrace");
        registerTopHbox.getChildren().add(registerTitleLabel);
        registerTopHbox.setAlignment(Pos.CENTER);
        registerTopHbox.setPadding(new Insets(20));
        registerCenterVbox.getChildren().addAll(registerNameHbox, registerSurNameHbox, registerEmailHbox, registerPasswordHbox, registerButton, registerBackButton);
        registerCenterVbox.setSpacing(10);
        registerCenterVbox.setAlignment(Pos.CENTER);
        registerCenterVbox.setMaxWidth(220);
        registerNameHbox.getChildren().addAll(registerNameLabel, registerName);
        registerSurNameHbox.getChildren().addAll(registerSurnameLabel, registerSurname);
        registerEmailHbox.getChildren().addAll(registerEmailLabel, registerEmail);
        registerPasswordHbox.getChildren().addAll(registerPasswordLabel, registerPassword);
        registerNameLabel.setText("Jméno:  ");
        registerNameLabel.setPrefWidth(70);
        registerSurnameLabel.setText("Příjmení:  ");
        registerSurnameLabel.setPrefWidth(70);
        registerEmailLabel.setText("Email:  ");
        registerEmailLabel.setPrefWidth(70);
        registerPasswordLabel.setText("Heslo:  ");
        registerPasswordLabel.setPrefWidth(70);
        registerButton.setText("Registrace");
        registerButton.setPrefWidth(220);
        registerBackButton.setText("Zpět");
        registerBackButton.setPrefWidth(220);
        registerScene = new Scene(registerBorderPane, 1200,720);
    }

}
