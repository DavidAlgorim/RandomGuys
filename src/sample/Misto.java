package sample;

import java.util.List;

public class Misto {

    private String nazev;
    private String[] adresa;
    private String email;
    private String telefon;

    public Misto(String nazev, String[] adresa, String email, String telefon){
        this.nazev = nazev;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
    }

    public List<Event> getEventy(){
        // Metoda načte z DB Eventy vztahující se k tomuto Místu

        return null;
    }

    public String getNazev() {
        return nazev;
    }

    public String[] getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }
}
