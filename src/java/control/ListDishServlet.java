/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
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
import objects.Submenu;

/**
 *
 * @author Aziz
 */
@WebServlet(name = "ListDishServlet", urlPatterns = {"/ListDishServlet"})
public class ListDishServlet extends HttpServlet {

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
            out.println("<title>Servlet ListDishServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListDishServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        int rid = Integer.parseInt(request.getParameter("rid"));
        String mtype = request.getParameter("mtype");
        HttpSession session = request.getSession(false);
        Submenu s = new Submenu();
        ArrayList<Submenu> sm = (ArrayList<Submenu>) s.getByRidMtype(rid, mtype);
        ArrayList<ArrayList<Dish>> dishsub = new ArrayList<ArrayList<Dish>>();
        Dish d = new Dish();
        for(Submenu k: sm){
            ArrayList<Dish> dishes = (ArrayList<Dish>) d.getDish(k.getSmcategory(), mtype, rid);
            dishsub.add(dishes);
        }
        request.setAttribute("Submenu", sm);
        request.setAttribute("Dishes", dishsub);
        Restaurant r = new Restaurant();
        RequestDispatcher view = request.getRequestDispatcher("/customerPortal.jsp?");
        view.forward(request, response);
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
        processRequest(request, response);
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
