package Model;

/**
 * Division class
 */
public class Division {
    private final int divisionID;
    private final int countryID;
    private String division;

    /**
     * The Division constructor
     *
     * @param divisionID
     * @param countryID
     * @param division
     */
    public Division(int divisionID, int countryID, String division) {
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.division = division;

    }

    /**
     * The division getter
     *
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * The division setter
     *
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * The division ID getter
     *
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }
}