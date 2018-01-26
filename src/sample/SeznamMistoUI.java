package sample;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import sample.db.Databaze;
import sample.db.Misto;

public class SeznamMistoUI {

    private TableColumn<Misto, String> sloupecNazev;
    private TableColumn<Misto, String> sloupecAdresa;
    private TableColumn<Misto, Integer> sloupecTelefon;
    private TableColumn<Misto, String> sloupecEmail;
    private TableView<Misto> table;
    private ObservableList<Misto> dataEventu;

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

    private TableView<Misto> vytvorTabulku() {
        table = new TableView<Misto>();
        dataEventu = FXCollections.observableArrayList();

        table.setEditable(true);

        sloupecNazev = new TableColumn("Název");
        sloupecNazev.setPrefWidth(110);
        sloupecAdresa = new TableColumn("Adresa");
        sloupecAdresa.setPrefWidth(110);
        sloupecTelefon = new TableColumn("Telefon");
        sloupecTelefon.setPrefWidth(130);
        sloupecEmail = new TableColumn("Email");
        sloupecEmail.setPrefWidth(130);

        sloupecNazev.setCellValueFactory(new PropertyValueFactory<Misto, String>("nazev"));
        sloupecAdresa.setCellValueFactory(new PropertyValueFactory<Misto, String>("adresa"));
        sloupecTelefon.setCellValueFactory(new PropertyValueFactory<Misto, Integer>("telefon"));
        sloupecEmail.setCellValueFactory(new PropertyValueFactory<Misto, String>("email"));

        sloupecNazev.setCellFactory(TextFieldTableCell.forTableColumn());
        sloupecTelefon.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sloupecEmail.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(dataEventu);
        table.getColumns().addAll(sloupecNazev, sloupecAdresa ,sloupecTelefon, sloupecEmail);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        dataEventu.addAll(Databaze.getMista());

        return table;
    }

    public void vyrobContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
            MenuItem smazMisto = new MenuItem("Smazat");
            if (main.getSpravce() != null || main.getAdmin() != null) {
                smazMisto.setOnAction(new EventHandler <ActionEvent> () {
                public void handle(ActionEvent click) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Smazat místo");
                    alert.setHeaderText("Místo bude permanentně vymazán z databáze.");
                    alert.setContentText("Chcete opravdu smazat?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                    // ... user chose OK
                    //Databaze.deleteMisto();
                    }/* else {
                    // ... user chose CANCEL or closed the dialog
                }*/
                    
                }
             });
            contextMenu.getItems().addAll(smazMisto);
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
