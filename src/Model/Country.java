package Model;

/**
 * The country class
 */
public class Country {
    private final int country_ID;

    private String country;

    /**
     * The country constructor
     *
     * @param country_ID
     * @param country
     */
    public Country(int country_ID, String country) {
        this.country_ID = country_ID;
        this.country = country;
    }

    /**
     * The country getter
     *
     * @return
     */
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String name) {
        this.country = name;
    }

    public int getCountryID() {
        return this.country_ID;
    }
}