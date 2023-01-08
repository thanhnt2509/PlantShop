/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

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
public class mainController extends HttpServlet {
    private String url="errorpage.html";

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
            String action=request.getParameter("action");
            
            if(action==null || action.equals("")) url="index.jsp";
            else if(action.equals("login")) url="loginServlet";
            else if(action.equals("register")) url="registerServlet";
            else if(action.equals("logout")) url="logoutServlet";
            
            else if(action.equals("editProfile")) url="changeProfileServlet";
            else if(action.equals("addtocart")) url="addToShoppingCartServlet";
            else if(action.equals("cancel")) url="cancelOrderServlet";
            else if(action.equals("reorder")) url="reOrderServlet";
            else if(action.equals("delete")) url="deleteItemCartServlet";
            else if(action.equals("update")) url="updateItemCartServlet";
            else if(action.equals("completeOrder")) url="completeOrderServlet";
            else if(action.equals("saveOrder")) url="saveShoppingCartServlet";
            else if(action.equals("viewCart")) url="viewCartServlet";
            
            else if(action.equals("manageAccounts")) url="manageAccountServlet";
            else if(action.equals("manageOrders")) url="manageOrderServlet";
            else if(action.equals("managePlants")) url="managePlantServlet";
            else if(action.equals("manageCategories")) url="manageCategoryServlet";
            else if(action.equals("updateStatusAccount")) url="updateStatusAccountServlet";
            else if(action.equals("searchAccount")) url="searchAccountServlet";
            else if(action.equals("searchOrder")) url="searchOrderServlet";
            else if(action.equals("searchPlant")) url="searchPlantServlet";
            else if(action.equals("searchCategory")) url="searchCategoryServlet";
            else if(action.equals("search")) url="searchServlet";
            else if(action.equals("clearCookie")) url="clearCookieServlet";
            else if(action.equals("orderDetail")) url="orderDetailServlet";
            else if(action.equals("getAllOrder")) url="getAllOrderServlet";
            else if(action.equals("getCompletedOrder")) url="getCompletedOrderServlet";
            else if(action.equals("getCanceledOrder")) url="getCanceledOrderServlet";
            else if(action.equals("getProcessingOrder")) url="getProcessingOrderServlet";
            else if(action.equals("editCatePage")) url="editCatePage.jsp";
            else if(action.equals("updatePlantStatus")) url="updatePlantStatusServlet";
            else if(action.equals("getPlant")) url="getPlantServlet";
            else if(action.equals("addNewCate")) url="addNewCateServlet";
            else if(action.equals("searchByCustomerID")) url="searchByCustomerIDServlet";
            else if(action.equals("editCate")) url="editCategoryServlet";
            else if(action.equals("addNewPlant")) url="addNewPlantServlet";
            else if(action.equals("deleteAllCart")) url="deleteAllCartServlet";
            else if(action.equals("updatePlant")) url="updatePlantServlet";
            else if(action.equals("updatePlantPage")) url="updatePlantPage.jsp";
            else if(action.equals("searchByStatus")) url="searchByStatusServlet";
            else if(action.equals("quickLogin")) url="quickLoginServlet";
            
            
            //redirect to jsp page
//            else if(action.equals("home")) url="index.jsp";
//            else if(action.equals("viewcart")) url="viewCart.jsp";
//            else if(action.equals("personalPage")) url="personalPage.jsp";
//            else if(action.equals("completedOrder")) url="completedOrder.jsp";
//            else if(action.equals("canceledOrder")) url="canceledOrder.jsp";
//            else if(action.equals("processingOrder")) url="processingOrder.jsp";
//            else if(action.equals("changeProfile")) url="changeProfile.jsp";
//            else if(action.equals("registration")) url="registration.jsp";
            
            request.getRequestDispatcher(url).forward(request, response);
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
