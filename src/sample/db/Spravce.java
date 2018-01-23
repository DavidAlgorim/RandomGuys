package sample.db;

public class Spravce implements Osoba{

    private int id;
    private String username;
    private String jmeno;
    private String email;

    
    Spravce(int id, String username, String jmeno, String email){
        this.id = id;
        this.username = username;
        this.jmeno = jmeno;
        this.email = email;
    }

    public Event pridejEvent(String nazev, Organizator organizator, Misto misto, int cena, int zvyhodnenaCena,
                             int kapacita, String popis){
        return Databaze.insertNewEvent(nazev,misto.getId(),cena,zvyhodnenaCena,kapacita,popis,organizator.getId());
    }

    public Organizator pridejOrganizatora(String jmeno, String email, int telefon){
        return Databaze.insertNewOrganizator(jmeno,email,telefon);
    }

    public Misto pridejMisto(String nazev, String adresa, String email, int telefon){
        return Databaze.insertNewMisto(nazev,adresa,email,telefon);
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
}
