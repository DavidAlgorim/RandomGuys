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
import javafx.util.converter.BooleanStringConverter;
import sample.db.Databaze;
import sample.db.Listek;
import sample.db.Uzivatel;

public class SeznamHistorieUI {

    private TableColumn<Listek, String> sloupecEvent;
    private TableColumn<Listek, Boolean> sloupecZvyhodneny;
    private TableView<Listek> table;
    private ObservableList<Listek> dataEventu;

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
            borderPane.setCenter(vytvorTabulku(main.getUzivatel()));
            backButton.setText("Zpět");
            backButton.setPrefWidth(150);
            bottomHbox.getChildren().addAll(backButton);
            bottomHbox.setPadding(new Insets(10));
            bottomHbox.setAlignment(Pos.CENTER);
            borderPane.setBottom(bottomHbox);
            scene = new Scene(borderPane, 1200,720);
        }
    }

    private TableView<Listek> vytvorTabulku(Uzivatel uzivatel) {
        table = new TableView<Listek>();
        dataEventu = FXCollections.observableArrayList();

        table.setEditable(true);

        sloupecEvent = new TableColumn("Event");
        sloupecEvent.setPrefWidth(130);
        sloupecZvyhodneny = new TableColumn("Zvýhodněný");
        sloupecZvyhodneny.setPrefWidth(130);

        //sloupecEvent.setCellValueFactory(new PropertyValueFactory<Listek, String>("zakaznik"));
        sloupecZvyhodneny.setCellValueFactory(new PropertyValueFactory<Listek, Boolean>("zvyhodneny"));

        //sloupecEvent.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecZvyhodneny.setCellFactory(TextFieldTableCell.<Listek, Boolean>forTableColumn(new BooleanStringConverter()));

        table.setItems(dataEventu);
        table.getColumns().addAll(sloupecEvent, sloupecZvyhodneny);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        dataEventu.addAll(Databaze.getListkyUzivatele(uzivatel));

        return table;
    }
}
