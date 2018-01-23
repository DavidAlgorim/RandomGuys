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
import sample.db.Admin;

public class SeznamAdminUI {

    private TableColumn<Admin, String> sloupecJmeno;
    private TableColumn<Admin, String> sloupecPrijmeni;
    private TableColumn<Admin, String> sloupecUsername;
    private TableColumn<Admin, String> sloupecHeslo;
    private TableView<Admin> table;
    private ObservableList<Admin> seznamAdminu;

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

    private TableView<Admin> vytvorTabulku() {
        table = new TableView<Admin>();
        seznamAdminu = FXCollections.observableArrayList();

        table.setEditable(true);

        sloupecJmeno = new TableColumn("Jméno");
        sloupecJmeno.setPrefWidth(110);
        sloupecPrijmeni = new TableColumn("Příjmení");
        sloupecPrijmeni.setPrefWidth(130);
        sloupecHeslo = new TableColumn("Heslo");
        sloupecHeslo.setPrefWidth(130);
        sloupecUsername = new TableColumn("Username");
        sloupecUsername.setPrefWidth(130);

        sloupecJmeno.setCellValueFactory(new PropertyValueFactory<Admin, String>("jmeno"));
        sloupecPrijmeni.setCellValueFactory(new PropertyValueFactory<Admin, String>("telefon"));
        sloupecHeslo.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
        sloupecUsername.setCellValueFactory(new PropertyValueFactory<Admin, String>("username"));

        sloupecJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecPrijmeni.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecHeslo.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecUsername.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(seznamAdminu);
        table.getColumns().addAll(sloupecJmeno, sloupecPrijmeni, sloupecUsername, sloupecHeslo);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

}
