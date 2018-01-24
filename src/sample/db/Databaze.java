package sample.db;

import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Databaze {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String dbConnection = "jdbc:mysql://localhost:8889/jdbc_db";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";


    // Metody vytvářející instance dle jejich id z DB

    public static Event getEvent(int id){
        Connection connection = getDBConn();
        PreparedStatement statement;
        Event event = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM event WHERE id_event = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                event = new Event(id, rs.getString("nazev"),
                        getOrganizator(rs.getInt("id_organizator")),
                        getMisto(rs.getInt("id_misto")), rs.getInt("cena"),
                        rs.getInt("zvyhodnena_cena"), rs.getInt("kapacita"),
                        rs.getString("popis"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return event;
    }

    public static Organizator getOrganizator(int id){
        Connection connection = getDBConn();
        PreparedStatement statement;
        Organizator organizator = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM organizator WHERE id_organizator = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                organizator = new Organizator(id, rs.getString("jmeno"), rs.getString("email"),
                        rs.getInt("telefon"));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return organizator;
    }

    public static Misto getMisto(int id){
        Connection connection = getDBConn();
        PreparedStatement statement;
        Misto misto = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM misto WHERE id_misto = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                misto = new Misto(id, rs.getString("nazev"), rs.getString("adresa"),
                        rs.getString("email"), rs.getInt("telefon"));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return misto;
    }



    // Metody vytvářející seznamy instancí

    public static List<Event> getEventy(){
        Connection connection = getDBConn();
        PreparedStatement statement;
        List<Event> eventy = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM event");
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                eventy.add(new Event(rs.getInt("id_event"),rs.getString("nazev"),
                        getOrganizator(rs.getInt("id_organizator")),
                        getMisto(rs.getInt("id_misto")),rs.getInt("cena"),
                        rs.getInt("zvyhodnena_cena"),rs.getInt("kapacita"),
                        rs.getString("popis")));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return eventy;
    }

    static List<Event> getEventyOrganizatora(Organizator organizator){
        Connection connection = getDBConn();
        PreparedStatement statement;
        List<Event> eventy = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM event WHERE id_organizator = ?");
            statement.setInt(1,organizator.getId());
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                eventy.add(new Event(rs.getInt("id_event"),rs.getString("nazev"),
                        organizator,getMisto(rs.getInt("id_misto")),
                        rs.getInt("cena"),rs.getInt("zvyhodnena_cena"),
                        rs.getInt("kapacita"),rs.getString("popis")));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return eventy;
    }

    static List<Event> getEventyMista(Misto misto){
        Connection connection = getDBConn();
        PreparedStatement statement;
        List<Event> eventy = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM event WHERE id_misto = ?");
            statement.setInt(1,misto.getId());
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                eventy.add(new Event(rs.getInt("id_event"),rs.getString("nazev"),
                        getOrganizator(rs.getInt("id_organizator")),
                        misto,rs.getInt("cena"),rs.getInt("zvyhodnena_cena"),
                        rs.getInt("kapacita"),rs.getString("popis")));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return eventy;
    }

    public static List<Organizator> getOrganizatori(){
        Connection connection = getDBConn();
        PreparedStatement statement;
        List<Organizator> organizatori = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM organizator");
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                organizatori.add(new Organizator(rs.getInt("id_organizator"),
                        rs.getString("jmeno"), rs.getString("email"),
                        rs.getInt("telefon")));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return organizatori;
    }

    public static List<Misto> getMista(){
        Connection connection = getDBConn();
        PreparedStatement statement;
        List<Misto> mista = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM misto");
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                mista.add(new Misto(rs.getInt("id_misto"),rs.getString("nazev"),
                        rs.getString("adresa"),rs.getString("email"),
                        rs.getInt("telefon")));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return mista;
    }




    // Metody pro vytváření instancí uživatelů

    public static Osoba getOsoba(String username){
        Connection connection = getDBConn();
        PreparedStatement statement;
        Osoba osoba = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM osoba WHERE username = ?");
            statement.setString(1,username);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String status = rs.getString("status");
                switch (status) {
                    case "uzivatel":
                        osoba = new Uzivatel(rs.getInt("id_osoba"), username, rs.getString("jmeno"),
                                rs.getString("email"), rs.getInt("body"));
                        break;
                    case "spravce":
                        osoba = new Spravce(rs.getInt("id_osoba"), username, rs.getString("jmeno"),
                                rs.getString("email"));
                        break;
                    case "admin":
                        osoba = new Admin(rs.getInt("id_osoba"), username, rs.getString("jmeno"),
                                rs.getString("email"));
                        break;
                }
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return osoba;
    }


    public static String databazeGetHash(String username) {
        String hash = "";
        try {
            Connection connection = getDBConn();
            Statement statement = connection.createStatement();

            String select = "SELECT pass_hash FROM osoba WHERE username = \'" + username + "\'";
            ResultSet rs = statement.executeQuery(select);
            while (rs.next()) {
                hash = rs.getString("pass_hash");
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            alertException(e);
        }
        return hash;
    }



    // Inserty

    static Event insertNewEvent(String nazev, int idMisto, int cena, int zvyhodnenaCena,
                                              int kapacita, String popis, int idOrganizator) {
        Connection connection = getDBConn();

        try {
            String insert = "INSERT INTO event (nazev, id_misto, cena, zvyhodnena_cena, kapacita, popis, " +
                    "id_organizator) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, nazev);
            statement.setInt(2, idMisto);
            statement.setInt(3, cena);
            statement.setInt(4, zvyhodnenaCena);
            statement.setInt(5, kapacita);
            statement.setString(6, popis);
            statement.setInt(7, idOrganizator);

            statement.execute();

            statement.close();
            connection.close();
        } catch (Exception e) {
            alertException(e);
        }

        int eventId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id_event FROM event WHERE nazev = ?");
            statement.setString(1,nazev);
            ResultSet rs = statement.executeQuery();

            eventId = rs.getInt("id_event");

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }

        return getEvent(eventId);
    }

    static Organizator insertNewOrganizator(String jmeno, String email, int telefon) {
        Connection connection = getDBConn();
        try {
            String insert = "INSERT INTO organizator (jmeno, email, telefon) VALUES (?, ?, ?)";
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

        int organizatorId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id_organizator FROM organizator " +
                    "WHERE jmeno = ?");
            statement.setString(1,jmeno);
            ResultSet rs = statement.executeQuery();

            organizatorId = rs.getInt("id_organizator");

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }

        return getOrganizator(organizatorId);
    }

    public static Misto insertNewMisto(String nazev, String adresa, String email, int telefon) {
        Connection connection = getDBConn();
        try {
            String insert = "INSERT INTO misto (nazev, adresa, email, telefon) VALUES (?, ?, ?, ?)";
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

        int mistoId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id_misto FROM misto WHERE " +
                    "nazev = ?");
            statement.setString(1,nazev);
            ResultSet rs = statement.executeQuery();

            mistoId = rs.getInt("id_misto");

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }

        return getMisto(mistoId);
    }





    /*

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

    */


    // Přidávání osob


    static void insertNewSpravce(String jmeno, String email, String username, String heslo) {
        try {
            Connection connection = getDBConn();

            String insert = "INSERT INTO osoba (jmeno, email, username, pass_hash, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, jmeno);
            statement.setString(2, email);
            statement.setString(3, username);
            statement.setString(4, heslo);
            statement.setString(5, "spravce");

            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }


    public static void insertNewUzivatel(String jmeno, String email, String username, String heslo) {
        try {
            Connection connection = getDBConn();

            String insert = "INSERT INTO osoba (jmeno, email, username, pass_hash, status) VALUES (?, ?, ?, ?, ?)";
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




    // Připojení DB

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
