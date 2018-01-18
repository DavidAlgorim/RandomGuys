package sample;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;


public final class Prihlaseni {

    public static Osoba prihlasitSe(String username, String heslo){

        String hash = "";
        try {
            ResultSet rs = new Databaze().databazeGET("SELECT pass_hash FROM osoba WHERE username = ?",
                    username);
            if(rs == null)
                return null;
            hash = rs.getString(1);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(BCrypt.checkpw(heslo, hash)) {
            System.out.println("Uživatel přihlášen");
            String status = "";
            try{
                ResultSet rs = new Databaze().databazeGET("SELECT status FROM osoba WHERE username = ?",
                        username);
                status = rs.getString(1);
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            switch(status){
                case "spravce":
                    return new Spravce(username);
                case "admin":
                    return new Admin(username);
                case "uzivatel":
                default:
                    return new Uzivatel(username);
            }
        } else {
            System.out.println("Špatné jméno nebo heslo");
            return null;
        }
    }

}
