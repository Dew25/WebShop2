/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import entity.History;
import entity.Product;
import entity.User;
import exeptions.NotMoneyException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CustomerFacade;
import session.HistoryFacade;
import session.ProductFacade;
import session.UserFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "IndexController",loadOnStartup = 1, urlPatterns = {"/","/checkLogin","/doBuy","/logout","/selectProduct","/buyProduct"})
public class IndexController extends HttpServlet {
@EJB ProductFacade productFacade;
@EJB CustomerFacade customerFacade;
@EJB HistoryFacade historyFacade;
@EJB UserFacade userFacade;



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
        String path = request.getServletPath();
        HttpSession session = request.getSession(false);
        
        if("/".equals(path)){
            request.setAttribute("user", false);
            List<Product> products = productFacade.findAll();
            User regUser = null;
            if(session != null){
            regUser = (User) session.getAttribute("regUser");
                if(regUser != null){
                    request.setAttribute("user", true);
                }
            }
            request.setAttribute("products", products);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        }else if("/proba".equals(path)){
            request.setAttribute("end", 5);
            request.getRequestDispatcher("proba.jsp").forward(request, response);
        }else if("/checkLogin".equals(path)){
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User user=null;
            try {
                user = userFacade.findByLoginPassword(login,password);
            } catch (LoginException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(user != null){
                session.setAttribute("regUser", user);
                request.getRequestDispatcher("/").forward(request, response);
            }else{
                request.getRequestDispatcher("/logout").forward(request, response);
            }
            
        }else if("/doBuy".equals(path)){
            User regUser=null;
            if(session != null){
                regUser = (User) session.getAttribute("regUser");
            }
                if(regUser == null){
                    request.getRequestDispatcher("loginForm.jsp").forward(request, response);
                }else{
                    request.setAttribute("user", true);
                    request.getRequestDispatcher("/").forward(request, response);
                }
            
        }else if("/logout".equals(path)){
            if(session != null){
                session.invalidate();
            }
            request.getRequestDispatcher("/").forward(request, response);
            
        }else if("/selectProduct".equals(path)){
            String productId = (String) request.getParameter("id");
            if(productId != null && session != null){
                Product product = productFacade.find(new Long(productId));
                request.setAttribute("product", product);
                
                request.getRequestDispatcher("/product.jsp").forward(request, response);
            }else{
               request.getRequestDispatcher("/loginForm.jsp").forward(request, response); 
            }
            
        }else if("/buyProduct".equals(path)){
            String product_id = (String) request.getParameter("product_id");
            String quantityProduct = (String) request.getParameter("quantityProduct");
            User user = null;
            if(session != null){
                user=(User) session.getAttribute("regUser");
            }
            if(product_id != null && quantityProduct != null && user != null){
                Product product = productFacade.find(new Long(product_id));
                Integer num = new Integer(quantityProduct);
                if(product.getQuantity()- num < 0){
                    throw new ServletException("Not product");
                }
                product.setQuantity(product.getQuantity()- num);
                
                productFacade.edit(product);
                Customer customer = user.getCustomer();
                customerFacade.edit(customer);
                if(customer.getMoney()-product.getPrice()*num < product.getPrice()*num){
                    throw new ServletException("Not money");
                }
                customer.setMoney(customer.getMoney()-product.getPrice()*num);
                Calendar date = new GregorianCalendar();
                History history = new History(date.getTime(), customer, product, num);
                historyFacade.create(history);
                request.setAttribute("products", productFacade.findAll());
                request.getRequestDispatcher("/").forward(request, response);
            }else{
               request.getRequestDispatcher("/loginForm.jsp").forward(request, response); 
            }
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
