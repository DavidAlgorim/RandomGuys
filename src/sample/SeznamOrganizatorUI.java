package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.util.converter.IntegerStringConverter;
import sample.db.Databaze;
import sample.db.Organizator;

public class SeznamOrganizatorUI {

    private TableColumn<Organizator, String> sloupecJmeno;
    private TableColumn<Organizator, Integer> sloupecTelefon;
    private TableColumn<Organizator, String> sloupecEmail;
    private TableView<Organizator> table;
    private ObservableList<Organizator> dataEventu;

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

    private TableView<Organizator> vytvorTabulku() {
        table = new TableView<Organizator>();
        dataEventu = FXCollections.observableArrayList();

        table.setEditable(true);

        sloupecJmeno = new TableColumn("Jméno");
        sloupecJmeno.setPrefWidth(110);
        sloupecTelefon = new TableColumn("Telefon");
        sloupecTelefon.setPrefWidth(130);
        sloupecEmail = new TableColumn("Email");
        sloupecEmail.setPrefWidth(130);
        
        sloupecJmeno.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getJmeno()));
        sloupecTelefon.setCellValueFactory(new PropertyValueFactory<Organizator, Integer>("telefon"));
        sloupecEmail.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
     
        sloupecJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecTelefon.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sloupecEmail.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(dataEventu);
        table.getColumns().addAll(sloupecJmeno, sloupecTelefon, sloupecEmail);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        dataEventu.addAll(Databaze.getOrganizatori());

        return table;
    }
}
