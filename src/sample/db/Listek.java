package sample.db;

public class Listek {

    private int id;
    private Zakaznik zakaznik;
    private Event event;
    private boolean zvyhodneny;

    public Listek(int id, Zakaznik zakaznik, Event event, boolean zvyhodneny){
        this.id = id;
        this.zakaznik = zakaznik;
        this.event = event;
        this.zvyhodneny = zvyhodneny;
    }

    public boolean vratit(Uzivatel uzivatel){
        // Zjistí se, zda lístek vrací uživatel, který ho ve skutečnosti vlastní
        return uzivatel.equals(getZakaznik());
    }

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

    public Event getEvent() {
        return event;
    }

    public boolean isZvyhodneny() {
        return zvyhodneny;
    }
}
