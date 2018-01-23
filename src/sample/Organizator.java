package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Organizator {

    private String jmeno;
    private String email;
    private String telefon;

    public Organizator(String jmeno, String email, String telefon){
        this.jmeno = jmeno;
        this.email = email;
        this.telefon = telefon;
    }

    public Organizator(int id){
        ResultSet rs = Databaze.databazeGETbyInt("SELECT * FROM organizator WHERE id_organizator = ?",id);
        try {
            jmeno = rs.getString("jmeno");
            email = rs.getString("email");
            telefon = rs.getString("telefon");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Event> getEventy(){
        // Metoda načte z DB Eventy vztahující se k tomuto Organizátorovi

        return null;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }
}
