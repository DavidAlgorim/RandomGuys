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

    /**
     * Vrací údaje z databáze podle zadaného parametru (SQL příkazu). Po načtení údaju z ResultSet musí být uzavřen!!
     * -> rs.close();
     * @param parametr SQL příkaz
     * @param udaje údaje, podle kterých může být vyhledán záznam v databázi (většinou se bude jednat o jeden - např.
     *              username, email nebo název eventu
     * @return result set z databáze podle parametru
     */
    public ResultSet databazeGET(String parametr, String... udaje) {
        Connection connection = getDBConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(parametr);
            int i = 1;
            // Pokud není metodě předán žádný údaj, cyklus neproběhne ani jednou
            for (String udaj : udaje) {
                statement.setString(i,udaj);
                i++;
            }
            rs = statement.executeQuery();

            statement.close();
            connection.close();

        } catch (SQLException | NullPointerException ex) {
            databazeChybaPripojeniAlert(ex);
        } finally {
            finallyBlockDatabazovaFunkce(statement, connection);
        }
        return rs;
    }


    /**
     * Přidává údaje do databáze dle zadaného parametru
     * @param parametr SQL příkaz
     * @param udaje údaje, které mají být zapsány do databáze
     * @return true, pokud vše proběhlo v pořádku
     */
    public boolean databazeInsert(String parametr, String... udaje){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDBConn();

            // Hodně hrubý návrh, kde pracuji pouze se Stringama. Ale jde to i takto jednoduše, můžeme udělat zvlášť
            // funkci pro každou entitu (Osoba, Event, Organizator, Misto,..)
            int i = 1;
            for (String udaj : udaje) {
                statement.setString(i,udaj);
                i++;
            }

            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            databazeChybaPripojeniAlert(ex);
            return false;
        } finally {
            finallyBlockDatabazovaFunkce(statement,connection);
        }

        return true;
    }

    public boolean databazeInsertNewUser(String parametr, String jmeno, String email, String username, String heslo) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDBConn();



            statement.setString(1,jmeno);
            statement.setString(2,email);
            statement.setString(3,username);
            statement.setString(4,heslo);



            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            databazeChybaPripojeniAlert(ex);
            return false;
        } finally {
            finallyBlockDatabazovaFunkce(statement,connection);
        }

        return true;
    }

    private Connection getDBConn(){
        Connection connection = null;
        try {
            //          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
        }
        catch (SQLException | NullPointerException | ClassNotFoundException ex){
            System.out.println("Database connection failed");
        }
        return connection;
    }

    private void databazeChybaPripojeniAlert(Exception ex){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("DB");
                alert.setHeaderText("Chyba pripojeni: " + ex.getMessage());

                alert.showAndWait();
                System.out.println(ex.getMessage());
            }
        });
    }

    private void finallyBlockDatabazovaFunkce(Statement statement, Connection connection){
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
