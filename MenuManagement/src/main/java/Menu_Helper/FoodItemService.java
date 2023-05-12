/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_Helper;

import Menu_Persistance.FoodList_CRUD;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class FoodItemService {
    private FoodList_CRUD foodCRUD;
    
    public FoodItemService() {
        foodCRUD = new FoodList_CRUD();
    }
    
    public ArrayList<FoodItem> getFood() {
        return foodCRUD.getFood();
    }
}
