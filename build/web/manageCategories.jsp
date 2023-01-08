<%-- 
    Document   : manageCategories
    Created on : Sep 28, 2022, 7:20:28 PM
    Author     : Legion
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Category Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
        <script>
            
        </script>
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
                <h3 style="color: greenyellow">${requestScope.noti}</h3>
                <h3 style="color: red">${requestScope.warning}</h3>
                <form action="mainController" method="get">
                    <input type="text" name="txtsearch" placeholder="Input Category Name" value="${param.txtsearch}">
                    <input type="submit" value="searchCategory" name="action">
                </form><br/>
                <form action="mainController" method="get">
                    <input type="text" name="catename" required="" placeholder="Input new cate name"/>
                    <input type="submit" value="addNewCate" name="action"/>
                </form><br/>
                <c:choose>
                    <c:when test="${requestScope.categoryList.isEmpty()}">
                        <h3>No result</h3>
                    </c:when>
                    <c:otherwise>
                        <table border="1" style="text-align: center; width: 100%">
                            <thead style="background: #006666; color: white">
                                <tr>
                                    <th>Category ID</th>
                                    <th>Category Name</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cate" items="${requestScope.categoryList}">
                                    <tr>
                                        <td>${cate.cateID}</td>
                                        <td>${cate.cateName}</td>
                                        <td><a href="mainController?action=editCatePage&cid=${cate.cateID}">Edit name</a></td>
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
