/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Restaurant;

/**
 *
 * @author Aziz
 */
@WebServlet(name = "UpdateRestaurantServlet", urlPatterns = {"/UpdateRestaurantServlet"})
public class UpdateRestaurantServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateRestaurantServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateRestaurantServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        if (session == null){
            String message = "You have been logged out!!";
            response.sendRedirect("restaurantLogin.jsp?message="+message);
        }
        else{
            Restaurant r = (Restaurant) session.getAttribute("Restaurant");
            String action = request.getParameter("action");
            int rid = Integer.parseInt(r.getrID());
            String enter;
            if (action.equalsIgnoreCase("info")){
                enter = "0";
                RequestDispatcher view = request.getRequestDispatcher("/updateRestaurant.jsp?enter="+enter);
                view.forward(request, response);
            }
            else if(action.equalsIgnoreCase("pwd")){
                enter = "1";
                RequestDispatcher view = request.getRequestDispatcher("/updateRestaurant.jsp?enter="+enter);
                view.forward(request, response);
            }
            else if(action.equalsIgnoreCase("delete")){
                r.deleteRestaurant(rid);
                session.invalidate();
                response.sendRedirect("index.html");
            }
            else if(action.equalsIgnoreCase("logout")){
                session.invalidate();
                response.sendRedirect("index.html");
            }
        }
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
        HttpSession session = request.getSession(true);
        if (session == null){
            String message = "You have been logged out!!";
            response.sendRedirect("customerLogin.jsp?message="+message);
        }
        else{
            Restaurant r = (Restaurant) session.getAttribute("Restaurant");
            String action = request.getParameter("action");
            int rid = Integer.parseInt(r.getrID());
            String enter;
            if (action.equalsIgnoreCase("info")){
                r.setrName(request.getParameter("rname"));
                r.setAddress(request.getParameter("address"));
                r.setPhone(request.getParameter("phone"));
                r.setdMin(Double.parseDouble(request.getParameter("dmin")));
                r.setrID(request.getParameter("rid"));
                r.updateRestaurant(r);
                enter = "0";
                response.sendRedirect("restaurantPortal.jsp?rid="+rid+"&enter="+enter);
            }
            if (action.equalsIgnoreCase("pwd")){
                enter = "1";
                boolean valid;
                String oldPassword = request.getParameter("oldpwd");
                String password = request.getParameter("pwd");
                valid = r.changePwd(rid, oldPassword, password);
                if(valid){
                    response.sendRedirect("restaurantPortal.jsp?&enter="+enter);
                }
                else{
                    String message = "Invalid Current Password";
                    response.sendRedirect("updateRestaurant.jsp?message="+message+"&enter="+enter);
                }
            }
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
