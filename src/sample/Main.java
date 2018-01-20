package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Button showEvent = new Button();
    private Stage mainStage = new Stage();
    private PrihlaseniUI prihlaseniUI = new PrihlaseniUI();
    private SeznamEventuUI seznamEventuUI = new SeznamEventuUI();
    //neregistrovaný
    private Scene guestScene;
    private BorderPane guestBorderPane = new BorderPane();
    private Label guestTitleLabel = new Label();
    private Button guestLoginButton = new Button();
    private HBox guestTopHbox = new HBox();
    private VBox guestCenterVbox = new VBox();

    //registrovaný
    private Scene registredScene;
    private BorderPane registredBorderPane = new BorderPane();
    private Button registredHistory = new Button();
    private Button registredReview = new Button();
    private Label registredTitleLabel = new Label();
    private Button registredProfileButton = new Button();
    private Button registredLogoutButton = new Button();
    private VBox registredTopVbox = new VBox();
    private VBox registredCenterVbox = new VBox();
    private VBox registredCenterButtonsVbox = new VBox();

    //admin
    private VBox adminCenterButtonsVbox = new VBox();
    private HBox adminEventButtonHbox = new HBox();
    private HBox adminOrganizerButtonHbox = new HBox();
    private HBox adminPlaceButtonHbox = new HBox();
    private HBox adminAdminButtonHbox = new HBox();
    private Button adminAddEvent = new Button();
    private Button adminAddOrganizer = new Button();
    private Button adminShowOrganizer = new Button();
    private Button adminAddPlace = new Button();
    private Button adminShowPlace = new Button();
    private Button adminAddAdmin = new Button();
    private Button adminShowAdmin = new Button();

    @Override
    public void start(Stage primaryStage){
        mainStage = primaryStage;
        showEvent.setText("Eventy");
        showEvent.setPrefWidth(150);

        uiNeregistrovany();

        mainStage.setTitle("RandomGuys");
        mainStage.show();

        guestLoginButton.setOnMouseClicked(event -> {
            prihlaseniUI.nactiUI(mainStage, this);
        });

        showEvent.setOnMouseClicked(event -> {
            seznamEventuUI.nactiUI(mainStage, this);
        });
    }

    private void uiNeregistrovany(){
        guestLoginButton.setText("Login");
        guestLoginButton.setPrefWidth(150);
        guestTopHbox.setPadding(new Insets(20));
        guestTitleLabel.setText("Ticketstream");
        guestTitleLabel.setFont(Font.font("Arial",32));

        guestBorderPane.setTop(guestTopHbox);
        guestBorderPane.setCenter(guestCenterVbox);

        guestTopHbox.setAlignment(Pos.BASELINE_RIGHT);
        guestTopHbox.getChildren().add(guestLoginButton);

        guestCenterVbox.getChildren().addAll(guestTitleLabel,showEvent);
        guestCenterVbox.setAlignment(Pos.CENTER);
        guestCenterVbox.setSpacing(200);
        guestScene = new Scene(guestBorderPane, 1200, 720);
        mainStage.setScene(guestScene);
    }

    private void uiRegistrovany(){
        registredProfileButton.setText("Profil");
        registredProfileButton.setPrefWidth(150);
        registredLogoutButton.setText("Odhlásit");
        registredLogoutButton.setPrefWidth(150);
        registredHistory.setText("Historie nákupů");
        registredHistory.setPrefWidth(150);
        registredReview.setText("Hodnocení");
        registredReview.setPrefWidth(150);
        registredTitleLabel.setText("Ticketstream");
        registredTitleLabel.setFont(Font.font("Arial",32));

        registredBorderPane.setTop(registredTopVbox);
        registredBorderPane.setCenter(registredCenterVbox);

        registredTopVbox.setAlignment(Pos.BASELINE_RIGHT);
        registredTopVbox.getChildren().addAll(registredProfileButton, registredLogoutButton);
        registredTopVbox.setPadding(new Insets(20));
        registredTopVbox.setSpacing(10);

        //if (user je přihlášen && není admin)
        registredCenterVbox.getChildren().addAll(registredTitleLabel, registredCenterButtonsVbox);
        registredCenterVbox.setAlignment(Pos.CENTER);
        registredCenterVbox.setSpacing(200);

        registredCenterButtonsVbox.getChildren().addAll(showEvent, registredHistory, registredReview);
        registredCenterButtonsVbox.setAlignment(Pos.CENTER);
        registredCenterButtonsVbox.setSpacing(10);
        /*else if (user je přihlášen && je admin)
        registredCenterVbox.getChildren().addAll(registredTitleLabel, adminCenterButtonsVbox);
        adminAddEvent.setText("Přidat event");
        adminAddEvent.setPrefWidth(150);
        adminAddOrganizer.setText("Přidat organizátora");
        adminAddOrganizer.setPrefWidth(150);
        adminShowOrganizer.setText("Seznam organizátorů");
        adminShowOrganizer.setPrefWidth(150);
        adminAddPlace.setText("Přidat místo");
        adminAddPlace.setPrefWidth(150);
        adminShowPlace.setText("Seznam míst");
        adminShowPlace.setPrefWidth(150);
        adminAddAdmin.setText("Přidat správce");
        adminAddAdmin.setPrefWidth(150);
        adminShowAdmin.setText("Seznam správců");
        adminShowAdmin.setPrefWidth(150);
        adminCenterButtonsVbox.getChildren().addAll(adminEventButtonHbox,adminOrganizerButtonHbox,adminPlaceButtonHbox,adminAdminButtonHbox);
        adminCenterButtonsVbox.setSpacing(10);
        adminEventButtonHbox.getChildren().addAll(adminAddEvent,showEvent);
        adminEventButtonHbox.setAlignment(Pos.CENTER);
        adminOrganizerButtonHbox.getChildren().addAll(adminAddOrganizer,adminShowOrganizer);
        adminOrganizerButtonHbox.setAlignment(Pos.CENTER);
        adminPlaceButtonHbox.getChildren().addAll(adminAddPlace,adminShowPlace);
        adminPlaceButtonHbox.setAlignment(Pos.CENTER);
        adminAdminButtonHbox.getChildren().addAll(adminAddAdmin,adminShowAdmin);
        adminAdminButtonHbox.setAlignment(Pos.CENTER);*/



        registredScene = new Scene(registredBorderPane, 1200, 720);
        mainStage.setScene(registredScene);
    }

    public void zobrazMainMenuUI(){
        //vytvoří se podmínka pro
        //if (user login) -> zobrazí UI pro registrovaného
        //mainStage.setScene(registredScene);
        //else if (!user login) -> zobrazí UI pro neregistrovaného
        mainStage.setScene(guestScene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
