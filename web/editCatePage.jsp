<%-- 
    Document   : editCatePage
    Created on : Nov 3, 2022, 2:28:54 AM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit category name Page</title>
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
                <a href="mainController?action=manageCategories">Back to Manage Category Page</a>
                <form action="mainController" method="get">
                    <table>
                        <tr>
                            <th>Category ID: ${param.cid}
                                <input type="hidden" name="cid" value="${param.cid}"/>
                            </th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>Category new name</th>
                            <td><input type="text" name="catename" value="${param.catename}" placeholder="Input new category name"></td>
                        </tr>
                        <tr><td><input type="submit" value="editCate" name="action"></td></tr>
                    </table>
                </form>
                <footer>
                    <c:import url="footer.jsp"/>
                </footer>
            </c:otherwise>
        </c:choose>
    </body>
</html>
