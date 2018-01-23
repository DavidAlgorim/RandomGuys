package sample.db;

import java.util.List;

public class Misto {

    private int id;
    private String nazev;
    private String adresa;
    private String email;
    private int telefon;

    Misto(int id, String nazev, String adresa, String email, int telefon){
        this.id = id;
        this.nazev = nazev;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
    }

    public List<Event> getEventy(){
        return Databaze.getEventyMista(this);
    }

    int getId(){
        return id;
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

    public int getTelefon() {
        return telefon;
    }
}
