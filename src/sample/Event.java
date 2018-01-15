package sample;

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
        // Načte zbylé atributy eventu z databáze podle jeho názvu
    }

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
