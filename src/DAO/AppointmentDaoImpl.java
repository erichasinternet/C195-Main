package DAO;

import Database.JDBC;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * The Appointment DAO class
 */
public class AppointmentDaoImpl {

    /**
     * Gets appointment data from the appointments table for the appointment tableview
     *
     * @return
     */
    public static ObservableList<Appointment> buildAppointments() {
        Connection c;
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments, contacts WHERE appointments.Contact_ID = contacts.Contact_ID;";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Appointment newAppointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getDate("End").toLocalDate(),
                        rs.getTimestamp("End").toLocalDateTime(),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets data for the togglegroup, filters by the current week
     *
     * @return
     */
    public static ObservableList<Appointment> buildWeek() {
        Connection c;
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE week(Start)=week(now());";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Appointment newAppointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getDate("End").toLocalDate(),
                        rs.getTimestamp("End").toLocalDateTime(),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets data for the togglegroup, filters by the current month
     *
     * @return
     */
    public static ObservableList<Appointment> buildMonth() {
        Connection c;
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = " SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE month(Start)=month(now());";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Appointment newAppointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getDate("End").toLocalDate(),
                        rs.getTimestamp("End").toLocalDateTime(),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"));

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Allows the user to update the appointment information in the database
     *
     * @param aptID
     * @param title
     * @param description
     * @param location
     * @param contactName
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     * @return
     * @throws SQLException
     */
    public static boolean updateAppointments(int aptID, String title, String description, String location, int contactName, String type,
                                             LocalDateTime start, LocalDateTime end, int customerID, int userID) throws SQLException {
        Connection c;
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Contact_ID = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ? WHERE Appointment_ID = ?;";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, location);
            statement.setInt(4, contactName);
            statement.setString(5, type);
            statement.setTimestamp(6, Timestamp.valueOf(start));
            statement.setTimestamp(7, Timestamp.valueOf(end));
            statement.setInt(8, customerID);
            statement.setInt(9, userID);
            statement.setInt(10, aptID);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Allows the user to create an appointment in the database
     *
     * @param title
     * @param description
     * @param location
     * @param contactName
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     * @return
     * @throws SQLException
     */
    public static boolean createAppointment(String title, String description, String location, int contactName, String type,
                                            LocalDateTime start, LocalDateTime end, int customerID, int userID) throws SQLException {
        Connection c;
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = "INSERT INTO appointments(Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, location);
            statement.setInt(4, contactName);
            statement.setString(5, type);
            statement.setTimestamp(6, Timestamp.valueOf(start));
            statement.setTimestamp(7, Timestamp.valueOf(end));
            statement.setInt(8, customerID);
            statement.setInt(9, userID);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Warns the user if there is an appointment within 15 minutes from the current time
     *
     * @return
     */
    public static ObservableList<Appointment> appointmentAlerts() {
        Connection c;
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = "SELECT Appointment_ID, Start FROM appointments a WHERE a.start >= now() and a.start <= date_add(now(), interval 15 minute);";
            //
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Appointment newAppointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getTimestamp("Start").toLocalDateTime());

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates an appointment object from the database
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static Appointment newAppointment(ResultSet rs) throws SQLException {
        return new Appointment(
                rs.getInt("Appointment_ID"),
                rs.getString("Title"),
                rs.getString("Description"),
                rs.getString("Location"),
                rs.getString("Contact_Name"),
                rs.getString("Type"),
                rs.getDate("Start").toLocalDate(),
                rs.getTimestamp("Start").toLocalDateTime(),
                rs.getDate("End").toLocalDate(),
                rs.getTimestamp("End").toLocalDateTime(),
                rs.getInt("Customer_ID"),
                rs.getInt("User_ID"),
                rs.getInt("Contact_ID"));
    }

    /**
     * Gets appointment data by customer ID
     *
     * @param customerID
     * @return
     */
    public static ObservableList<Appointment> getCustomerID(int customerID) {
        Connection c;
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments, contacts WHERE appointments.Customer_ID = ?;";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setInt(1, customerID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                appointments.add(newAppointment(rs));
            }

            return appointments;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Allows the user to delete an appointment from the database
     *
     * @param AptID
     * @return
     * @throws SQLException
     */
    public static boolean deleteAppointment(int AptID) throws SQLException {
        Connection c;
        try {
            c = JDBC.getConnection();
            //SQL Query
            String SQL = "DELETE FROM appointments WHERE Appointment_ID = ?;";

            PreparedStatement statement = c.prepareStatement(SQL);
            statement.setInt(1, AptID);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}