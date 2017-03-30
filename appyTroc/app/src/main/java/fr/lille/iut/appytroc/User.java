package fr.lille.iut.appytroc;

/**
 * Created by tibo on 29/03/17.
 */

public class User {

    private final String name, pwd;

    public User( String login, String pwd ) {
        this.name = login;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }
}
