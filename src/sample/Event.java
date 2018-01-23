package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Event {

    private String nazev;
    private Organizator organizator;
    private Misto misto;
    private int cena;
    private int zvyhodnenaCena;
    private int kapacita;
    private String popis;
    
    
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
        nactiUdajeEventu();
    }

    private void nactiUdajeEventu(){
        ResultSet rs = Databaze.databazeGETbyString("SELECT * FROM event WHERE nazev = ?",nazev);
        try {
            while (rs.next()) {
                nazev = rs.getString("nazev");
                organizator = new Organizator(Databaze.databazeGETbyString("SELECT id_organizator FROM event "+
                        "WHERE nazev = ?",nazev).getInt("id_organizator"));
                misto = new Misto(Databaze.databazeGETbyString("SELECT id_misto FROM event WHERE nazev = ?",
                        nazev).getInt("id_misto"));
                cena = rs.getInt("cena");
                zvyhodnenaCena = rs.getInt("zvyhodnena_cena");
                kapacita = rs.getInt("kapacita");
                popis = rs.getString("popis");
                rs.close();
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
