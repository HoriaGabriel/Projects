package Model;

public class Client {

    private int id;
    private String name;
    private String telephone;
    private String address;

    /**
     * Id getter
     * @return returns the id of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Id setter
     * @param id sets the id of the client
     */
    public void setId(int id) {
        this.id = id;
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
     * Telephone getter
     * @return returns the telephone of the client
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Telephone setter
     * @param telephone sets the telephone of the client
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Address getter
     * @return returns the address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Address setter
     * @param address sets the address of the client
     */
    public void setAddress(String address) {
        this.address = address;
    }
}

