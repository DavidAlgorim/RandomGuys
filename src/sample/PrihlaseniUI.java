package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import sample.db.*;

public class PrihlaseniUI {

    private Scene scene;
    private TextField username = new TextField();
    private Label usernameLabel = new Label();
    private PasswordField password = new PasswordField();
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

    private Main main;

    private Uzivatel uzivatel;
    private Spravce spravce;
    private Admin admin;

    public void nactiUI(Stage stage, Main main){
        this.main = main;
        prihlaseniUI();
        registraceUI();

        stage.setScene(scene);
        stage.show();


        loginButton.setOnMouseClicked(event ->  {
            Osoba prihlasenaOsoba = Prihlaseni.prihlasitSe(getUsername().getText(), getPassword().getText());
            prihlasovani(prihlasenaOsoba);
        });

        registerText.setOnMouseClicked(event -> {
            stage.setScene(registerScene);
        });

        registerBackButton.setOnMouseClicked(event -> {
            stage.setScene(scene);
        });
        registerButton.setOnMouseClicked(event -> {

            // Hashování hesla
            String hashed = BCrypt.hashpw(getRegisterPassword().getText(), BCrypt.gensalt());
            Databaze.insertNewUzivatel(getRegisterName().getText() + " " + getRegisterSurname().getText(),
                    getRegisterEmail().getText(), getRegisterUsername().getText(), hashed);


            getRegisterName().clear();
            getRegisterSurname().clear();
            getRegisterUsername().clear();
            getRegisterEmail().clear();
            getRegisterPassword().clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Vaše registrace byla úspěšně potvrzena.");
            alert.showAndWait();

            stage.setScene(scene);
        });

        loginBackButton.setOnMouseClicked(event -> {
            this.main.zobrazMainMenuUI();
        });
    }



    public void prihlasovani(Osoba osoba){
        // Pracoval bych spíše s username, takže jen všude nahradit username za username
        if(osoba instanceof Uzivatel){
            // Zobrazit UI pro přihlášené
            System.out.println("uzivatel");
            uzivatel = (Uzivatel) osoba;
            main.uiPodleUsera(uzivatel, null, null);
        }
        else if(osoba instanceof Admin){
            // Zobrazit UI pro admina
            System.out.println("admin");
            admin = (Admin) osoba;
            main.uiPodleUsera(null, null, admin);
        }
        else if(osoba instanceof Spravce) {
            // Zobrazit UI pro správce
            System.out.println("spravce");
            spravce = (Spravce) osoba;
            main.uiPodleUsera(null,spravce,null);
        }
    }



    private void prihlaseniUI(){
        if (scene == null)
        {
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
            emailHbox.getChildren().addAll(usernameLabel, getUsername());
            passwordHbox.getChildren().addAll(passwordLabel, getPassword());
            usernameLabel.setText("Username:  ");
            usernameLabel.setPrefWidth(70);
            passwordLabel.setText("Heslo:  ");
            passwordLabel.setPrefWidth(70);
            loginBackButton.setText("Zpět");
            loginBackButton.setPrefWidth(220);
            scene = new Scene(borderPane, 1200,720);
        }
    }



    private void registraceUI(){
        if(registerScene == null)
        {
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
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
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
