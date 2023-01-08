<%-- 
    Document   : OrderDetail
    Created on : Sep 4, 2022, 9:05:48 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <c:set var="warning" value="Need to login to continue" scope="request"/>
                <jsp:forward page="login.jsp"/>
            </c:when>
            <c:otherwise>
                <header>
                    <c:import url="header_loginedUser.jsp"/>
                </header>
                <a href="mainController?from=&to=&action=getAllOrder">View Every Orders</a>
                <c:choose>
                    <c:when test="${requestScope.detailList == null}">
                        <h3>You don't have any order</h3>
                    </c:when>
                    <c:otherwise>
                        <table border="1" style="text-align: center; width: 100%">
                            <thead style="background: #006666; color: white">
                                <tr>
                                    <td>Order ID</td>
                                    <td>Plant ID</td>
                                    <td>Plant Name</td>
                                    <td>Image</td>
                                    <td>Price</td>
                                    <td>Quantity</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="detail" items="${requestScope.detailList}">
                                    <tr>
                                        <td>${detail.orderID}</td>
                                        <td>${detail.plantID}</td>
                                        <td>${detail.plantName}</td>
                                        <td><img src="${detail.imgPath}" style="width: 20%"/></td>
                                        <td>${detail.price}</td>
                                        <td>${detail.quantity}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <h3> Total money: ${requestScope.totalMoney}$</h3>
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
