package Model;

/**
 * User class
 */
public class User {

    /**
     * User ID
     */
    private final int userID;

    /**
     * Username
     */
    private final String userName;

    /**
     * The User constructor
     *
     * @param userID   User ID
     * @param userName Username
     */
    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     * Gets the User ID
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets the Username
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

}