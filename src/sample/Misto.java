package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Misto {

    private String nazev;
    private String adresa;
    private String email;
    private String telefon;

    public Misto(String nazev, String adresa, String email, String telefon){
        this.nazev = nazev;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
    }

    public Misto(int id){
        ResultSet rs = Databaze.databazeGETbyInt("SELECT * FROM misto WHERE id_misto = ?",id);
        try {
            nazev = rs.getString("nazev");
            adresa = rs.getString("adresa");
            email = rs.getString("email");
            telefon = rs.getString("telefon");
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Event> getEventy(){
        // Metoda načte z DB Eventy vztahující se k tomuto Místu

        return null;
    }

    public String getNazev() {
        return nazev;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }
}
