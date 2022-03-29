package DAO;

import Database.JDBC;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The division DAO class
 */
public class DivisionDaoImpl {

    /**
     * Gets country data
     *
     * @param country
     * @return The Division ID, Country ID, and Division
     */
    public ObservableList<Division> getCountry(String country) {
        try {
            String SQL = "SELECT first_level_divisions.Division_ID, countries.Country_ID, first_level_divisions.Division  FROM countries, first_level_divisions WHERE countries.Country = ? and countries.Country_ID = first_level_divisions.Country_ID;";
            Connection c = JDBC.getConnection();
            ObservableList<Division> divisions = FXCollections.observableArrayList();

            PreparedStatement ps = c.prepareStatement(SQL);
            ps.setString(1, country);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                divisions.add(createDivision(rs));
            }

            return divisions;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Creates a division object from the database
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Division createDivision(ResultSet rs) throws SQLException {
        return new Division(
                rs.getInt("Division_ID"),
                rs.getInt("Country_ID"),
                rs.getString("Division"));
    }
}
