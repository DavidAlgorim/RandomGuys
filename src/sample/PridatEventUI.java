package sample;

import java.util.List;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.db.Databaze;
import sample.db.Misto;
import sample.db.Organizator;

public class PridatEventUI {

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox topHbox = new HBox();
    private Label titleLabel = new Label();
    private VBox centerVbox = new VBox();
    private TextArea centerTextAreaPopis = new TextArea();
    private TextField centerTextAreaNazev = new TextField();
    private ComboBox<String> centerComboBoxOrganizator = new ComboBox();
    private ObservableList<Organizator> listOrganizatoru;
    private ObservableList<String> seznamOrganizatoru;
    private ComboBox<String> centerComboBoxMisto = new ComboBox();
    private List<Misto> listMist;
    private ObservableList<String> seznamMist;
    private TextField centerTextAreaCena = new TextField();
    private TextField centerTextAreaKapacita = new TextField();
    private Button centerButtonVytvorit = new Button();
    private Button centerButtonZpet = new Button();
    private Label centerLabelPopis = new Label();
    private Label centerLabelNazev = new Label();
    private Label centerLabelOrganizator = new Label();
    private Label centerLabelMisto= new Label();
    private Label centerLabelCena = new Label();
    private Label centerLabelKapacita = new Label();

    public void nactiUI(Stage stage, Main main){

        vytvorScenu();

        stage.setScene(scene);
        stage.show();

        centerButtonZpet.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });
        centerButtonVytvorit.setOnMouseClicked(event -> {
            Databaze.insertNewEvent(centerTextAreaNazev.getText(), (Databaze.getIdMista(centerComboBoxMisto.getSelectionModel().getSelectedItem())),
                    Integer.parseInt(centerTextAreaCena.getText()), Integer.parseInt(centerTextAreaCena.getText()),
                    Integer.parseInt(centerTextAreaKapacita.getText()),centerTextAreaPopis.getText(), Databaze.getIdOrganizatora(centerComboBoxOrganizator.getSelectionModel().getSelectedItem()));

            centerTextAreaNazev.clear();
            centerTextAreaCena.clear();
            centerTextAreaKapacita.clear();
            centerTextAreaPopis.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Event úspěšně přidán");
            alert.showAndWait();
        });
    }

    private void vytvorScenu(){
        if (scene == null)
        {
            //život by byl jednodušší bez milionu různých listů a jejich nekompatibilitou
            listMist = FXCollections.observableList(Databaze.getMista());
            seznamMist = FXCollections.observableArrayList();
            for (Misto misto :
                    listMist) {
                seznamMist.add(misto.getNazev());
            }

            listOrganizatoru = FXCollections.observableList(Databaze.getOrganizatori());
            seznamOrganizatoru = FXCollections.observableArrayList();
            for (Organizator organizator :
                    listOrganizatoru) {
                seznamOrganizatoru.add(organizator.getJmeno());
            }

            titleLabel.setText("Nový event");
            titleLabel.setFont(Font.font("Arial",32));
            topHbox.getChildren().add(titleLabel);
            topHbox.setAlignment(Pos.CENTER);
            topHbox.setPadding(new Insets(20));
            borderPane.setTop(topHbox);

            centerButtonZpet.setText("Zpět");
            centerButtonVytvorit.setText("Vytvořit");
            centerTextAreaPopis.setPrefHeight(100);
            centerLabelPopis.setText("Popis");
            centerLabelNazev.setText("Název");
            centerLabelOrganizator.setText("Organizator");
            centerLabelMisto.setText("Místo");
            centerLabelCena.setText("Cena");
            centerLabelKapacita.setText("Kapacita");
            centerComboBoxMisto.setItems(seznamMist);
            centerComboBoxMisto.setPrefWidth(220);
            centerComboBoxOrganizator.setItems(seznamOrganizatoru);
            centerComboBoxOrganizator.setPrefWidth(220);
            centerButtonVytvorit.setPrefWidth(220);
            centerButtonZpet.setPrefWidth(220);
            centerVbox.getChildren().addAll(centerLabelPopis, centerTextAreaPopis, centerLabelNazev, centerTextAreaNazev,
                    centerLabelOrganizator, centerComboBoxOrganizator, centerLabelMisto, centerComboBoxMisto,
                    centerLabelCena, centerTextAreaCena, centerLabelKapacita, centerTextAreaKapacita, centerButtonVytvorit, centerButtonZpet);
            centerVbox.setSpacing(5);
            centerVbox.setMaxWidth(220);
            centerVbox.setAlignment(Pos.CENTER_LEFT);
            borderPane.setCenter(centerVbox);


            scene = new Scene(borderPane,1200,720);
        }
    }

}