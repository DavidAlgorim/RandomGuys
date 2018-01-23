package sample;

import javafx.scene.control.Alert;
import sun.misc.Version;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static ResultSet databazeGETbyString(String parametr, String... udaje) {
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
            alertException(ex);
        }
        return rs;
    }

    /**
     * Vrací údaje z databáze podle zadaného parametru (SQL příkazu). Po načtení údaju z ResultSet musí být uzavřen!!
     * -> rs.close();
     * @param parametr SQL příkaz
     * @param udaje údaje, podle kterých může být vyhledán záznam v databázi (jedná se o int, takže to bude nejspíš id)
     * @return result set z databáze podle parametru
     */
    public static ResultSet databazeGETbyInt(String parametr, int... udaje) {
        Connection connection = getDBConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(parametr);
            int i = 1;
            // Pokud není metodě předán žádný údaj, cyklus neproběhne ani jednou
            for (int udaj : udaje) {
                statement.setInt(i,udaj);
                i++;
            }
            rs = statement.executeQuery();

            statement.close();
            connection.close();

        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return rs;
    }

    //všechny eventy
    public static ResultSet databazeGetEvent() {

        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM event";

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);

            statement.close();
            connection.close();
            rs.close();

        } catch (Exception e) {
            alertException(e);
        }
        return rs;
    }

    //v parametru event, pro který hledáme hodnocení
    public static ResultSet databazeGetHodnoceni(int idEvent) {

        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM hodnoceni WHERE id_event = " + idEvent;

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);

            statement.close();
            connection.close();
            rs.close();


        } catch (Exception e) {
            alertException(e);
        }
        return rs;
    }

    //parametr jméno neregistrovaného uživatele
    public static ResultSet databazeGetListek(String zakaznik) {

        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM listek WHERE zakaznik = " + zakaznik;

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);

            statement.close();
            connection.close();
            rs.close();


        } catch (Exception e) {
            alertException(e);
        }
        return rs;
    }

    //overload, parametr id registrovaného
    public static ResultSet databazeGetListek(int idOsoba) {

        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM listek WHERE id_osoba = " + idOsoba;

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);

            statement.close();
            connection.close();
            rs.close();


        } catch (Exception e) {
            alertException(e);
        }
        return rs;
    }

    public static ResultSet databazeGetMisto() {

        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM misto";

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);

            statement.close();
            connection.close();
            rs.close();


        } catch (Exception e) {
            alertException(e);
        }
        return rs;
    }

    public static ResultSet databazeGetOrganizator() {

        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM organizator";

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);

            statement.close();
            connection.close();
            rs.close();


        } catch (Exception e) {
            alertException(e);
        }
        return rs;
    }

    public static ResultSet databazeGetOsoba(int idOsoba) {

        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM osoba WHERE id_osoba = " + idOsoba;

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);
            statement.close();


        } catch (Exception e) {
            alertException(e);
        }
        return rs;
    }

    public static String databazeGetHash(String username) {

        String hash = "";
        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM osoba WHERE username = \'" + username + "\'";

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);
            while (rs.next()) {
                hash = rs.getString("pass_hash");
            }
            statement.close();


        } catch (Exception e) {
            alertException(e);
        }
        return hash;
    }

    public static String databazeGetStatus(String username) {

        String status = "";
        ResultSet rs = null;
        try {
            //připojení k db
            Connection connection = getDBConn();

            //SELECT dotaz
            String select = "SELECT * FROM osoba WHERE username = \'" + username + "\'";

            Statement statement = connection.createStatement();

            rs = statement.executeQuery(select);
            while (rs.next()) {
                status = rs.getString("status");
            }
            statement.close();


        } catch (Exception e) {
            alertException(e);
        }
        return status;
    }

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
            alertException(e);
        }
    }


    public static void databazeInsertNewHodnoceni(int hodnoceni, String textHodnoceni, int idEvent) {
        try {
            //připojení k databázi
            Connection connection = getDBConn();

            //insert SQL příkaz
            String insert = "INSERT INTO hodnoceni (hodnoceni, text_hodnoceni, id_event) VALUES (?, ?, ?)";

            //statement
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setInt(1, hodnoceni);
            statement.setString(2, textHodnoceni);
            statement.setInt(3, idEvent);


            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }
    //lístek asi nejlépe řešit Overloadem, jinak mě moc nenapadá jak řešit registrovaného a neregistrovaného
    public static void databazeInsertNewListek(String zakaznik, int zvyhodneni, int idEvent) {
        try {
            //připojení k databázi
            Connection connection = getDBConn();

            //insert SQL příkaz
            String insert = "INSERT INTO listek (zakaznik, zvyhodneni, id_event) VALUES (?, ?, ?)";

            //statement
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, zakaznik);
            statement.setInt(2, zvyhodneni);
            statement.setInt(3, idEvent);


            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }
    //stejná metoda pro registrovaného zákazníka, detaily zákazníka pak ideálně řešit přes sql JOIN
    public static void databazeInsertNewListek(int zvyhodneni, int idEvent, int idOsoba) {
        try {
            //připojení k databázi
            Connection connection = getDBConn();

            //insert SQL příkaz
            String insert = "INSERT INTO listek (zvyhodneni, id_event, id_osoba) VALUES (?, ?, ?)";

            //statement
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setInt(1, zvyhodneni);
            statement.setInt(2, idEvent);
            statement.setInt(3, idOsoba);


            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }

    public static void databazeInsertNewMisto(String nazev, String adresa, String email, int telefon) {
        try {
            //připojení k databázi
            Connection connection = getDBConn();

            //insert SQL příkaz
            String insert = "INSERT INTO misto (nazev, adresa, email, telefon) VALUES (?, ?, ?, ?)";

            //statement
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, nazev);
            statement.setString(2, adresa);
            statement.setString(3, email);
            statement.setInt(4, telefon);


            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }


    public static void databazeInsertNewOrganizator(String jmeno, String email, int telefon) {
        try {
            //připojení k databázi
            Connection connection = getDBConn();

            //insert SQL příkaz
            String insert = "INSERT INTO organizator (jmeno, email, telefon) VALUES (?, ?, ?)";

            //statement a zaplnění parametry metody, status je vždy uzivatel
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, jmeno);
            statement.setString(2, email);
            statement.setInt(3, telefon);;

            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }


    public static void databazeInsertNewOsoba(String jmeno, String email, String username, String heslo) {
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
            alertException(e);
        }
    }

    private static Connection getDBConn(){
        Connection connection = null;
        try {
            //Otevreni komunikace do databaze
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

    private static void alertException(Exception e) {
        System.out.println(e.getMessage());
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Query error");
        alert.setHeaderText("Chyba pripojeni: " + e.getMessage());

        alert.showAndWait();
    }
}
