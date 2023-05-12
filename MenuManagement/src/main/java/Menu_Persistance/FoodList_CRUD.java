/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_Persistance;

//import buyFood.helper.FoodInfo;
import Menu_Helper.FoodItem;
import java.io.IOException;
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
public class FoodList_CRUD {

    private static Connection getCon() {
        Connection con = null;
        String connection = System.getenv("MENU_DB_URL");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/MenuManagement?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public ArrayList<FoodItem> getFood() {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        try {
            Connection con = getCon();
            String q = "SELECT foodname, foodprice, foodquantity FROM FoodList";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q);

            while (rs.next()) {
                String foodName = rs.getString("foodname");
                double foodPrice = rs.getDouble("foodprice");
                int foodQuantity = rs.getInt("foodquantity");

                FoodItem foodItem = new FoodItem(foodName, foodPrice, foodQuantity);
                foodItems.add(foodItem);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return foodItems;
    }

    public boolean addFood(FoodItem item) {
        try {
            Connection con = getCon();
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO FoodList (foodname, foodprice, foodquantity) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getQuantity());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception ex) {
            System.out.println("Adding order failed: " + ex.getMessage());
            return false;
        }
    }

    public boolean updateFoodQuantities(String[] items) {
        try {
            Connection con = getCon();
            PreparedStatement stmt = con.prepareStatement("UPDATE FoodList SET foodquantity = ? WHERE foodname = ?");

            // iterate over each item and update its quantity
            for (String item : items) {
                int quantity = getFoodQuantity(item);
                int newQuantity = quantity - 1;
                
                
                stmt.setInt(1, newQuantity);
                stmt.setString(2, item);
                stmt.executeUpdate();
            }

            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public int getFoodQuantity(String itemName) {
        int quantity = 0;
        try {
            Connection con = getCon();
            PreparedStatement stmt = con.prepareStatement("SELECT foodquantity FROM FoodList WHERE foodname = ?");
            stmt.setString(1, itemName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("foodquantity");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantity;
    }

}
