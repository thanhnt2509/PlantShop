<%-- 
    Document   : ManageAccounts
    Created on : Sep 26, 2022, 3:13:20 PM
    Author     : Legion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account Page</title>
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
                    <input type="text" name="txtsearch" placeholder="Input Email or Full Name" value="${param.txtsearch}">
                    <input type="submit" value="searchAccount" name="action">
                </form><br/>
                <c:choose>
                    <c:when test="${requestScope.accountList.isEmpty()}">
                        <h3>No result</h3>
                    </c:when>
                    <c:otherwise>
                        <table border="1" style="text-align: center; width: 100%">
                            <thead style="background: #006666; color: white">
                                <tr>
                                    <th>ID</th>
                                    <th>Email</th>
                                    <th>Full name</th>
                                    <th>Status</th>
                                    <th>Phone</th>
                                    <th>Role</th>
                                    <th>Action</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="acc" items="${requestScope.accountList}">
                                    <tr>
                                        <td>${acc.accID}</td>
                                        <td>${acc.email}</td>
                                        <td>${acc.fullname}</td>
                                        <td><c:choose>
                                                <c:when test="${acc.status eq 1}">Active</c:when>
                                                <c:otherwise>Inactive</c:otherwise>
                                            </c:choose></td>
                                        <td>${acc.phone}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acc.role eq 1}">Admin</c:when>
                                                <c:otherwise>User</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${acc.role eq 0}">
                                                <c:url var="banlink" value="mainController">
                                                    <c:param name="email" value="${acc.email}"></c:param>
                                                    <c:param name="status" value="${acc.status}"></c:param>
                                                    <c:param value="updateStatusAccount" name="action" ></c:param>
                                                </c:url>
                                                <c:choose>
                                                    <c:when test="${acc.status eq 1}"><a href="${banlink}">Block</a></c:when>
                                                    <c:otherwise><a href="${banlink}">Unblock</a></c:otherwise>
                                                </c:choose>
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
