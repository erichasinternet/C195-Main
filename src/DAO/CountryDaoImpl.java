package DAO;

import Database.JDBC;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * The country DAO class
 */
public class CountryDaoImpl {

    /**
     * Gets country data from the countries table
     *
     * @return The Country ID and Country Name
     */
    public static ObservableList<Country> getCountries() {
        Connection c;
        ObservableList<Country> countries = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            String SQL = "SELECT * FROM countries";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Country newCountry = new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"));
                countries.add(newCountry);
            }
            return countries;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}