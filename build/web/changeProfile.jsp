<%-- 
    Document   : changeProfile
    Created on : Aug 5, 2022, 12:02:40 PM
    Author     : Legion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Profile Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.admin != null}">
                <c:set var="noti" value="That's not a page for admin" scope="request"/>
                <jsp:forward page="adminPage.jsp"/>
            </c:when>
            <c:when test="${sessionScope.user == null}">
                <c:set var="warning" value="Need to login to continue" scope="request"/>
                <jsp:forward page="login.jsp"/>
            </c:when>
            <c:otherwise>
                <header>
                    <c:import url="header_loginedUser.jsp"/>
                </header>
                <a href="personalPage.jsp">Back to Personal Page</a>
                <h1>Change your Profile info here</h1>
                <form action="mainController" method="post">
                    <table>
                        <tr>
                            <td>New Full Name</td>
                            <td><input type="text" name="txtfullname" value="${param.txtfullname}"></td>
                        </tr>
                        <tr>
                            <td>New Phone Number</td>
                            <td><input type="text" name="txtphone" value="${param.txtphone}"></td>
                        </tr>
                        <tr>
                            <td>New Password</td>
                            <td><input type="password" name="txtpass" value="${param.txtpass}"></td>
                        </tr>
                        <tr><td><input type="submit" value="editProfile" name="action"></td></tr>
                    </table>
                </form>
                <h3 style="color: greenyellow">${requestScope.noti}</h3>
                <h3 style="color: red">${requestScope.error}</h3>
                <footer>
                    <c:import url="footer.jsp"/>
                </footer>
            </c:otherwise>
        </c:choose>
    </body>
</html>
