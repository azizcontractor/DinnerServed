/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Dish;
import objects.Restaurant;
import utils.OracleConnection;

/**
 * This servlet is used by the restaurant entity t add dishes to its menu.
 * @author Aziz
 */
@WebServlet(name = "AddDishServlet", urlPatterns = {"/AddDishServlet"})
public class AddDishServlet extends HttpServlet {

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
            out.println("<title>Servlet AddDishServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDishServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Takes in the restaurant from the session. Generates a new dish with a new dish ID.
     * Displays current dishes on the submenu as well and allows editing.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Dish d = new Dish();
        int did = d.genID();
        HttpSession session = request.getSession(false);
        Restaurant r = (Restaurant) session.getAttribute("Restaurant");
        int rid = Integer.parseInt(r.getrID());
        ArrayList<Dish> dishes = (ArrayList<Dish>) d.getDish(request.getParameter("smcategory"), request.getParameter("mtype"), rid);
        request.setAttribute("Dishes", dishes);
        RequestDispatcher view = request.getRequestDispatcher("/addDish.jsp?did="+did);
        view.forward(request, response);
        //processRequest(request, response);
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
        String action = request.getParameter("action");
        Dish d = new Dish();
        if(action == null){
            HttpSession session = request.getSession(false);
            Restaurant r = (Restaurant) session.getAttribute("Restaurant");
            int rid = Integer.parseInt(r.getrID());
            int did = Integer.parseInt(request.getParameter("did"));
            String mtype = request.getParameter("mtype");
            String smcat = request.getParameter("smcat");
            String dname = request.getParameter("dname");    
            double price = Double.parseDouble(request.getParameter("price"));
            String desc = request.getParameter("desc");
            d.addDish(did, dname, price, desc, mtype, smcat, rid);
        }
        else if (action.equalsIgnoreCase("update")){
            int did = Integer.parseInt(request.getParameter("did"));
            String dname = request.getParameter("dname");
            String desc = request.getParameter("desc");
            double price = Double.parseDouble(request.getParameter("price"));
            d.updateDish(did, price, dname, desc);
        }
        else if (action.equalsIgnoreCase("delete")){
            int did = Integer.parseInt(request.getParameter("did"));
            d.deleteDish(did);
        }
        response.sendRedirect("ListMenuServlet");
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
