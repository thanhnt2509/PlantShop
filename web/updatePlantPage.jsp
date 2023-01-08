<%-- 
    Document   : updatePlantPage
    Created on : Nov 6, 2022, 11:48:44 AM
    Author     : Legion
--%>

<%@page import="dao.PlantDAO"%>
<%@page import="dto.Plant"%>
<%@page import="dto.Category"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Plant Page</title>
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
                <%
                    ArrayList<Category> catelist = CategoryDAO.getCategories();
                    ArrayList<String> imgList = PlantDAO.getImgPath();
                    request.setAttribute("imgList", imgList);
                    request.setAttribute("categoryList", catelist);
                %>
                <a href="mainController?action=managePlants">Back to Manage Plant Page</a>
                <form action="mainController" method="get">
                    <table>
                        <tr>
                            <th>Plant ID: ${param.pid}
                                <input type="hidden" name="pid" value="${param.pid}"/>
                            </th>
                            <td></td>
                        </tr>
                        <tr><th>Plant Name</th><td><input type="text" name="txtname" placeholder="Input new name"/></td></tr>
                        <tr><th>Price</th><td><input type="number" name="txtprice" placeholder="Input new price"/></td></tr>
                        <tr>
                            <th>Image</th>
                            <td>
                                <select name="imgpath">
                                    <c:forEach var="image" items="${requestScope.imgList}">
                                        <option value="${image}">${image}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr><th>Description</th><td><input type="text" name="txtdes" placeholder="Input new description"/></td></tr>
                        <tr>
                            <th>Category</th>
                            <td>
                                <select name="cateid">
                                    <c:forEach var="cate" items="${requestScope.categoryList}">
                                        <option value="${cate.cateID}">${cate.cateName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" name="action" value="updatePlant"/>
                </form>
                <h3 style="color: red">${requestScope.error}</h3>
                <footer>
                    <c:import url="footer.jsp"/>
                </footer>
            </c:otherwise>
        </c:choose>
    </body>
</html>
