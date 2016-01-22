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
import objects.Customer;
import objects.Menu;
import objects.Restaurant;

/**
 * Used by customer entity to update personal information in the database. 
 * Also used to for most other actions regarding customer entity.
 * @author Aziz
 */
@WebServlet(name = "UpdateCustomerServlet", urlPatterns = {"/UpdateCustomerServlet"})
public class UpdateCustomerServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateCustomerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCustomerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Handles account password and information updates by sending them to the doPost method. Also handles deleting account from database.
     * Handles searching for particular cuisine style and selection of a restaurant by the customer.
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
            response.sendRedirect("customerLogin.jsp?message="+message);
        }
        else{
            Customer c = (Customer) session.getAttribute("Customer");
            String action = request.getParameter("action");
            String username = c.getUsername();
            String enter;
            if(action.equalsIgnoreCase("info")){
                enter = "0";
                RequestDispatcher view = request.getRequestDispatcher("/updateCustomer.jsp?enter="+enter);
                view.forward(request, response);
            }
            else if(action.equalsIgnoreCase("pwd")){
                enter = "1";
                RequestDispatcher view = request.getRequestDispatcher("/updateCustomer.jsp?enter="+enter);
                view.forward(request, response);
            }
            else if(action.equalsIgnoreCase("logout")){
                session.invalidate();
                response.sendRedirect("index.html");
            }
            else if(action.equalsIgnoreCase("delete")){
                c.deleteCustomer(username);
                session.invalidate();
                response.sendRedirect("index.html");
            }
            else if (action.equalsIgnoreCase("search")){
                Restaurant r = new Restaurant();
                String search = request.getParameter("search");
                if(search.equalsIgnoreCase("")){
                    response.sendRedirect("customerPortal.jsp");
                    return;
                }
                session.setAttribute("keyword", search);
                search = search.substring(0, 1).toUpperCase() + search.substring(1);
                ArrayList<Restaurant> rests = (ArrayList<Restaurant>) r.getByCStyle(search);
                request.setAttribute("Restaurants", rests);
                RequestDispatcher view = request.getRequestDispatcher("/customerPortal.jsp");
                view.forward(request, response);
            }
            else if (action.equalsIgnoreCase("order")){
                Menu m = new Menu();
                ArrayList<Menu> menus = (ArrayList<Menu>) m.getByRid(Integer.parseInt(request.getParameter("rid")));
                request.setAttribute("Menus", menus);
                RequestDispatcher view = request.getRequestDispatcher("/customerPortal.jsp");
                view.forward(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Shows the current user information and allows update.
     * Also used for changing password.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession session = request.getSession();
        if (session == null){
            String message = "You have been logged out!!";
            response.sendRedirect("customerLogin.jsp?message="+message);
        }
        else{
            Customer c = (Customer) session.getAttribute("Customer");
            String username = c.getUsername();
            String action = request.getParameter("action");
            String enter;
            boolean valid;
            if(action.equalsIgnoreCase("info")){
                String fName, lName, address, phone, email;
                fName = request.getParameter("fname");
                lName = request.getParameter("lname");
                address = request.getParameter("address");
                phone = request.getParameter("phone");
                email = request.getParameter("email");
                c.setfName(fName);
                c.setlName(lName);
                c.setEmail(email);
                c.setPhone(phone);
                c.setUsername(username);
                c.setAddress(address);
                c.updateCustomer(c);
                enter = "0";
                response.sendRedirect("customerPortal.jsp?enter="+enter);
            }
            else if(action.equalsIgnoreCase("pwd")){
                String oldPassword = request.getParameter("oldpwd");
                String password = request.getParameter("pwd");
                enter = "1";
                valid = c.changePassword(username, oldPassword, password);
                if(valid){
                    response.sendRedirect("customerPortal.jsp?&enter="+enter);
                }
                else{
                    String message = "Invalid Current Password";
                    response.sendRedirect("updateCustomer.jsp?message="+message+"&enter="+enter);
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
