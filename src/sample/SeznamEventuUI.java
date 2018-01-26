package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import sample.db.Databaze;
import sample.db.Event;
import sample.db.Uzivatel;

import java.util.Optional;

public class SeznamEventuUI {

    private TableColumn<Event, String> sloupecNazev;
    private TableColumn<Event, String> sloupecOrganizator;
    private TableColumn<Event, String> sloupecMisto;
    private TableColumn<Event, Integer> sloupecCena;
    private TableColumn<Event, Double> sloupecHodnoceni;
    private TableView<Event> table;
    private ObservableList<Event> dataEventu;

    private Scene scene;
    private BorderPane borderPane;
    private HBox bottomHbox;
    private Button backButton;
    private Button _provizorni;

    private Main main;
    private Stage stage;
    private EventDetailUI eventDetailUI = new EventDetailUI();

    private Uzivatel uzivatel;

    public void nactiUI(Stage stage, Main main, Uzivatel uzivatel){
        this.main = main;
        this.stage = stage;
        this.uzivatel = uzivatel;

        vytvorScenu();
        stage.setScene(scene);
        stage.show();
        this.vyrobContextMenu();

        backButton.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });

        _provizorni.setOnMouseClicked(event -> {
            eventDetailUI.nactiUI(stage, this,
                    dataEventu.get(table.getSelectionModel().getFocusedIndex()), main);
        });
    }

    public void zobrazSeznamEventuUI(){
        stage.setScene(scene);
    }

    private void vytvorScenu(){
        borderPane = new BorderPane();
        borderPane.setCenter(vytvorTabulku());
        backButton = new Button();
        backButton.setText("Zpět");
        backButton.setPrefWidth(150);
        bottomHbox = new HBox();
        bottomHbox.getChildren().addAll(backButton, _provizorni);
        bottomHbox.setPadding(new Insets(10));
        bottomHbox.setAlignment(Pos.CENTER);
        borderPane.setBottom(bottomHbox);
        _provizorni = new Button();
        _provizorni.setText("Zakoupit lístky");
        scene = new Scene(borderPane, 1200,720);
    }

    private TableView<Event> vytvorTabulku() {
        table = new TableView<Event>();
        dataEventu = FXCollections.observableArrayList();

        table.setEditable(true);

        sloupecNazev = new TableColumn("Název");
        sloupecNazev.setPrefWidth(110);
        sloupecOrganizator = new TableColumn("Organizátor");
        sloupecOrganizator.setPrefWidth(130);
        sloupecMisto = new TableColumn("Místo");
        sloupecMisto.setPrefWidth(130);
        sloupecCena = new TableColumn("Cena");
        sloupecCena.setPrefWidth(130);
        sloupecHodnoceni = new TableColumn("Hodnocení");
        sloupecHodnoceni.setEditable(false);

        sloupecNazev.setCellValueFactory(new PropertyValueFactory<Event, String>("nazev"));
        sloupecOrganizator.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOrganizator().getJmeno()));
        sloupecMisto.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMisto().getAdresa()));
        sloupecCena.setCellValueFactory(new PropertyValueFactory<Event, Integer>("cena"));
        sloupecHodnoceni.setCellValueFactory(new PropertyValueFactory<Event, Double>("hodnoceni"));


        sloupecNazev.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecOrganizator.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecMisto.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecCena.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sloupecHodnoceni.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


        if (main.getSpravce() != null || main.getAdmin() != null) {
            table.setEditable(true);
        } else {
            table.setEditable(false);
        }
        dataEventu.addAll(Databaze.getEventy());
        table.setItems(dataEventu);
        table.getColumns().addAll(sloupecNazev, sloupecOrganizator,sloupecMisto, sloupecCena, sloupecHodnoceni);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }
    
    public void vyrobContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
            MenuItem detailEventu = new MenuItem("Zobrazit detail");
            MenuItem smazEvent = new MenuItem("Smazat");
            detailEventu.setOnAction(new EventHandler <ActionEvent> () {
                public void handle(ActionEvent click) {
                    eventDetailUI.nactiUI(stage, SeznamEventuUI.this,
                            dataEventu.get(table.getSelectionModel().getFocusedIndex()), main);
                }
             });
            if (main.getSpravce() != null || main.getAdmin() != null) {
                smazEvent.setOnAction(new EventHandler <ActionEvent> () {
                public void handle(ActionEvent click) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Smazat Event");
                    alert.setHeaderText("Event bude permanentně vymazán z databáze.");
                    alert.setContentText("Chcete opravdu smazat?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        Databaze.removeEvent(table.getSelectionModel().getSelectedItem().getNazev());
                    } else {

                    }
                }
             });
            contextMenu.getItems().addAll(detailEventu, smazEvent);
            }
            
            else {
                contextMenu.getItems().addAll(detailEventu);
            }
            
        this.table.setOnMousePressed(new EventHandler <MouseEvent>() {
            
            @Override
            public void handle(MouseEvent click) {
                if (click.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(scene.getWindow(), click.getScreenX(), click.getScreenY());
                }
            }
        });
    }

}
