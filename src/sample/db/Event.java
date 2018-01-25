package sample.db;

public class Event {

    private int id;
    private String nazev;
    private Organizator organizator;
    private Misto misto;
    private int cena;
    private int zvyhodnenaCena;
    private int kapacita;
    private String popis;
    private double hodnoceni;

    Event(int id, String nazev, Organizator organizator, Misto misto, int cena, int zvyhodnenaCena, int kapacita,
                 String popis, double hodnoceni){
        this.id = id;
        this.nazev = nazev;
        this.organizator = organizator;
        this.misto = misto;
        this.cena = cena;
        this.zvyhodnenaCena = zvyhodnenaCena;
        this.kapacita = kapacita;
        this.popis = popis;
        this.hodnoceni = hodnoceni;
    }

    int getId(){
        return id;
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

    public double getHodnoceni() {
        return hodnoceni;
    }
}
