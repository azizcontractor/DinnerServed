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
import objects.Cart;
import objects.Customer;
import objects.Dish;
import objects.DishOrd;
import objects.Order;

/**
 *
 * @author Aziz
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

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
            out.println("<title>Servlet CartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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
        String smcategory = request.getParameter("smcat");
        int did = Integer.parseInt(request.getParameter("did"));
        Dish di = new Dish();
        di = di.getByDID(did);
        DishOrd d = new DishOrd();
        d.fromDish(di);
        Cart c;
        HttpSession session = request.getSession(false);
        if(session.getAttribute("Cart") == null){
            c = new Cart();
            c.addDish(d);
            c.addTotal(d.getPrice());
            session.setAttribute("Cart", c);
        }
        else{
            c = (Cart) session.getAttribute("Cart");
            ArrayList<DishOrd> dishes = c.getDishes();
            boolean add = true;
            for(DishOrd k: dishes){
                if(k.getDid() == d.getDid()){
                    k.addQ();
                    c.addTotal(k.getPrice());
                    add = false;
                    break;
                }
            }
            if (add == true){
                c.addDish(d);
                c.addTotal(d.getPrice());
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("ListDishServlet?rid="+rid+"&mtype="+mtype);
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
        //processRequest(request, response);
        HttpSession session = request.getSession();
        String action = request.getParameter("remove");
        int did;
        String mtype;
        int rid;
        Cart c = (Cart) session.getAttribute("Cart");
        if(action.equalsIgnoreCase("remove")){
            did = Integer.parseInt(request.getParameter("did"));
            Dish d = new Dish();
            d = d.getByDID(did);
            mtype = d.getMtype();
            rid = d.getRid();
            for(int i = 0; i < c.getDishes().size(); i++){
                if(c.getDishes().get(i).getDid() == did){
                    c.removeTotal(c.getDishes().get(i).getPrice());
                    c.getDishes().remove(i);
                    break;
                }
            }
            if(c.getDishes().isEmpty()){
                session.setAttribute("Cart", null);
            }
            response.sendRedirect("ListDishServlet?rid="+rid+"&mtype="+mtype);
        }
        if (action.equalsIgnoreCase("checkout")){
            Order o = new Order();
            int oid = o.getID();
            Customer cus = (Customer) session.getAttribute("Customer");
            o.createOrd(oid, cus.getUsername(), 0.07, c.getTotal());
            for(DishOrd d: c.getDishes()){
                d.addToOrd(oid);
            }
            String enter = "3";
            session.setAttribute("Cart", null);
            response.sendRedirect("customerPortal.jsp?enter="+enter);
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
