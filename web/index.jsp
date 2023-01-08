<%-- 
    Document   : index
    Created on : Aug 1, 2022, 5:18:35 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
        <link rel="stylesheet" href="./mycss.css" type="text/css">
    </head>
    <body>
        <header>
            <c:import url="header.jsp"/>
        </header>
        <c:choose>
            <c:when test="${requestScope.searchlist != null || requestScope.seachlist.size() > 0}">
                <table border="1" style="width: auto">
                    <thead style="color: white; background: #006666;">
                        <tr>
                            <td></td>
                            <td>Product ID</td>
                            <td>Name</td>
                            <td>Status</td>
                            <td>Category</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="list" items="${requestScope.searchlist}">
                            <tr>
                                <td style="width: 70%"><img src='${list.imgpath}' class="product"/></td>
                                <td>${list.id}</td>
                                <td>${list.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${list.status eq 1}">Available</c:when>
                                        <c:otherwise>Unavailable</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${list.catename}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${list.status eq 1}">
                                            <a href="mainController?action=addtocart&pid=${list.id}">Add to Cart</a>
                                        </c:when>
                                        <c:otherwise><p style="color: red; font-size: 1.5em">Out of stock</p></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <img src="images/background.jpg" style="width: 100%; margin: 1% 0;">
            </c:otherwise>
        </c:choose>
        <footer>
            <c:import url="footer.jsp"/>
        </footer>
    </body>
</html>
