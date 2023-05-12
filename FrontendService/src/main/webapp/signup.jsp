<%-- 
    Document   : signup
    Created on : Feb 9, 2023, 6:51:35 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTER</title>
    </head>
    <body>
        <form action="Register" method="post">
            <table border="2" align="center" cellpadding="5" cellspacing="5">
                <tr>

                    <td> Enter username :</td>
                    <td> <input type="text" name="registername" size="30"> </td>
                </tr>
                <tr>
                    <td> Enter password :</td>
                    <td> <input type="password" name="registerpassword" size="30"> </td>
                </tr>
                <tr>
                <tr>

                    <td> Enter email :</td>
                    <td> <input type="text" name="newEmail" size="30"> </td>
                </tr>
                <tr>

                    <td> Enter address :</td>
                    <td> <input type="text" name="newAddress" size="30"> </td>
                </tr>

                <td></td>
                <td>
                    <a href="index.html">
                        <input type="submit" value="create account" >
                    </a>
                </td>
                </tr>
            </table>
        </form>
    </body>
</html>
