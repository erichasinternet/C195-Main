package Model;

/**
 * The contact class
 */
public class Contact {
    private int ID;
    private String name;
    private String email;

    /**
     * The contact constructor
     *
     * @param ID
     * @param name
     * @param email
     */
    public Contact(int ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    /**
     * The contact ID getter
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * The contact name getter
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * The contact name setter
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}