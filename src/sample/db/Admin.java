package sample.db;

public class Admin extends Spravce{

    Admin(int id, String username, String jmeno, String email){
        super(id,username,jmeno,email);
    }

    public void vytvorSpravce(String username, String jmeno, String email, String heslo){
        Databaze.insertNewSpravce(username, jmeno, email, heslo);
    }

}
