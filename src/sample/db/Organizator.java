package sample.db;

import java.util.List;

public class Organizator {

    private int id;
    private String jmeno;
    private String email;
    private int telefon;

    Organizator(int id, String jmeno, String email, int telefon){
        this.id = id;
        this.jmeno = jmeno;
        this.email = email;
        this.telefon = telefon;
    }

    public List<Event> getEventy(){
        return Databaze.getEventyOrganizatora(this);
    }

    int getId(){
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefon() {
        return telefon;
    }
}
