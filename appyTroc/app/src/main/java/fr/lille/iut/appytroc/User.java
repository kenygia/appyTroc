package fr.lille.iut.appytroc;

/**
 * Created by tibo on 29/03/17.
 */

public class User {

    private final String login, pwd;

    public User( String login, String pwd ) {
        this.login = login;
        this.pwd = pwd;
    }

    public String getLogin() {
        return login;
    }

    public String getPwd() {
        return pwd;
    }
}
