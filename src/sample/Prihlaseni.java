package sample;

import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Prihlaseni {

    private static String hash = "";
    private static String status = "";

    public static Osoba prihlasitSe(String username, String heslo){

        hash = Databaze.databazeGetHash(username);
        status = Databaze.databazeGetStatus(username);


        if(BCrypt.checkpw(heslo, hash)) {
            switch (status) {
                case "spravce":
                    System.out.println("správce potvrzen");
                    return new Spravce(username);
                case "admin":
                    System.out.println("admin potvrzen");
                    return new Admin(username);
                case "uzivatel":
                default:
                    System.out.println("uživatel potvrzen");
                    return new Uzivatel(username);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Chyba přihlášení!");
            alert.setContentText("Chybné uživatelské jméno nebo heslo!");
            alert.showAndWait();
            return null;
        }
    }

}
