package POJO;

public class CourierToCreate extends CourierBase {
    private String firstName;

    public CourierToCreate(String login, String password, String firstName) {
        super(login, password);
        this.firstName = firstName;
    }

    public CourierToCreate(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
