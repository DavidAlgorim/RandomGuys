package sample;

public class Admin extends Spravce{

    private String username;
    private String email;
    private String jmeno;

    public Admin(String username){
        super(username);
        this.username = username;
        // Načtení emailu a jména z DB podle username
    }

    public void vytvorSpravce(String username, String jmeno, String email, String heslo){
        // Zapíše nového správce do DB, podle výstupu z formuláře "přidání nového správce"
    }

}
