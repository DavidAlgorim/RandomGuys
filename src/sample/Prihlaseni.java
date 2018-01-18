package sample;

import com.sun.tools.javac.code.Attribute;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Prihlaseni {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String dbConnection = "jdbc:mysql://localhost:8889";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";

    public static Connection getDBConn(){
        Connection connection = null;

        try {
            //          Otevreni komunikace do databaze
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
        }
        catch (SQLException | NullPointerException | ClassNotFoundException ex){

        }
        return connection;
    }

    public static void databazovaFunkce(String funkce, String parametr) {

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
            } else if (funkce.equals("INSERT")) {

                statement =
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

    public static void nactiZDB(ResultSet rs) {
        try {
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(Prihlaseni.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static Osoba prihlasitSe(String username, String heslo){

        String hash = "";
        try {
            String sql = "SELECT pass_hash FROM osoba WHERE username = ?";
            Connection conn = getDBConn();
            PreparedStatement pstmt = conn.prepareStatement( sql );
            pstmt.setString( 1, username);
            ResultSet results = pstmt.executeQuery( );
            hash = results.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(BCrypt.checkpw(heslo, hash)) {
            System.out.println(hash);
            return new Uzivatel(username);
        } else {
            System.out.println("DEMENT");
            return null;
        }

        // Ověří, zda sedí heslo a vrátí instanci uživatele/správce pokud ano, pokud ne vrátí null

    }

}
