/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "menu")
@XmlAccessorType (XmlAccessType.FIELD)
       public class FoodXML{
     @XmlElement(name="food")
           private ArrayList<FoodItem> foodItems;
           
           
           public List<FoodItem>getMenu(){
               return foodItems;
               
           }
          public  FoodXML(){
               
               
           }
           public void setMenu(ArrayList<FoodItem> fd){
               foodItems=fd;
               
           }
           
       }