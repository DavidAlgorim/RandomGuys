package sample;

import org.mindrot.jbcrypt.BCrypt;

public class Neregistrovany implements Zakaznik{


    public Neregistrovany(){ }

    public Uzivatel registrujSe(String jmeno, String email, String username, String heslo){
        // Zde se načtou údaje zadané uživatelem v registračním formuláři a zapíší se do DB
        //osolení a zahashování hesla
        String hesloHashed = BCrypt.hashpw(heslo, BCrypt.gensalt());


        new Databaze().databazeInsertNewUser("INSERT INTO jdbc_db.osoba (jmeno, email, username, pass_hash) VALUES (?,?,?,?)",jmeno,email,username,hesloHashed);
        // Po vytvoření a zapsání do DB bude instance nově vzniklého uživatele vrácena metodou
        return new Uzivatel(username);
    }

    @Override
    public void kupListek(Zakaznik zakaznik, Event event, boolean zvyhodneny) {
        // Přidá do DB zakoupený lístek
    }
}
