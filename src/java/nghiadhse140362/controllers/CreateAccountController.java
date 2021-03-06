/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nghiadhse140362.daos.UsersDAO;
import nghiadhse140362.utils.Constants;
import nghiadhse140362.utils.EncodingHelpers;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "CreateAccountController", urlPatterns = {"/CreateAccountController"})
public class CreateAccountController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateAccountController.class);
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
        boolean success = false;
        try{
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String name = request.getParameter("txtName");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            String encPassword = EncodingHelpers.toHexString(password);
            UsersDAO dao = new UsersDAO();
            success=dao.createUser(email, encPassword, name, address, phone);
            
        } catch (SQLException |NoSuchAlgorithmException| NamingException ex) {
            if(ex instanceof SQLException && ex.getMessage().contains("duplicate")){
                request.setAttribute("EXISTED", "EXISTED");
            }
            LOGGER.error(ex.getMessage());
        }finally{
            if(success)response.sendRedirect(Constants.LOGIN_PAGE);
            else request.getRequestDispatcher(Constants.LOGIN_PAGE).forward(request, response);
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
        response.sendRedirect(Constants.CREATE_ACCOUNT_PAGE);
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
