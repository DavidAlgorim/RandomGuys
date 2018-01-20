package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.converter.DoubleStringConverter;

public class SeznamEventuUI {

    private TableColumn<Event, String> sloupecNazev;
    private TableColumn<Event, String> sloupecOrganizator;
    private TableColumn<Event, String> sloupecMisto;
    private TableColumn<Event, String> sloupecCena;
    private TableColumn<Event, Double> sloupecHodnoceni;
    private TableView<Event> table;
    private ObservableList<Event> dataEventu;

    private Scene scene;
    private BorderPane borderPane = new BorderPane();
    private HBox bottomHbox = new HBox();
    private Button backButton = new Button();
    private Button _provizorni = new Button();

    private Main main;
    private Stage stage;
    private EventDetailUI eventDetailUI = new EventDetailUI();

    public void nactiUI(Stage stage, Main main){
        this.main = main;
        this.stage = stage;

        vytvorScenu();
        stage.setScene(scene);
        stage.show();

        backButton.setOnMouseClicked(event -> {
            main.zobrazMainMenuUI();
        });

        _provizorni.setOnMouseClicked(event -> {
            eventDetailUI.nactiUI(stage, this);
        });
    }

    public void zobrazSeznamEventuUI(){
        stage.setScene(scene);
    }

    private void vytvorScenu(){
        if (scene == null)
        {
            borderPane.setCenter(vytvorTabulku());
            backButton.setText("Zpět");
            backButton.setPrefWidth(150);
            bottomHbox.getChildren().addAll(backButton, _provizorni);
            bottomHbox.setPadding(new Insets(10));
            bottomHbox.setAlignment(Pos.CENTER);
            borderPane.setBottom(bottomHbox);
            _provizorni.setText("provizorní tlačítko další hehe (předělá se na klik eventu)");
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
        sloupecCena = new TableColumn("Cena");
        sloupecCena.setPrefWidth(130);
        sloupecHodnoceni = new TableColumn("Hodnocení");
        sloupecHodnoceni.setEditable(false);

        sloupecNazev.setCellValueFactory(new PropertyValueFactory<Event, String>("nazev"));
        sloupecOrganizator.setCellValueFactory(new PropertyValueFactory<Event, String>("organizator"));
        sloupecMisto.setCellValueFactory(new PropertyValueFactory<Event, String>("misto"));
        sloupecCena.setCellValueFactory(new PropertyValueFactory<Event, String>("cena"));
        sloupecHodnoceni.setCellValueFactory(new PropertyValueFactory<Event, Double>("hodnoceni"));

        sloupecNazev.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecOrganizator.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecMisto.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecCena.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecHodnoceni.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        table.setItems(dataEventu);
        table.getColumns().addAll(sloupecNazev, sloupecOrganizator, sloupecMisto, sloupecCena, sloupecHodnoceni);
        return table;
    }

}
