/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend_business;

import Frontend_Helper.FoodXML;
import Frontend_Helper.GetOrderXML;
import Frontend_persistance.User_CRUD;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "AuthenticateUser", urlPatterns = {"/AuthenticateUser"})
public class AuthenticateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthenticateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenticateUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        Business business = new Business();

        AuthenticationKey authenticationKey = new AuthenticationKey();
        String token = authenticationKey.getToken(request).getKey();
        Cookie newCookie = authenticationKey.getCookie(username, token);

        if (business.isAdmin(username, password)) {
            response.addCookie(newCookie);
            GetOrderXML ordersxml;
            ordersxml = retreiveOrdersFromBackend(token);
            request.setAttribute("listoforders", ordersxml);
            RequestDispatcher rd = request.getRequestDispatcher("adminForm.jsp");
            rd.forward(request, response);
        } else if (business.isAuthenticated(username, password)) {
            response.addCookie(newCookie);

            String query = request.getParameter("query");

            request.getSession().setAttribute("uname", username);
            FoodXML result;
            result = retreiveServicesFromBackend(token);
            request.setAttribute("listoffood", result);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("menu.jsp");
            requestDispatcher.forward(request, response);
        } else {
            FoodXML result;
            result = retreiveServicesFromBackend(token);
            request.setAttribute("listoffood", result);
            RequestDispatcher rd = request.getRequestDispatcher("menuWithoutLogin.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private FoodXML retreiveServicesFromBackend(String token) {
        try {
            return (Business.getServices(token));
        } catch (IOException ex) {
            Logger.getLogger(AuthenticateUser.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

    private GetOrderXML retreiveOrdersFromBackend(String token) {
        try {
            return (Business.getOrder(token));
        } catch (IOException ex) {
            Logger.getLogger(AuthenticateUser.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
}
