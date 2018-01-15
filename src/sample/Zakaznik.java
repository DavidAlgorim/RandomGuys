package sample;

public interface Zakaznik extends Osoba{

    // Přidá do DB zakoupený lístek
    public void kupListek(Zakaznik zakaznik, Event event, boolean zvyhodneny);

}
