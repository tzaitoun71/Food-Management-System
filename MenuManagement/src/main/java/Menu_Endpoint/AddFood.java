/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_Endpoint;

import Menu_Helper.FoodItem;
import Menu_Persistance.FoodList_CRUD;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author student
 */
@Path("foodlist/{foodname}/{foodprice}/{foodQuantity}")
public class AddFood {
    @Context
    private UriInfo context;
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String addFood(@PathParam("foodname") String foodName, @PathParam("foodprice") String foodPrice, @PathParam("foodQuantity") String foodQuantity) 
    {
     try {
        
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodName);
        foodItem.setPrice(Double.parseDouble(foodPrice));
        foodItem.setQuantity(Integer.parseInt(foodQuantity));
        FoodList_CRUD foodCRUD = new FoodList_CRUD();
        foodCRUD.addFood(foodItem);
        String[] items = new String[]{foodName};
        foodCRUD.updateFoodQuantities(items);
        
        if(foodItem != null)
              return("Inserted");
          else
              return("Not inserted");
    } catch (Exception e) {
        // Handle any JAXB exceptions that occur
        return "Error processing order: " + e.getMessage();
    }
    }
}
