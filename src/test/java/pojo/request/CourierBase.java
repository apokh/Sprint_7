package pojo.request;

public abstract class CourierBase {
    private String login;
    private String password;

    public CourierBase(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierBase() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
