/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu_Endpoint;

import Menu_Business.MenuBusiness;
import Menu_Helper.FoodXML;
import java.io.StringWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("menu")
public class GetMenu {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetMenu
     */
    public GetMenu() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml() {
        MenuBusiness menuBusiness= new MenuBusiness();
        FoodXML menu = menuBusiness.getMenu();
        System.out.println(">>>>>>>>>>>>>>>>>>" + menu);
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(FoodXML.class);
        
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(menu, sw);
     
    return (sw.toString());
    
      } catch (JAXBException ex) {
            Logger.getLogger(GetMenu.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
    }
}
