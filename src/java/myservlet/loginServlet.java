/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import dto.Account;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Legion
 */
public class loginServlet extends HttpServlet {

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
            String email = request.getParameter("txtemail");
            String password = request.getParameter("txtpassword");
            String save = request.getParameter("staylogin");
            Account acc = null;
            String randomToken = String.valueOf(Math.random());
            try {
                acc = AccountDAO.getAccount(email, password);
                if (acc != null) {
                    if (acc.getStatus() == 1) {
                        HttpSession session = request.getSession();
                        if (acc.getRole() == 1) {
                            session.setAttribute("admin", acc);
                            if (save != null) {
                                String token = randomToken;
                                AccountDAO.updateToken(token, email);
                                Cookie cookie = new Cookie("selector", token);
                                cookie.setMaxAge(-1);
                                response.addCookie(cookie);
                            }
                            request.getRequestDispatcher("adminPage.jsp").forward(request, response);
                        } else {
                            session.setAttribute("user", acc);
                            if (save != null) {
                                String token = randomToken;
                                AccountDAO.updateToken(token, email);
                                Cookie cookie = new Cookie("selector", token);
                                cookie.setMaxAge(60 * 2);
                                response.addCookie(cookie);
                            }
                            request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("warning", "Blocked account, contact admin for detail");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("warning", "Incorrect email or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
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
