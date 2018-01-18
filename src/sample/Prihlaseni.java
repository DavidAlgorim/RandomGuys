package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;


public class Prihlaseni {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String dbConnection = "jdbc:mysql://127.0.0.1:3306";
    private static final String dbUser = "root";
    private static final String dbPassword = "passw0rd";

    public void databazovaFunkce(String funkce, String parametr) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

//          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);

//          provedeni dotazu
            ResultSet rs = null;

            if (funkce.equals("GET")) {

                statement = connection.prepareStatement(parametr);

                rs = statement.executeQuery();

                nactiZDB(rs);
                rs.close();
            }

            if (funkce.equals("INSERT")) {

                statement = connection.prepareStatement(parametr);

                statement.setString(1, addID.getText());
                statement.setString(2, addJmeno.getText());
                statement.setString(3, addPrijmeni.getText());
                statement.setString(4, addAdresa.getText());
                statement.setString(5, addPohlavi.getText());
                statement.setString(6, addVzdelani.getText());

                statement.executeUpdate();

            }

            if (funkce.equals("UPDATE")) {

                statement = connection.prepareStatement(parametr);

                statement.setString(1, changeItem);
                statement.setString(2, changeID);

                statement.executeUpdate();
            }

//              uzavreni spojeni
            statement.close();
            connection.close();

        } catch (SQLException | NullPointerException | ClassNotFoundException ex) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ex.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("DB");
                    alert.setHeaderText("Chyba pripojeni");

                    alert.showAndWait();
                    System.out.println(ex.getMessage());
                }
            });

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }

        }

    }


    public static Osoba prihlasitSe(String username, String heslo){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/jdbc_db");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //vůbec nevím jak s tím propojením s db
        String hesloDb = "";
        String status = "";

        if(BCrypt.checkpw(heslo, hesloDb)) {
            if (status.equals("spravce")) {
                return Spravce;
            } else {
                return Zakaznik;
            }

        } else {
            return null;
        }

        // Ověří, zda sedí heslo a vrátí instanci uživatele/správce pokud ano, pokud ne vrátí null

    }

}
