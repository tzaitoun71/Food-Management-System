/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend_Helper;

/**
 *
 * @author student
 */
public class FoodItem {
    private int foodid;
    private String name;
    private double price;
    private int quantity;
    
    public FoodItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public FoodItem() {}

    public int getFoodid() {
        return foodid;
    }

    public void setFoodId(int foodid) {
        this.foodid = foodid;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
