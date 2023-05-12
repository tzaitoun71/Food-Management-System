/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend_persistance;

import Frontend_Helper.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author student
 */
public class User_CRUD {

    private static Connection getCon() {
        Connection con = null;
        String connection = System.getenv("USER_DB_URL");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/FMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public boolean addUser(User user) {
        try {
            Connection con = getCon();
            PreparedStatement statement = con.prepareStatement("INSERT INTO UserList (username, password, address, email) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getAddress());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception ex) {
            System.out.println("Error adding user: " + ex.getMessage());
            return false;
        }
    }

    public boolean checkUser(String username, String password) {
        try {
            Connection con = getCon();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM UserList WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            System.out.println("Error checking user: " + ex.getMessage());
            return false;
        }
    }

    public boolean isValidUser(String username, String password) {
        boolean result = false;
        try {
            Connection con = getCon();
            String query = "SELECT COUNT(*) FROM UserList WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 1) {
                    result = true;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUser(String username) {
        User user = null;
        try {
            Connection con = getCon();
            String query = "SELECT * FROM UserList WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateEmail(String username, String password, String newEmail) {
        try {
            Connection con = getCon();
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE Users SET email=? WHERE username=? AND password=?");
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            System.out.println("Email updated successfully.");
        } catch (Exception ex) {
            System.out.println("Updating email failed: " + ex.getMessage());
        }
    }

    public void updateAddress(String username, String password, String newAddress) {
        try {
            Connection con = getCon();
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE Users SET address=? WHERE username=? AND password=?");
            preparedStatement.setString(1, newAddress);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            System.out.println("Address updated successfully.");
        } catch (Exception ex) {
            System.out.println("Updating address failed: " + ex.getMessage());
        }
    }

}
