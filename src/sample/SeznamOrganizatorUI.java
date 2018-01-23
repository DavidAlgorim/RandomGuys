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

public class SeznamOrganizatorUI {

    private TableColumn<Event, String> sloupecJmeno;
    private TableColumn<Event, String> sloupecTelefon;
    private TableColumn<Event, String> sloupecEmail;
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

        sloupecJmeno = new TableColumn("Jméno");
        sloupecJmeno.setPrefWidth(110);
        sloupecTelefon = new TableColumn("Telefon");
        sloupecTelefon.setPrefWidth(130);
        sloupecEmail = new TableColumn("Email");
        sloupecEmail.setPrefWidth(130);

        sloupecJmeno.setCellValueFactory(new PropertyValueFactory<Event, String>("jmeno"));
        sloupecTelefon.setCellValueFactory(new PropertyValueFactory<Event, String>("telefon"));
        sloupecEmail.setCellValueFactory(new PropertyValueFactory<Event, String>("email"));

        sloupecJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecTelefon.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecEmail.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(dataEventu);
        table.getColumns().addAll(sloupecJmeno, sloupecTelefon, sloupecEmail);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }
}
