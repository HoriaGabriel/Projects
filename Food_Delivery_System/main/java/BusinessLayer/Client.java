package BusinessLayer;

import java.io.Serializable;

public class Client implements Serializable {

    private String name;
    private String password;
    private int Id;

    /**
     * Id getter
     * @return returns the id of the client
     */
    public int getId() {
        return Id;
    }

    /**
     * Id setter
     * @param id sets the id of the client
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Name getter
     * @return returns the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name sets the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Password getter
     * @return returns the password of the client
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     * @param password sets the password of the client
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

