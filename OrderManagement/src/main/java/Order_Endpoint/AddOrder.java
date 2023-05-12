/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order_Endpoint;

import Order_Business.Messaging;
import Order_Business.OrderBusiness;
import Order_Helper.GetOrderXML;
import Order_Helper.Order;
import Order_Persistance.Order_CRUD;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("orders/{username}/{totalPrice}/{items}")
public class AddOrder {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public AddOrder() {
    }

    /**
     * Retrieves representation of an instance of
     * ryerson.ca.searchbook.endpoint.SearchResource
     *
     * @return an instance of java.lang.String
     */
    
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String addOrder(@PathParam("username") String userName, @PathParam("totalPrice") String totalPrice, @PathParam("items")String items) 
    {
     try {
        
        Order order = new Order();
        //order.setTotalPrice(totalPrice);
        order.setUsername(userName);
        order.setTotalPrice(Double.parseDouble(totalPrice));
        Order_CRUD ordercrud = new Order_CRUD();
        ordercrud.addOrder(order);
        
          if(order != null) {
              Messaging.sendmessage("ORDER:"+ order.getUsername() +":"+order.getTotalPrice()+":"+items);
              return("Inserted");
          }
          else
              return("Not inserted");
    } catch (Exception e) {
        // Handle any JAXB exceptions that occur
        return "Error processing order: " + e.getMessage();
    }
    }
    

}

