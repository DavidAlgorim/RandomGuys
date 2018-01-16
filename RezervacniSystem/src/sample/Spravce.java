package sample;

import java.util.List;

public class Spravce implements Osoba{

    private String username;
    private String email;
    private String jmeno;
    private List<Event> eventy;
    private List<Misto> mista;
    private List<Organizator> organizatori;

    public Spravce(String username){
        this.username = username;
        nactiUdajeSpravce();
    }

    private void nactiUdajeSpravce(){
        // Zde se načtou další údaje o správci (zbylé atributy) z DB
        // Budou využity entity Osoba(/Správce) + Event, Místo, Organizátor
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
