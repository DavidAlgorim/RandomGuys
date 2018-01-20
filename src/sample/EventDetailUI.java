package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EventDetailUI {

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox topHbox = new HBox();
    private Label titleLabel = new Label();
    private VBox centerVbox = new VBox();
    private TextArea centerTextAreaPopis = new TextArea();
    private TextField centerTextAreaNazev = new TextField();
    private TextField centerTextAreaOrganizator = new TextField();
    private TextField centerTextAreaMisto = new TextField();
    private TextField centerTextAreaCena = new TextField();
    private ComboBox<String> centerComboBoxPocet = new ComboBox();
    private ObservableList<String> pocetListku;
    private ComboBox<String> centerComboBoxZvyhodneny = new ComboBox();
    private ObservableList<String> pocetZvyhodneny;
    private TextField centerTextAreaBody = new TextField();
    private Button centerButtonKoupit = new Button();
    private Button centerButtonZpet = new Button();
    private Label centerLabelPopis = new Label();
    private Label centerLabelNazev = new Label();
    private Label centerLabelOrganizator = new Label();
    private Label centerLabelMisto= new Label();
    private Label centerLabelCena = new Label();
    private Label centerLabelPocet = new Label();
    private Label centerLabelZvyhodneny = new Label();
    private Label centerLabelBody = new Label();

    public void nactiUI(Stage stage, SeznamEventuUI seznamEventuUI){

        vytvorScenu();

        stage.setScene(scene);
        stage.show();

        centerButtonZpet.setOnMouseClicked(event -> {
            seznamEventuUI.zobrazSeznamEventuUI();
        });
        centerComboBoxPocet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue)
                {
                    case "1":
                        pocetZvyhodneny = FXCollections.observableArrayList("0","1");
                        break;
                    case "2":
                        pocetZvyhodneny = FXCollections.observableArrayList("0","1","2");
                        break;
                    case "3":
                        pocetZvyhodneny = FXCollections.observableArrayList("0","1","2","3");
                        break;
                    case "4":
                        pocetZvyhodneny = FXCollections.observableArrayList("0","1","2","3","4");
                        break;
                    case "5":
                        pocetZvyhodneny = FXCollections.observableArrayList("0","1","2","3","4","5");
                        break;
                    case "6":
                        pocetZvyhodneny = FXCollections.observableArrayList("0","1","2","3","4","5","6");
                        break;
                }
                centerComboBoxZvyhodneny.setItems(pocetZvyhodneny);
            }
        });
    }

    private void vytvorScenu(){
        if (scene == null)
        {
            titleLabel.setText("Nákup lístku");
            titleLabel.setFont(Font.font("Arial",32));
            topHbox.getChildren().add(titleLabel);
            topHbox.setAlignment(Pos.CENTER);
            topHbox.setPadding(new Insets(20));
            borderPane.setTop(topHbox);

            centerButtonZpet.setText("Zpět");
            centerButtonKoupit.setText("Koupit");
            centerTextAreaBody.setEditable(false);

            centerTextAreaCena.setEditable(false);
            centerTextAreaMisto.setEditable(false);
            centerTextAreaNazev.setEditable(false);
            centerTextAreaOrganizator.setEditable(false);
            centerTextAreaPopis.setEditable(false);
            centerTextAreaPopis.setPrefHeight(100);
            centerLabelPopis.setText("Popis");
            centerLabelNazev.setText("Název");
            centerLabelOrganizator.setText("Organizator");
            centerLabelMisto.setText("Místo");
            centerLabelCena.setText("Cena");
            centerLabelPocet.setText("Počet");
            centerLabelZvyhodneny.setText("Zvýhodnění");
            centerLabelBody.setText("Uplatnit body");
            pocetListku = FXCollections.observableArrayList("1","2","3","4","5","6");
            centerComboBoxPocet.setItems(pocetListku);
            centerComboBoxPocet.setPrefWidth(220);
            centerComboBoxZvyhodneny.setPrefWidth(220);
            centerButtonKoupit.setPrefWidth(220);
            centerButtonZpet.setPrefWidth(220);
            centerVbox.getChildren().addAll(centerLabelPopis, centerTextAreaPopis, centerLabelNazev, centerTextAreaNazev,
                    centerLabelOrganizator, centerTextAreaOrganizator, centerLabelMisto, centerTextAreaMisto,
                    centerLabelCena, centerTextAreaCena, centerLabelPocet, centerComboBoxPocet, centerLabelZvyhodneny, centerComboBoxZvyhodneny,
                    centerLabelBody, centerTextAreaBody, centerButtonKoupit, centerButtonZpet);
            centerVbox.setSpacing(5);
            centerVbox.setMaxWidth(220);
            borderPane.setCenter(centerVbox);


            scene = new Scene(borderPane,1200,720);
        }
    }
}
