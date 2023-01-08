/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Legion
 */
public class changeProfileServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("user");
            String email = acc.getEmail();
            String fullname = request.getParameter("txtfullname");
            String phone = request.getParameter("txtphone");
            String password = request.getParameter("txtpass");
            if (fullname == null || phone == null || password == null
                    || fullname.trim().equals("") || phone.trim().equals("") || password.trim().equals("")) {
                int accid = acc.getAccID();
                Account oldAcc = AccountDAO.getAccount(accid);
                if (fullname == null || fullname.trim().equals("")) fullname = oldAcc.getFullname();
                if (phone == null || phone.trim().equals("")) phone = oldAcc.getPhone();
                if (password == null || password.trim().equals("")) password = oldAcc.getPassword();
            }
            if (!phone.matches("[0-9]{6,10}")) {
                request.setAttribute("error", "Invalid phone number");
                request.getRequestDispatcher("changeProfile.jsp").forward(request, response);
            } else {
                AccountDAO.updateAccount(email, password, fullname, phone);
                request.setAttribute("noti", "Change successfully");
                request.getRequestDispatcher("changeProfile.jsp").forward(request, response);
            }
        } catch (Exception e) {
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
