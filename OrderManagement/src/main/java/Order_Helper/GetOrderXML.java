/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order_Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orderlist")
@XmlAccessorType (XmlAccessType.FIELD)
       public class GetOrderXML{
     @XmlElement(name="orders")
           private ArrayList<Order> orderList;
           
           
           public List<Order>getOrders(){
               return orderList;
               
           }
          public  GetOrderXML(){
               
               
           }
           public void setOrder(ArrayList<Order> o){
               orderList=o;
               
           }
           
       }