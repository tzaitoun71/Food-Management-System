/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend_Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.NONE)
public class OrderXML {

    @XmlElement(name = "userName")
    private String userName;
    @XmlElement(name = "totalCost")
    private double totalCost;

    public String getUserName() {
        return userName;

    }

    public OrderXML() {

    }

    public void setUserName(String userName) {
        this.userName = userName;

    }

    public double getTotalCost() {
        return totalCost;

    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;

    }

}
