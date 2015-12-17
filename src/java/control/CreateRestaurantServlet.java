/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Customer;
import objects.Restaurant;

/**
 *
 * @author Aziz
 */
@WebServlet(name = "CreateRestaurantServlet", urlPatterns = {"/CreateRestaurantServlet"})
public class CreateRestaurantServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateRestaurantServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateRestaurantServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        Restaurant r = new Restaurant();
        int rid = Integer.parseInt(request.getParameter("acct"));
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String rName = request.getParameter("name");
        String phone = request.getParameter("phone");
        double dMin = Double.parseDouble(request.getParameter("dmin"));
        String sName = request.getParameter("cstyle");
        sName = sName.substring(0,1).toUpperCase() + sName.substring(1);
        boolean valid = r.validStyle(sName);   
        if(valid){
            r.createRestaurant(rid, password, address, rName, phone, dMin, sName);
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(150);
            r = r.getByRID(rid).get(0);
            session.setAttribute("Restaurant", r);
            response.sendRedirect("restaurantPortal.jsp");
        }
        else{
            String message = "Invalid Cuisine Style";
            response.sendRedirect("createRestaurant.jsp?message="+message+"&rid=" + rid);
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

}
