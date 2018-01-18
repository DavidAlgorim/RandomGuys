package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

public class Spravce implements Osoba{

    private String username;
    private String email;
    private String jmeno;
    private List<Event> eventy;
    private List<Misto> mista;
    private List<Organizator> organizatori;
    private ObservableList<Spravce> dataSpravci;

    
    public Spravce(String username){
        this.username = username;
        ResultSet rs = null;
        nactiUdajeSpravce(rs);
    }
    
    public Spravce(String username, String email, String jmeno, List<Event> eventy, List<Misto> mista,
        List <Organizator> organizatori){
        this.username = username;
        this.email = email;
        this.jmeno = jmeno;
        this.eventy = eventy;
        this.mista = mista;
        this.organizatori = organizatori;
    }

    
    
    private void nactiUdajeSpravce(ResultSet rs){
        // Zde se načtou další údaje o správci (zbylé atributy) z DB
        // Budou využity entity Osoba(/Správce) + Event, Místo, Organizátor
        try {
            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String jmeno = rs.getString("jmeno");
                List<Event> eventy = getEventy();
                List<Misto> mista = getMista();
                List<Organizator> organizatori = getOrganizatori();
                
                dataSpravci.add(new Spravce(username, email, jmeno, eventy, mista, organizatori));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Spravce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Event pridejEvent(String nazev, Organizator organizator, Misto misto, int cena, int zvyhodnenaCena,
                             int kapacita, String popis){
        // Zapsání nového eventu do DB
        
        return new Event(nazev, organizator, misto, cena, zvyhodnenaCena, kapacita, popis);
    }


    public Misto pridejMisto(String nazev, String[] adresa, String email, String telefon){
        // Zapsání nového místa do DB

        return new Misto(nazev, adresa, email, telefon);
    }

    public Organizator pridejOrganizatora(String jmeno, String email, String telefon){
        // Zapsání nového organizátora do DB

        return new Organizator(jmeno, email, telefon);
    }

    public void zmenUdaje(String username, String jmeno, String email){
        // Metoda volaná po vyplnění formuláře "změna údajů"
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getJmeno() {
        return jmeno;
    }

    public List<Event> getEventy() {
        return eventy;
    }

    public List<Misto> getMista() {
        return mista;
    }

    public List<Organizator> getOrganizatori() {
        return organizatori;
    }
}
