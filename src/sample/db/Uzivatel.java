package sample.db;

import java.util.List;
import java.util.Objects;

public class Uzivatel implements Zakaznik{

    private int id;
    private String username;
    private String jmeno;
    private String email;
    private List<Listek> koupeneListky;
    private int bonusoveBody;

    Uzivatel(int id, String username, String jmeno, String email, int bonusoveBody){
        this.id = id;
        this.username = username;
        this.jmeno = jmeno;
        this.email = email;
        this.bonusoveBody = bonusoveBody;
    }

    public void pouzijBonusBody(Listek listek){
        // Snížení výsledné ceny a po potvrzení nákupu také snížení počtu bonusových bodů v DB
    }

    public void ohodnotEvent(Event event, int hodnoceni, String hodnoceniText){
        // Prida
    }

    public void vratListek(Listek listek){
        if(listek.vratit(this)){
        // Konkretni listek bude vymazán z DB (musí být zaznamenáno také v Event - musí být snížen počet zakoupených
        // lístků)
        }
    }

    public void zmenUdaje(String username, String jmeno, String email){
        // Metoda volaná po vyplnění formuláře "změna údajů"
    }

    @Override
    public void kupListek(Zakaznik zakaznik, Event event, boolean zvyhodneny) {
        // Přidá do DB zakoupený lístek
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzivatel uzivatel = (Uzivatel) o;
        return Objects.equals(getUsername(), uzivatel.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
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

    public List<Listek> getKoupeneListky() {
        return koupeneListky;
    }

    public int getBonusoveBody() {
        return bonusoveBody;
    }
}
