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
import sample.db.Uzivatel;

public class ProfilUI {

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox topHbox = new HBox();
    private Label titleLabel = new Label();
    private VBox centerVbox = new VBox();
    private TextField centerTextAreaJmeno = new TextField();
    private TextField centerTextAreaHeslo = new TextField();
    private Button centerButtonZmenit = new Button();
    private Button centerButtonZpet = new Button();
    private Label centerLabelJmeno = new Label();
    private Label centerLabelBody = new Label();
    private Label centerLabelHeslo = new Label();

    public void nactiUI(Stage stage, Main main){

        vytvorScenu(main.getUzivatel());

        stage.setScene(scene);
        stage.show();

        centerButtonZpet.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });
        centerButtonZmenit.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });
    }

    private void vytvorScenu(Uzivatel uzivatel){
        if (scene == null)
        {
            titleLabel.setText("Změna údajů");
            titleLabel.setFont(Font.font("Arial",32));
            topHbox.getChildren().add(titleLabel);
            topHbox.setAlignment(Pos.CENTER);
            topHbox.setPadding(new Insets(20));
            borderPane.setTop(topHbox);

            centerButtonZpet.setText("Zpět");
            centerButtonZmenit.setText("Změnit");
            centerLabelJmeno.setText("Jméno");
            centerTextAreaJmeno.setText(uzivatel.getJmeno());
            centerTextAreaJmeno.setEditable(false);
            centerLabelBody.setText("Bonusové body: " + Integer.toString(uzivatel.getBonusoveBody()));
            centerLabelHeslo.setText("Heslo");
            centerButtonZpet.setPrefWidth(220);
            centerButtonZmenit.setPrefWidth(220);
            centerVbox.getChildren().addAll(centerLabelJmeno, centerTextAreaJmeno, centerLabelBody, centerLabelHeslo, centerTextAreaHeslo, centerButtonZmenit,
                    centerButtonZpet);
            centerVbox.setSpacing(5);
            centerVbox.setMaxWidth(220);
            centerVbox.setAlignment(Pos.CENTER_LEFT);
            borderPane.setCenter(centerVbox);


            scene = new Scene(borderPane,1200,720);
        }
    }

}
