/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_Helper;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class CartItem {
        private String name;
        private double price;
    private ArrayList<CartItem> items;

    public CartItem() {
        items = new ArrayList<>();
    }
        public CartItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public void addItem(String name, double price) {
        items.add(new CartItem(name, price));
    }
    }

