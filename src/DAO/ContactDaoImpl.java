package DAO;

import Database.JDBC;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * The Contact DAO class
 */
public class ContactDaoImpl {

    /**
     * Gets contact data from the contacts table
     *
     * @return Contact ID, Name, and Email
     */
    public static ObservableList<Contact> buildContactsList() {
        Connection c;
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            String SQL = "SELECT * FROM contacts;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Contact newContact = new Contact(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email"));
                contacts.add(newContact);
            }
            return contacts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}