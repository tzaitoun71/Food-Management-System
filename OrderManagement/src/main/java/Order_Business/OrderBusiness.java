/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order_Business;

import Order_Helper.Order;
import Order_Helper.GetOrderXML;
import Order_Persistance.Order_CRUD;
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
public class OrderBusiness {
    
    public  GetOrderXML getOrders(){
       Order_CRUD ordercrud = new Order_CRUD();
       ArrayList<Order> orderItems = ordercrud.getAllOrders();
       
        GetOrderXML o;
        o = new GetOrderXML();
        o.setOrder(orderItems);
        return (o);
    }
    
      
}
