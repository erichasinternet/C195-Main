package DAO;

import Database.JDBC;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The User DAO class
 */
public class UserDaoImpl {

    /**
     * @return User ID and Username from the users table
     */
    public static ObservableList<User> buildUsersList() {
        Connection c;
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            String SQL = "SELECT User_ID, User_Name FROM users;";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                User newUser = new User(
                        rs.getInt("User_ID"),
                        rs.getString("User_Name"));
                users.add(newUser);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param username
     * @param password
     * @return All fields from the users table with matching username and password
     * @throws SQLException
     */
    public static boolean checkLogin(String username, String password) throws SQLException {
        String search = "SELECT * FROM users WHERE User_Name=? AND Password=?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(search);

        ps.setString(1, username);
        ps.setString(2, password);

        try {
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            return (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}