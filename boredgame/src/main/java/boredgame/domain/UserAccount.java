package boredgame.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UserAccount {

    @NotEmpty(message = "{UserAccount.firstName.NotEmpty}")
    private String firstName;
    @NotEmpty(message = "{UserAccount.lastName.NotEmpty}")
    private String lastName;
    private String id;

    @NotEmpty(message = "{UserAccount.userName.NotEmpty}")
    private String userName;

    private List<String> roles = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
