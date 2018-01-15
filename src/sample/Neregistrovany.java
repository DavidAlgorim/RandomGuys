package sample;

public class Neregistrovany implements Zakaznik{


    public Neregistrovany(){ }

    public Uzivatel registrujSe(String username, String email, String jmeno, String heslo){
        // Zde se načtou údaje zadané uživatelem v registračním formuláři a zapíší se do DB

        // Po vytvoření a zapsání do DB bude instance nově vzniklého uživatele vrácena metodou
        return new Uzivatel(username);
    }

    @Override
    public void kupListek(Zakaznik zakaznik, Event event, boolean zvyhodneny) {
        // Přidá do DB zakoupený lístek
    }
}
