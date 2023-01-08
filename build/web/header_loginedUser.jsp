<%-- 
    Document   : header_loginedUser
    Created on : Aug 1, 2022, 4:46:38 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="changeProfile.jsp">Change Profile</a></li>
                <li><a href="mainController?action=getCompletedOrder">Completed Orders</a></li>
                <li><a href="mainController?action=getCanceledOrder">Cancelled Orders</a></li>
                <li><a href="mainController?action=getProcessingOrder">Processing Orders</a></li>
                <li><a href="mainController?action=viewCart">View Cart</a></li>
                <li><a href="mainController?action=logout">Log Out</a></li>
                <li>
                    <form action="mainController" method="get">
                        <table>
                            <tr>
                                <td>Order date</td>
                                <td><input type="date" name="from"></td>
                            </tr>
                            <tr>
                                <td>Received date</td>
                                <td><input type="date" name="to"></td>
                            </tr>
                            <tr><td></td><td><input type="submit" value="getAllOrder" name="action"></td></tr>
                        </table>
                    </form>
                </li>
            </ul>
        </nav>
    </body>
</html>
