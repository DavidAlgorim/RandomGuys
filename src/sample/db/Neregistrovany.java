package sample.db;

public class Neregistrovany implements Zakaznik {


    public Neregistrovany(){ }

    public Uzivatel registrujSe(String jmeno, String email, String username, String heslo){
        // Zde se načtou údaje zadané uživatelem v registračním formuláři a zapíší se do DB



        // Po vytvoření a zapsání do DB bude instance nově vzniklého uživatele vrácena metodou
        return null;
    }

    @Override
    public void kupListek(Zakaznik zakaznik, Event event, boolean zvyhodneny) {
        // Přidá do DB zakoupený lístek
    }
}
