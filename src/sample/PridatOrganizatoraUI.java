package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PridatOrganizatoraUI {

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox topHbox = new HBox();
    private Label titleLabel = new Label();
    private VBox centerVbox = new VBox();
    private TextField centerTextAreaJmeno = new TextField();
    private TextField centerTextAreaTelefon = new TextField();
    private TextField centerTextAreaEmail = new TextField();
    private Button centerButtonVytvorit = new Button();
    private Button centerButtonZpet = new Button();
    private Label centerLabelJmeno = new Label();
    private Label centerLabelTelefon = new Label();
    private Label centerLabelEmail= new Label();

    public void nactiUI(Stage stage, Main main){

        vytvorScenu();

        stage.setScene(scene);
        stage.show();

        centerButtonZpet.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });
        centerButtonVytvorit.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });
    }

    private void vytvorScenu(){
        if (scene == null)
        {
            titleLabel.setText("Nový organizátor");
            titleLabel.setFont(Font.font("Arial",32));
            topHbox.getChildren().add(titleLabel);
            topHbox.setAlignment(Pos.CENTER);
            topHbox.setPadding(new Insets(20));
            borderPane.setTop(topHbox);

            centerButtonZpet.setText("Zpět");
            centerButtonVytvorit.setText("Vytvořit");
            centerLabelJmeno.setText("Jméno");
            centerLabelTelefon.setText("Telefon");
            centerLabelEmail.setText("Email");
            centerButtonZpet.setPrefWidth(220);
            centerButtonVytvorit.setPrefWidth(220);
            centerVbox.getChildren().addAll(centerLabelJmeno, centerTextAreaJmeno, centerLabelTelefon, centerTextAreaTelefon,
                    centerLabelEmail, centerTextAreaEmail, centerButtonVytvorit, centerButtonZpet);
            centerVbox.setSpacing(5);
            centerVbox.setMaxWidth(220);
            centerVbox.setAlignment(Pos.CENTER_LEFT);
            borderPane.setCenter(centerVbox);


            scene = new Scene(borderPane,1200,720);
        }
    }
}
