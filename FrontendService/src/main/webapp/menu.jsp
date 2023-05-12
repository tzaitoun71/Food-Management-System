<%@page import="Frontend_Helper.FoodXML"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="Frontend_Helper.FoodItem"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
//            ArrayList<FoodItem> foodlist = (ArrayList) request.getSession().getAttribute("listoffood");
            FoodXML tariqthefood = (FoodXML) request.getAttribute("listoffood");
            
            ArrayList<FoodItem> foodlist = tariqthefood.getMenu();
            HashMap<String, FoodItem> cart = (HashMap<String, FoodItem>) session.getAttribute("cart");
            double cartTotal = 0.0;
            if (cart != null) {
                for (FoodItem item : cart.values()) {
                    cartTotal += item.getPrice();
                }
            }
            
            request.getSession().setAttribute("listoffood", tariqthefood.getMenu());
        %>
        <center><h2>Food Menu</h2></center>
        <center><h2>Hello <%=session.getAttribute("uname")%></h2></center>
        <center>
            <form action="checkout.jsp"  method="get">
                <% if (foodlist != null) { %>
                <table>
                    <tr>
                        <th>Food Name:</th>
                        <th>Price:</th>
                        <th>Available:</th>
                        <th>Add to Cart:</th>
                    </tr>
                    <% for (FoodItem food : foodlist) {%>
                    <tr>
                        <td><%= food.getName()%></td>
                        <td><%= food.getPrice()%></td>
                        <% if (food.getQuantity() >= 1) {
                        %>
                        <td>In Stock</td>
                        <td>
                            <input type="checkbox" name="cartItems" value="<%= food.getName()%>" <% if (cart != null && cart.containsKey(food.getName())) { %>checked<% } %>/>
                        </td>
                        <%} else {
                        %>
                        <td>Out of Stock</td>
                        <td>
                            <input type="checkbox" name="cartItems" value="<%= food.getName()%>" disabled="true" />
                        </td>
                        <% }

                        %>
                    </tr>
                    <% } %>
                </table>
                <% } else { %>
                <p>No food items to display.</p>
                <% }%>
                <input type="submit" value="Add to Cart" />
            </form>
            <a href="index.html">
                <input type="submit" value="Return" >
            </a>
        </center>
    </body>
</
