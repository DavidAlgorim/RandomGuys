package sample;

import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;
import sample.db.*;


public final class Prihlaseni {

    public static Osoba prihlasitSe(String username, String heslo){

        String hash = Databaze.databazeGetHash(username);

        if(BCrypt.checkpw(heslo, hash)) {
            return Databaze.getOsoba(username);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Chyba přihlášení!");
            alert.setContentText("Chybné uživatelské jméno nebo heslo!");
            alert.showAndWait();
            return null;
        }
    }

}
