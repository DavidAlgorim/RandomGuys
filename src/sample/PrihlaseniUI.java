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
    private TextField registerUsername = new TextField();
    private TextField registerPassword = new TextField();
    private HBox registerNameHbox = new HBox();
    private HBox registerSurNameHbox = new HBox();
    private HBox registerEmailHbox = new HBox();
    private HBox registerUsernameHbox = new HBox();
    private HBox registerPasswordHbox = new HBox();
    private Label registerNameLabel = new Label();
    private Label registerSurnameLabel = new Label();
    private Label registerEmailLabel = new Label();
    private Label registerUsernameLabel = new Label();
    private Label registerPasswordLabel = new Label();
    private Button registerButton = new Button();
    private Button registerBackButton = new Button();

    public void nactiUI(Stage stage){
        prihlaseniUI();
        registraceUI();

        stage.setScene(scene);
        stage.show();


        loginButton.setOnMouseClicked(event ->  {
            prihlasovani();
        });

        registerText.setOnMouseClicked(event -> {
            stage.setScene(registerScene);
        });

        registerBackButton.setOnMouseClicked(event -> {
            stage.setScene(scene);
        });
        registerButton.setOnMouseClicked(event -> {
            new Neregistrovany().registrujSe(getRegisterName().getText() + " " + getRegisterSurname().getText(),
                    getRegisterEmail().getText(), getRegisterUsername().getText(),getRegisterPassword().getText());
        });
    }



    public void prihlasovani(){
        // Pracoval bych spíše s username, takže jen všude nahradit email za username
        Osoba uzivatel = Prihlaseni.prihlasitSe(getEmail().getText(), getPassword().getText());
        if(uzivatel instanceof Uzivatel){
            // Zobrazit UI pro přihlášené
        }
        else if(uzivatel instanceof Spravce){
            // Zobrazit UI pro správce
        }
        else if(uzivatel instanceof Admin){
            // Zobrazit UI pro admina
        }
        else{
            // Zobrazit uživateli alert, že je chybné jméno nebo heslo
        }
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
        emailHbox.getChildren().addAll(emailLabel, getEmail());
        passwordHbox.getChildren().addAll(passwordLabel, getPassword());
        emailLabel.setText("Email:  ");
        emailLabel.setPrefWidth(70);
        passwordLabel.setText("Heslo:  ");
        passwordLabel.setPrefWidth(70);
        loginBackButton.setText("Zpět");
        loginBackButton.setPrefWidth(220);
        scene = new Scene(borderPane, 1200,720);
    }



    private void registraceUI(){
        registerBorderPane.setCenter(registerCenterVbox);
        registerBorderPane.setTop(registerTopHbox);
        registerTitleLabel.setFont(Font.font("Arial",32));
        registerTitleLabel.setText("Registrace");
        registerTopHbox.getChildren().add(registerTitleLabel);
        registerTopHbox.setAlignment(Pos.CENTER);
        registerTopHbox.setPadding(new Insets(20));
        registerCenterVbox.getChildren().addAll(registerNameHbox, registerSurNameHbox, registerEmailHbox, registerUsernameHbox, registerPasswordHbox, registerButton, registerBackButton);
        registerCenterVbox.setSpacing(10);
        registerCenterVbox.setAlignment(Pos.CENTER);
        registerCenterVbox.setMaxWidth(220);
        registerNameHbox.getChildren().addAll(registerNameLabel, getRegisterName());
        registerSurNameHbox.getChildren().addAll(registerSurnameLabel, getRegisterSurname());
        registerEmailHbox.getChildren().addAll(registerEmailLabel, getRegisterEmail());
        registerUsernameHbox.getChildren().addAll(registerUsernameLabel, getRegisterUsername());
        registerPasswordHbox.getChildren().addAll(registerPasswordLabel, getRegisterPassword());
        registerNameLabel.setText("Jméno:  ");
        registerNameLabel.setPrefWidth(70);
        registerSurnameLabel.setText("Příjmení:  ");
        registerSurnameLabel.setPrefWidth(70);
        registerEmailLabel.setText("Email:  ");
        registerEmailLabel.setPrefWidth(70);
        registerUsernameLabel.setText("Uživatelské jméno:  ");
        registerUsernameLabel.setPrefWidth(70);
        registerPasswordLabel.setText("Heslo:  ");
        registerPasswordLabel.setPrefWidth(70);
        registerButton.setText("Registrace");
        registerButton.setPrefWidth(220);
        registerBackButton.setText("Zpět");
        registerBackButton.setPrefWidth(220);
        registerScene = new Scene(registerBorderPane, 1200,720);
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    public TextField getRegisterName() {
        return registerName;
    }

    public TextField getRegisterSurname() {
        return registerSurname;
    }

    public TextField getRegisterEmail() {
        return registerEmail;
    }

    public TextField getRegisterPassword() {
        return registerPassword;
    }

    public TextField getRegisterUsername() {
        return registerUsername;
    }
}
