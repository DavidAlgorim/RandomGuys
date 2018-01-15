package sample;

import java.util.List;

public class Organizator {

    private String jmeno;
    private String email;
    private String telefon;

    public Organizator(String jmeno, String email, String telefon){
        this.jmeno = jmeno;
        this.email = email;
        this.telefon = telefon;
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
