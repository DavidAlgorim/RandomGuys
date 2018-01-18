package sample;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;


public class Prihlaseni {


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
