/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_Business;

import Menu_Helper.FoodItem;
import Menu_Helper.FoodItemService;
import Menu_Helper.FoodXML;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
public class MenuBusiness {
    
    public  FoodXML getMenu(){
       FoodItemService foodItemService = new FoodItemService();
       ArrayList<FoodItem> foodItems = foodItemService.getFood();
       
        FoodXML fd;
        fd = new FoodXML();
        fd.setMenu(foodItems);
        return (fd);
    }
    
      
}
