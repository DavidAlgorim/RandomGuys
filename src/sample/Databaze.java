package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Databaze {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String dbConnection = "jdbc:mysql://localhost:8889/jdbc_db";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";
//
//    /**
//     * Vrací údaje z databáze podle zadaného parametru (SQL příkazu). Po načtení údaju z ResultSet musí být uzavřen!!
//     * -> rs.close();
//     * @param parametr SQL příkaz
//     * @param udaje údaje, podle kterých může být vyhledán záznam v databázi (většinou se bude jednat o jeden - např.
//     *              username, email nebo název eventu
//     * @return result set z databáze podle parametru
//     */
//    public ResultSet databazeGET(String parametr, String... udaje) {
//        Connection connection = getDBConn();
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//        try {
//            statement = connection.prepareStatement(parametr);
//            int i = 1;
//            // Pokud není metodě předán žádný údaj, cyklus neproběhne ani jednou
//            for (String udaj : udaje) {
//                statement.setString(i,udaj);
//                i++;
//            }
//            rs = statement.executeQuery();
//
//            statement.close();
//            connection.close();
//
//        } catch (SQLException | NullPointerException ex) {
//            databazeChybaPripojeniAlert(ex);
//        } finally {
//            finallyBlockDatabazovaFunkce(statement, connection);
//        }
//        return rs;
//    }

    public static void databazeInsertNewEvent(String nazev, int idMisto, double cena, double zvyhodnenaCena, int kapacita, String popis, int idOrganizator) {
        try {
            //připojení k databázi
            Connection connection = getDBConn();

            //insert SQL příkaz
            String insert = "INSERT INTO event (nazev, id_misto, cena, zvyhodnena_cena, kapacita, popis, id_organizator) VALUES (?, ?, ?, ?, ?, ?, ?)";

            //statement
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, nazev);
            statement.setInt(2, idMisto);
            statement.setDouble(3, cena);
            statement.setDouble(4, zvyhodnenaCena);
            statement.setInt(5, kapacita);
            statement.setString(6, popis);
            statement.setInt(7, idOrganizator);

            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Query error");
            alert.setHeaderText("Chyba pripojeni: " + e.getMessage());

            alert.showAndWait();
        }
    }

    public static void databazeInsertNewUser(String jmeno, String email, String username, String heslo) {
        try {
            //připojení k databázi
            Connection connection = getDBConn();

            //insert SQL příkaz
            String insert = "INSERT INTO osoba (jmeno, email, username, pass_hash, status) VALUES (?, ?, ?, ?, ?)";

            //statement a zaplnění parametry metody, status je vždy uzivatel
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, jmeno);
            statement.setString(2, email);
            statement.setString(3, username);
            statement.setString(4, heslo);
            statement.setString(5, "uzivatel");

            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Query error");
            alert.setHeaderText("Chyba pripojeni: " + e.getMessage());

            alert.showAndWait();
        }
    }

    private static Connection getDBConn(){
        Connection connection = null;
        try {
            //          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
        }
        catch (SQLException | NullPointerException | ClassNotFoundException ex){
            System.out.println("Database connection failed");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection error");
            alert.setHeaderText("Chyba pripojeni: " + ex.getMessage());

            alert.showAndWait();
        }
        return connection;
    }
}
