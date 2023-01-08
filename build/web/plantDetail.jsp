<%-- 
    Document   : plantDetail
    Created on : Jun 18, 2022, 6:59:46 PM
    Author     : Admin
--%>

<%@page import="dto.Plant"%>
<%@page import="dao.PlantDAO"%>
<%@page import="dao.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Detail Page</title>
    </head>
    <body>
            <%
                String plantid=request.getParameter("plantid");
                if(plantid!=null){
                    int plantID=Integer.parseInt(plantid.trim());
                    Plant p=PlantDAO.getPlant(plantID);
            %>
            <table>
                <tr>
                    <td>Plant ID</td>
                    <td>Plant Name</td>
                    <td>Price</td>
                    <td>Image</td>
                    <td>Description</td>
                    <td>Status</td>
                    <td>Category ID</td>
                    <td>Category Name</td>
                </tr>
                <tr>
                    <td><%=p.getId()%></td>
                    <td><%=p.getName()%></td>
                    <td><%=p.getPrice()%></td>
                    <td><img src="<%=p.getImgpath()%>"></td>
                    <td><%=p.getDescription()%></td>
                    <%
                        if(p.getStatus()==1){
                    %>
                    <td>Is Available</td>
                    <%
                        }else{
                    %>
                    <td>Is empty</td>
                    <%
                        }
                    %>
                    <td><%=p.getCateid()%></td>
                    <td><%=p.getCatename()%></td>
                </tr>
            </table>
        <%
            }else{
        %>
        <p>Invalid Plant ID</p>
        <%
            }
        %>
        <a href="viewCart.jsp">Go to Cart</a>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
