package Model;

/**
 * The customer class
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String country;
    private String division;
    private int divisionID;
    private int countryID;

    /**
     * The customer constructor
     *
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param country
     * @param division
     * @param divisionID
     * @param countryID
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerPostalCode,
                    String customerPhone, String country, String division, int divisionID, int countryID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.country = country;
        this.division = division;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
        this.countryID = countryID;
    }

    /**
     * The customer constructor for the Customer List
     *
     * @param customerID
     * @param customerName
     */
    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    /**
     * The customer ID getter
     *
     * @return
     */
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * The customer name getter
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * The customer address getter
     *
     * @return
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * The customer postal code getter
     *
     * @return
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * The customer phone number getter
     *
     * @return
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * The customer country getter
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * The customer division getter
     *
     * @return
     */
    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * The customer division ID getter
     *
     * @return
     */
    public int getCustomerDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * The customer Country ID getter
     *
     * @return
     */
    public int getCountryID() {
        return countryID;
    }

}