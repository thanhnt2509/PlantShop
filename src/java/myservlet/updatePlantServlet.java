/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import dao.PlantDAO;
import dto.Plant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Legion
 */
public class updatePlantServlet extends HttpServlet {

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
            String pid = request.getParameter("pid");
            String pname = request.getParameter("txtname");
            String price = request.getParameter("txtprice");
            String imgpath = request.getParameter("imgpath");
            String description = request.getParameter("txtdes");
            String cateid = request.getParameter("cateid");
            if (pname == null || price == null || description == null
                    || pname.trim().equals("") || price.trim().equals("") || description.trim().equals("")){
                Plant p = PlantDAO.getPlant(Integer.parseInt(pid));
                if (pname == null || pname.trim().equals("")) pname = p.getName();
                if (price == null || price.trim().equals("")) price = String.valueOf(p.getPrice());
                if (description == null || description.trim().equals("")) description = p.getDescription();
            }
            boolean result = PlantDAO.updatePlant(pid, pname, price, imgpath, description, cateid);
            if (result){
                request.setAttribute("noti", "Update Plant successfully");
                request.getRequestDispatcher("mainController?action=managePlants").forward(request, response);
            }else{
                request.setAttribute("error", "Update plant failed");
                request.getRequestDispatcher("updatePlantPage.jsp").forward(request, response);
            }
        }catch(Exception e){
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
