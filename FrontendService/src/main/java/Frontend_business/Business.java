/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend_business;

import Frontend_Helper.FoodItem;
import Frontend_Helper.FoodXML;
import Frontend_Helper.GetOrderXML;
import Frontend_Helper.Order;
import Frontend_Helper.OrderXML;
import Frontend_persistance.User_CRUD;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author student
 */
public class Business {
    public boolean isAuthenticated(String username, String password) {
        User_CRUD userCrud = new User_CRUD();
        if (userCrud.isValidUser(username, password)) {
            return true;
        }
        return false;
    }

    public boolean isAdmin(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        }
        return false;
    }

    public static FoodXML getServices(String token) throws IOException {
        Client menuClient = ClientBuilder.newClient();
        String menuService = System.getenv("menuService");
        WebTarget menuWebTarget = menuClient.target("http://"+menuService+"/MenuManagement/webresources/menu");
        InputStream is = menuWebTarget.path("").request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        FoodXML foods = foodxmltoObjects(xml);
        return (foods);

    }

    public static void addOrder(String token, Order order, String items) throws IOException {

        if (token == null) {
            return;
        }
        String orderService = System.getenv("orderService");
        String url = "http://"+orderService+"/OrderManagement/webresources/orders"
                + "/" + order.getUsername()
                + "/" + order.getTotalPrice()
                + "/" + items;

        Client menuClient = ClientBuilder.newClient();
        WebTarget menuWebTarget = menuClient.target(url);
        Response response = menuWebTarget.path("").request(MediaType.TEXT_HTML).get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            String s = "slay";
            System.out.println("YES");
            // response was successful
        } else {
            // response was not successful
        }
    }
    
    public static void addFood(String token, FoodItem foodItem) throws IOException {
        String menuService = System.getenv("menuService");
        String url = "http://"+menuService+"/MenuManagement/webresources/foodlist"
                + "/" + foodItem.getName()
                + "/" + foodItem.getPrice()
                + "/" + foodItem.getQuantity();

        Client menuClient = ClientBuilder.newClient();
        WebTarget menuWebTarget = menuClient.target(url);
        Response response = menuWebTarget.path("").request(MediaType.TEXT_HTML).get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            String s = "slay";
            System.out.println("YES");
            // response was successful
        } else {
            // response was not successful
        }
    }
    
    private static FoodXML foodxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(FoodXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            FoodXML foods = (FoodXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return foods;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GetOrderXML getOrder(String token) throws IOException {

        if (token == null) {
            return new GetOrderXML();
        }
        String orderService = System.getenv("orderService");
        Client menuClient = ClientBuilder.newClient();
        WebTarget menuWebTarget = menuClient.target("http://"+orderService+"/OrderManagement/webresources/orders");
        InputStream is = menuWebTarget.path("").request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        GetOrderXML orders = orderxmltoObjects(xml);
        return (orders);

    }

    private static GetOrderXML orderxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(GetOrderXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            GetOrderXML orders = (GetOrderXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return orders;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
