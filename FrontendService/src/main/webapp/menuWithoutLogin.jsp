<%-- 
    Document   : menuWithoutLogin
    Created on : Mar 27, 2023, 1:18:25 AM
    Author     : student
--%>

<%@page import="java.util.HashMap"%>
<%@page import="Frontend_Helper.FoodItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Frontend_Helper.FoodXML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            FoodXML tariqthefood = (FoodXML) request.getAttribute("listoffood");
            ArrayList<FoodItem> foodlist = tariqthefood.getMenu();
            request.getSession().setAttribute("listoffood", tariqthefood.getMenu());
        %>
        <center><h2>Food Menu</h2></center>
        <center><h2>Login to Buy Food</h2></center>
        <center>
            <% if (foodlist != null && !foodlist.isEmpty()) { %>
                <table>
                    <tr>
                        <th>Food Name:</th>
                        <th>Price:</th>
                        <th>Available:</th>
                    </tr>
                    <% for (FoodItem food : foodlist) {%>
                        <tr>
                            <td><%= food.getName()%></td>
                            <td><%= food.getPrice()%></td>
                            <% if (food.getQuantity() >= 1) {
                            %>
                            <td>In Stock</td>
                            <%} else {
                            %>
                            <td>Out of Stock</td>
                            <% }
                            %>
                        </tr>
                    <% } %>
                </table>
            <% } else { %>
                <p>No food items available.</p>
            <% } %>
            <a href="index.html">
                <input type="submit" value="Login" >
            </a>
        </center>
    </body>
</html>






