<%-- 
    Document   : adminForm
    Created on : Feb 15, 2023, 1:15:28 PM
    Author     : student
--%>

<%@page import="Frontend_Helper.Order"%>
<%@page import="Frontend_Helper.GetOrderXML"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <form action="Admin" method="POST">
            <label for="foodname">Food Name:</label>
            <input type="text" id="foodname" name="foodname"><br><br>

            <label for="foodprice">Food Price:</label>
            <input type="text" id="foodprice" name="foodprice"><br><br>

            <label for="foodQuantity">Food Quantity:</label>
            <input type="text" id="foodQuantity" name="foodQuantity"><br><br>

            <input type="submit" value="Submit">
        </form>

        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Username</th>
                    <th>Total Price</th>
                </tr>
            </thead>
            <tbody>
                <%
                    GetOrderXML order = (GetOrderXML) request.getAttribute("listoforders");
                    if (order != null && order.getOrders() != null) {
                        ArrayList<Order> orderList = order.getOrders();
                        for (Order order : orderList) {
                %>
                <tr>
                    <td><%=order.getOrderID()%></td>
                    <td><%=order.getUsername()%></td>
                    <td><%=order.getTotalPrice()%></td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3">No orders to be displayed.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </center>
    <center>
        <a href="index.html">
            <input type="submit" value="Return">
        </a>
    </center>
</body>
</html>
