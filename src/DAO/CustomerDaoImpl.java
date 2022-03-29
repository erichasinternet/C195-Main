package DAO;

import Database.JDBC;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Customer DAO class
 */
public class CustomerDaoImpl {

    /**
     * Gets customer data for the Customers tableview
     *
     * @return Customer data
     */
    public static ObservableList<Customer> buildCustomers() {
        Connection c;
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            String SQL = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, countries.Country, first_level_divisions.Division, customers.Division_ID, countries.Country_ID FROM customers INNER JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Customer newCustomer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Country"),
                        rs.getString("Division"),
                        rs.getInt("Division_ID"),
                        rs.getInt("Country_ID"));

                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a list of customers
     *
     * @return Customer ID and Customer Name from the customers table
     */
    public static ObservableList<Customer> buildCustomerList() {
        Connection c;
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            String SQL = "SELECT Customer_ID, Customer_Name FROM customers;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Customer newCustomer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"));
                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Allows the user to update the customer's information in the database
     *
     * @param CustomerID
     * @param CustomerName
     * @param CustomerAddress
     * @param CustomerPostalCode
     * @param CustomerPhone
     * @param DivisionID
     * @return
     * @throws SQLException
     */
    public static boolean updateCustomer(int CustomerID, String CustomerName, String CustomerAddress, String CustomerPostalCode, String CustomerPhone, String DivisionID) throws SQLException {
        Connection c;
        try {
            c = JDBC.getConnection();
            String SQL = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setString(1, CustomerName);
            statement.setString(2, CustomerAddress);
            statement.setString(3, CustomerPostalCode);
            statement.setString(4, CustomerPhone);
            statement.setInt(5, Integer.parseInt(DivisionID));
            statement.setInt(6, CustomerID);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Allows the user to create a new customer in the database
     *
     * @param CustomerName
     * @param CustomerAddress
     * @param CustomerPostalCode
     * @param CustomerPhone
     * @param DivisionID
     * @return
     * @throws SQLException
     */
    public static boolean createCustomer(String CustomerName, String CustomerAddress, String CustomerPostalCode, String CustomerPhone, String DivisionID) throws SQLException {
        Connection c;
        try {
            c = JDBC.getConnection();
            String SQL = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setString(1, CustomerName);
            statement.setString(2, CustomerAddress);
            statement.setString(3, CustomerPostalCode);
            statement.setString(4, CustomerPhone);
            statement.setInt(5, Integer.parseInt(DivisionID));
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Allows the user to delete a customer from the database
     *
     * @param CustomerID
     * @return
     * @throws SQLException
     */
    public static boolean deleteCustomer(int CustomerID) throws SQLException {
        Connection c;
        try {
            c = JDBC.getConnection();
            String SQL = "DELETE FROM customers WHERE Customer_ID = ?;";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setInt(1, CustomerID);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}