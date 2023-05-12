<%@page import="Frontend_Helper.FoodItem"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
    </head>
    <body>
    <center>
        <h2>Your Order:</h2>
        <%
            ArrayList<FoodItem> foodlist = (ArrayList) request.getSession().getAttribute("listoffood");
            String[] cartItems = request.getParameterValues("cartItems");
            double totalPrice = 0.0;
            if (cartItems != null) {
                for (String itemName : cartItems) {
                    for (FoodItem food : foodlist) {
                        if (food.getName().equals(itemName)) {
                            out.print("<p>" + food.getName() + " - $" + food.getPrice() + "</p>");
                            totalPrice += food.getPrice();
                            break;
                        }
                    }
                }
            }
           String purchases = String.join(",", cartItems);
        %>
        <p>Total Price: $<%=totalPrice%></p>

        <form action="ConfirmationServlet" method="post">
            <input type="hidden" name="username" value="<%=session.getAttribute("uname")%>">
            <input type="hidden" name="totalPrice" value="<%=totalPrice%>">
            <input type="hidden" name="purchases" value="<%=purchases%>">
            <input type="submit" value="Confirm Order">
        </form>
    </center>
</body>
</html>
