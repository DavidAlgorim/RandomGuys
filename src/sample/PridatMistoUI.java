package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.db.Databaze;

import javax.xml.crypto.Data;

public class PridatMistoUI {

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox topHbox = new HBox();
    private Label titleLabel = new Label();
    private VBox centerVbox = new VBox();
    private TextField centerTextAreaNazev = new TextField();
    private TextField centerTextAreaAdresa = new TextField();
    private TextField centerTextAreaTelefon = new TextField();
    private TextField centerTextAreaEmail = new TextField();
    private Button centerButtonVytvorit = new Button();
    private Button centerButtonZpet = new Button();
    private Label centerLabelNazev = new Label();
    private Label centerLabelAdresa = new Label();
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
            Databaze.insertNewMisto(centerTextAreaNazev.getText(), centerTextAreaAdresa.getText(), centerTextAreaEmail.getText(), Integer.parseInt(centerTextAreaTelefon.getText()));
            centerTextAreaNazev.clear();
            centerTextAreaAdresa.clear();
            centerTextAreaEmail.clear();
            centerTextAreaTelefon.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Místo úspěšně přidáno");
            alert.showAndWait();
        });
    }

    private void vytvorScenu(){
        if (scene == null)
        {
            titleLabel.setText("Nové místo");
            titleLabel.setFont(Font.font("Arial",32));
            topHbox.getChildren().add(titleLabel);
            topHbox.setAlignment(Pos.CENTER);
            topHbox.setPadding(new Insets(20));
            borderPane.setTop(topHbox);

            centerButtonZpet.setText("Zpět");
            centerButtonVytvorit.setText("Vytvořit");
            centerLabelNazev.setText("Název");
            centerLabelAdresa.setText("Adresa");
            centerLabelTelefon.setText("Telefon");
            centerLabelEmail.setText("Email");
            centerButtonZpet.setPrefWidth(220);
            centerButtonVytvorit.setPrefWidth(220);
            centerVbox.getChildren().addAll(centerLabelNazev, centerTextAreaNazev, centerLabelAdresa,
                    centerTextAreaAdresa, centerLabelTelefon, centerTextAreaTelefon,
                    centerLabelEmail, centerTextAreaEmail, centerButtonVytvorit, centerButtonZpet);
            centerVbox.setSpacing(5);
            centerVbox.setMaxWidth(220);
            centerVbox.setAlignment(Pos.CENTER_LEFT);
            borderPane.setCenter(centerVbox);


            scene = new Scene(borderPane,1200,720);
        }
    }

}
