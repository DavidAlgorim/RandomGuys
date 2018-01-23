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

public class PridatAdminaUI {

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox topHbox = new HBox();
    private Label titleLabel = new Label();
    private VBox centerVbox = new VBox();
    private TextField centerTextAreaJmeno = new TextField();
    private TextField centerTextAreaPrijmeni = new TextField();
    private TextField centerTextAreaUsername = new TextField();
    private TextField centerTextAreaHeslo = new TextField();
    private Button centerButtonVytvorit = new Button();
    private Button centerButtonZpet = new Button();
    private Label centerLabelJmeno = new Label();
    private Label centerLabelPrijmeni = new Label();
    private Label centerLabelUsername= new Label();
    private Label centerLabelHeslo = new Label();

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
            titleLabel.setText("Nový admin");
            titleLabel.setFont(Font.font("Arial",32));
            topHbox.getChildren().add(titleLabel);
            topHbox.setAlignment(Pos.CENTER);
            topHbox.setPadding(new Insets(20));
            borderPane.setTop(topHbox);

            centerButtonZpet.setText("Zpět");
            centerButtonVytvorit.setText("Vytvořit");
            centerLabelJmeno.setText("Jméno");
            centerLabelPrijmeni.setText("Příjmení");
            centerLabelHeslo.setText("Heslo");
            centerLabelUsername.setText("Username");
            centerButtonZpet.setPrefWidth(220);
            centerButtonVytvorit.setPrefWidth(220);
            centerVbox.getChildren().addAll(centerLabelJmeno, centerTextAreaJmeno, centerLabelPrijmeni,
                    centerTextAreaPrijmeni, centerLabelUsername, centerTextAreaUsername, centerLabelHeslo,
                    centerTextAreaHeslo, centerButtonVytvorit, centerButtonZpet);
            centerVbox.setSpacing(5);
            centerVbox.setMaxWidth(220);
            centerVbox.setAlignment(Pos.CENTER_LEFT);
            borderPane.setCenter(centerVbox);


            scene = new Scene(borderPane,1200,720);
        }
    }

}
