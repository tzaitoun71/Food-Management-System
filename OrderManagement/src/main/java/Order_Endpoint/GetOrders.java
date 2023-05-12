/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order_Endpoint;

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
@Path("orders")
public class GetOrders {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public GetOrders() {
    }

    /**
     * Retrieves representation of an instance of
     * ryerson.ca.searchbook.endpoint.SearchResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getOrders() {
        OrderBusiness orderBusiness = new OrderBusiness();
        GetOrderXML orders = orderBusiness.getOrders();
        System.out.println(">>>>>>>>>>>>>>>>>>" + orders);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(GetOrderXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(orders, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(GetOrders.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }

}
