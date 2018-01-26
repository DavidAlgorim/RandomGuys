package sample.db;

import javafx.scene.control.Alert;

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
                        rs.getString("popis"), rs.getDouble("AVG(hodnoceni)"));
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
            statement = connection.prepareStatement("SELECT id_event, nazev, cena, zvyhodnena_cena, id_organizator, kapacita, popis, id_misto, AVG(hodnoceni) FROM event JOIN hodnoceni USING (id_event)");
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                eventy.add(new Event(rs.getInt("id_event"),rs.getString("nazev"),
                        getOrganizator(rs.getInt("id_organizator")),
                        getMisto(rs.getInt("id_misto")),rs.getInt("cena"),
                        rs.getInt("zvyhodnena_cena"),rs.getInt("kapacita"),
                        rs.getString("popis"), rs.getDouble("AVG(hodnoceni)")));
                System.out.println(rs.getDouble("AVG(hodnoceni)"));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return eventy;
    }

    public static List<Event> getEventyOrganizatora(Organizator organizator){
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
                        rs.getInt("kapacita"),rs.getString("popis"), rs.getDouble("AVG(hodnoceni)")));
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return eventy;
    }

    public static List<Event> getEventyMista(Misto misto){
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
                        rs.getInt("kapacita"),rs.getString("popis"), rs.getDouble("AVG(hodnoceni)")));
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

    public static List<Listek> getListkyUzivatele(Uzivatel uzivatel) {
                Connection connection = getDBConn();
                PreparedStatement statement;
                List<Listek> listky = new ArrayList<>();
                try {
                        statement = connection.prepareStatement("SELECT * FROM listek WHERE id_osoba = ?");
                        statement.setInt(1, uzivatel.getId());

                                ResultSet rs = statement.executeQuery();

                                while (rs.next()) {
                                listky.add(new Listek(rs.getInt("id_listek"), uzivatel, getEvent(rs.getInt("id_event")), rs.getBoolean("zvyhodneni")));
                            }

                               rs.close();
                        statement.close();
                        connection.close();

                            } catch (SQLException | NullPointerException ex) {
                        alertException(ex);
                    }
                return listky;
            }

    public static List<Misto> getMista(){
        Connection connection = getDBConn();
        PreparedStatement statement;
        List<Misto> mista = new ArrayList<>();
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
    
    public static int getIdMista(String nazev){
        Connection connection = getDBConn();
        PreparedStatement statement;
        int id = 0;
        try {
            statement = connection.prepareStatement("SELECT id_misto FROM misto WHERE nazev = ?");
            statement.setString(1, nazev);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                id = rs.getInt("id_misto");
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return id;
    }
    
    public static int getIdOrganizatora(String nazev){
        Connection connection = getDBConn();
        PreparedStatement statement;
        int id = 0;
        try {
            statement = connection.prepareStatement("SELECT id_organizator FROM organizator WHERE jmeno = ?");
            statement.setString(1, nazev);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                id = rs.getInt("id_organizator");
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException ex) {
            alertException(ex);
        }
        return id;
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
                        System.out.println("db uzivatel");
                        osoba = new Uzivatel(rs.getInt("id_osoba"), username, rs.getString("jmeno"),
                                rs.getString("email"), rs.getInt("body"));
                        break;
                    case "spravce":
                        System.out.println("db spravce");
                        osoba = new Spravce(rs.getInt("id_osoba"), username, rs.getString("jmeno"),
                                rs.getString("email"));
                        break;
                    case "admin":
                        System.out.println("db admin");
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

    public static void insertNewEvent(String nazev, int idMisto, int cena, int zvyhodnenaCena,
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
    }

    public static void insertNewOrganizator(String jmeno, String email, int telefon) {
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
    }

    public static void insertNewMisto(String nazev, String adresa, String email, int telefon) {
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
    }

    public static void insertNewSpravce(String jmeno, String email, String username, String heslo) {
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
    //pro registrovanýho
    public static void insertNewListek(boolean zvyhodneni, Event event, Uzivatel uzivatel) {
        try {
            Connection connection = getDBConn();

            String insert = "INSERT INTO listek (zvyhodneni, id_event, id_osoba) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setBoolean(1, zvyhodneni);
            statement.setInt(2, event.getId());
            statement.setInt(3, uzivatel.getId());


            System.out.println("success");
            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }

    //pro neregistrovanýho
    public static void insertNewListekNerergistrovany(String jmeno, boolean zvyhodneni, Event event) {
        try {
            Connection connection = getDBConn();

            String insert = "INSERT INTO listek (zakaznik, zvyhodneni, id_event) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, jmeno);
            statement.setBoolean(2, zvyhodneni);
            statement.setInt(3, event.getId());
            System.out.println("success");

            statement.execute();

            statement.close();
            connection.close();

        } catch (Exception e) {
            alertException(e);
        }
    }

    
    
public static void removeEvent(String nazev) {
        Connection connection = getDBConn();

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM event where nazev = ?");
            statement.setString(1, nazev);

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
