package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SeznamRecenzeUI {

    private TableColumn<Event, String> sloupecNazev;
    private TableColumn<Event, String> sloupecOrganizator;
    private TableColumn<Event, String> sloupecMisto;
    private TableColumn<Event, String> sloupecHodnoceni;
    private TableView<Event> table;
    private ObservableList<Event> dataEventu;

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox bottomHbox = new HBox();
    private Button backButton = new Button();

    private Main main;
    private Stage stage;

    public void nactiUI(Stage stage, Main main){
        this.main = main;
        this.stage = stage;

        vytvorScenu();
        stage.setScene(scene);
        stage.show();

        backButton.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });
    }

    private void vytvorScenu(){
        if (scene == null)
        {
            borderPane.setCenter(vytvorTabulku());
            backButton.setText("Zpět");
            backButton.setPrefWidth(150);
            bottomHbox.getChildren().addAll(backButton);
            bottomHbox.setPadding(new Insets(10));
            bottomHbox.setAlignment(Pos.CENTER);
            borderPane.setBottom(bottomHbox);
            scene = new Scene(borderPane, 1200,720);
        }
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
        sloupecHodnoceni = new TableColumn("Hodnocení");
        sloupecHodnoceni.setPrefWidth(130);

        sloupecNazev.setCellValueFactory(new PropertyValueFactory<Event, String>("nazev"));
        sloupecOrganizator.setCellValueFactory(new PropertyValueFactory<Event, String>("organizator"));
        sloupecMisto.setCellValueFactory(new PropertyValueFactory<Event, String>("misto"));
        sloupecHodnoceni.setCellValueFactory(new PropertyValueFactory<Event, String>("hodnoceni"));

        sloupecNazev.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecOrganizator.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecMisto.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecHodnoceni.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(dataEventu);
        table.getColumns().addAll(sloupecNazev, sloupecOrganizator, sloupecMisto, sloupecHodnoceni);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //editace buňky - hodnocení 
        sloupecHodnoceni.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Event, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Event, String> event) {

            }
        });

        return table;
    }

}
