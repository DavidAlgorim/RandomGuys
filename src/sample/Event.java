package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;


public class Event {

    private String nazev;
    private Organizator organizator;
    private Misto misto;
    private int cena;
    private int zvyhodnenaCena;
    private int kapacita;
    private String popis;
    
    private ObservableList<Event> dataEventy;
    
    
    // Konstruktor pro nově vytvářený event
    public Event(String nazev, Organizator organizator, Misto misto, int cena, int zvyhodnenaCena, int kapacita,
                 String popis){
        this.nazev = nazev;
        this.organizator = organizator;
        this.misto = misto;
        this.cena = cena;
        this.zvyhodnenaCena = zvyhodnenaCena;
        this.kapacita = kapacita;
        this.popis = popis;
    }
    
    // Konstruktor pro zobrazování už vzniklého eventu
    public Event(String nazev){
        this.nazev = nazev;
        ResultSet rs = null;
        nactiUdajeEventu(rs);
    }

    private void nactiUdajeEventu(ResultSet rs){
        try {
            while (rs.next()) {
                String nazev = rs.getString("nazev");
                Organizator organizator  = getOrganizator();
                Misto misto = getMisto();
                int cena = rs.getInt("cena");
                int zvyhodnenaCena = rs.getInt("zvyhodnenaCena");
                int kapacita = rs.getInt("kapacita");
                String popis = rs.getString("popis");

                dataEventy.add(new Event(nazev, organizator, misto, cena, zvyhodnenaCena, kapacita, popis));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        // Načte zbylé atributy eventu z databáze podle jeho názvu
    

    public String getNazev() {
        return nazev;
    }

    public Organizator getOrganizator() {
        return organizator;
    }

    public Misto getMisto() {
        return misto;
    }

    public int getCena() {
        return cena;
    }

    public int getZvyhodnenaCena() {
        return zvyhodnenaCena;
    }

    public int getKapacita() {
        return kapacita;
    }

    public String getPopis() {
        return popis;
    }
}
