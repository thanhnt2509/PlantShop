<%-- 
    Document   : viewCart
    Created on : Sep 11, 2022, 6:54:30 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
        <script>
            function warning(){
                return window.confirm("Delete all cart item? You sure?");
            }
        </script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.admin != null}">
                <c:set var="noti" value="That's not a page for admin" scope="request"/>
                <jsp:forward page="adminPage.jsp"/>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <header>
                            <c:import url="header.jsp"/>
                        </header>
                    </c:when>
                    <c:otherwise>
                        <header>
                            <c:import url="header_loginedUser.jsp"/>
                        </header>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.noti != null}">
                        <a href="mainController?txtsearch=&searchby=byname&action=search">Back to Shop</a>
                        <h3 style="color: greenyellow">${requestScope.noti}</h3>
                    </c:when>
                    <c:when test="${sessionScope.cart.isEmpty() || sessionScope.cart == null}">
                        <a href="mainController?txtsearch=&searchby=byname&action=search">Back to Shop</a>
                        <h3>Your cart is empty</h3>
                    </c:when>
                    <c:otherwise>
                        <a href="mainController?txtsearch=&searchby=byname&action=search">Back to Shop</a>
                        <table border="1" style="width: auto">
                            <thead style="color: white; background: #006666;">
                                <tr>
                                    <th>Product ID</th>
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cartitem" items="${sessionScope.cart}">
                                    <c:set var="cartKey" value="${cartitem.key}" scope="page"/>
                                    <tr>
                                        <td><a href="mainController?action=getPlant&pid=${cartitem.key}">${cartitem.key}</a></td>
                                        <td style="width: 70%"><img src='${requestScope.imgList.get(cartKey)}' class="product"/></td>
                                        <td>${requestScope.priceList.get(cartKey)}</td>
                                        <td>
                                            <form action="mainController" method="get">
                                                <input type="number" name="quantity" min="1" value="${cartitem.value}">
                                                <input type="hidden" name="txtpid" value="${cartitem.key}">
                                                <input type="submit" name="action" value="update">
                                            </form>
                                        </td>
                                        <td>
                                            <form action="mainController" method="get">
                                                <input type="hidden" name="txtpid" value="${cartitem.key}">
                                                <input type="submit" name="action" value="delete">
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <h3>Total money: ${requestScope.totalMoney}$</h3>
                        <c:set var="timenow" value="<%= new java.util.Date()%>"/>
                        <h3>Order date: <fmt:formatDate pattern="yyyy-MM-dd" value="${timenow}"/></h3>
                        <h3>Ship date: N/A</h3>
                        <form action="mainController" method="get">
                            <input type="submit" value="saveOrder" name="action">
                        </form><br/>
                        <form action="mainController" method="get" onsubmit="return warning()">
                            <input type="submit" value="deleteAllCart" name="action">
                        </form>
                        <h3 style="color: red">${requestScope.warning}</h3>
                    </c:otherwise>
                </c:choose>
                <footer>
                    <c:import url="footer.jsp"/>
                </footer>
            </c:otherwise>
        </c:choose>
    </body>
</html>
