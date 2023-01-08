<%-- 
    Document   : manageOrders
    Created on : Aug 26, 2022, 8:50:08 PM
    Author     : Legion
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Order Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.admin == null}">
                <c:set var="warning" value="You need to log in as Admin to access" scope="request"/>
                <jsp:forward page="login.jsp"/>
            </c:when>
            <c:otherwise>
                <header>
                    <c:import url="header_loginedAdmin.jsp"/>
                </header>
                <form action="mainController" method="post">
                    <input type="date" name="txtsearch">
                    <input type="submit" value="searchOrder" name="action">
                </form><br/>
                <form action="mainController" method="post">
                    <input type="number" min="1" name="txtsearch" value="${param.txtsearch}">
                    <input type="submit" value="searchByCustomerID" name="action">
                </form><br/>
                <form action="mainController" method="post">
                    <select name="orderstatus">
                        <option value="1" ${param.orderstatus == '1'? 'selected':''}>Processing</option>
                        <option value="2" ${param.orderstatus == '2'? 'selected':''}>Completed</option>
                        <option value="3" ${param.orderstatus == '3'? 'selected':''}>Canceled</option>
                    </select>
                    <input type="submit" value="searchByStatus" name="action">
                </form><br/>
                <c:choose>
                    <c:when test="${requestScope.orderList.isEmpty()}">
                        <h3>No result</h3>
                    </c:when>
                    <c:otherwise>
                        <table border="1" style="text-align: center; width: 100%">
                            <thead style="background: #006666; color: white">
                                <tr>
                                    <th>Order ID</th>
                                    <th>Order Date</th>
                                    <th>Ship Date</th>
                                    <th>Status</th>
                                    <th>Account ID</th>
                                    <th>Action</th>
                                </tr>  
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${requestScope.orderList}">
                                    <tr>
                                        <td>${order.orderID}</td>
                                        <td>${order.orderDate}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${order.shipDate == null}">N/A</c:when>
                                                <c:otherwise>${order.shipDate}</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><c:choose>
                                                <c:when test="${order.status eq 1}">Processing Order</c:when>
                                                <c:when test="${order.status eq 2}">Completed Order</c:when>
                                                <c:when test="${order.status eq 3}">Cancelled Order</c:when>
                                            </c:choose></td>
                                        <td>${order.accID}</td>
                                        <td>
                                            <c:if test="${order.status eq 1}">
                                                <a href="mainController?action=completeOrder&orderid=${order.orderID}">Complete Order</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                <footer>
                    <c:import url="footer.jsp"/>
                </footer>
            </c:otherwise>
        </c:choose>
    </body>
</html>
