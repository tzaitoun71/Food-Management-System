/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order_Persistance;

import Order_Helper.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Order_CRUD {

    private static Connection getCon() {
        Connection con = null;
        String connection = System.getenv("ORDER_DB_URL");
        try {

            Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://"+connection+"/OrderManagement?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public void addOrder(Order order) {
        try {
            Connection con = getCon();
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Orders (cname, fprice) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getUsername());
            preparedStatement.setDouble(2, order.getTotalPrice());
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getInt(1));
            }
        } catch (Exception ex) {
            System.out.println("Adding order failed: " + ex.getMessage());
        }
    }
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection con = getCon();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Orders");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderid");
                String username = rs.getString("cname");
                double totalCost = rs.getDouble("fprice");
                Order order = new Order();
                order.setOrderId(id);
                order.setUsername(username);
                order.setTotalPrice(totalCost);
                
                orders.add(order);
            }
        } catch (Exception ex) {
            System.out.println("Getting orders failed: " + ex.getMessage());
        }
        return orders;
    }
}

