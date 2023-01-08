<%-- 
    Document   : login
    Created on : Aug 1, 2022, 5:20:48 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
        <style>
            .parent {
                margin: 1rem;
                padding: 2rem 2rem;
                text-align: left;
            }
            .parent:first-child {
                vertical-align: top;
            } 
            .child {
                display: inline-block;
                padding: 1rem 1rem;
                vertical-align: middle;
            }
            .parent:not(:first-child) .relative {
                position: relative;
            }
            .parent:not(:first-child) .absolute {
                position: absolute;
                left: 29%;
                bottom: 84%;
            }
            .parent:not(:first-child) .relative #form-img {
                width: 45%;
            }
            .parent:not(:first-child) .absolute form{
                width: 50px;
            }
            .parent:not(:first-child) .relative #login-img {
                max-width: 75%;
            }
            .parent:not(:first-child) .absolute #login-x {
                max-width: 50%;
            }
        </style>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <div class="parent">
            <div class="child">
                <form action="mainController" method="post">
                    <table>
                        <tr><td>Email</td>
                            <td><input type="text" name="txtemail" value="${param.txtemail}" required=""></td></tr>
                        <tr><td>Password</td>
                            <td><input type="password" name="txtpassword" value="${param.txtpassword}" required=""></td></tr>
                    </table>
                    <input type="checkbox" value="staylogin" name="staylogin">Remember me<br/>
                    <input type="submit" value="login" name="action">
                </form>
            </div>
            <c:if test="${cookie.selector != null && not empty cookie.selector}">
                <div class="child">
                    <div class="relative">
                        <form action="mainController" method="post" id="form-img">
                            <input type="hidden" value="quickLogin" name="action">
                            <input type="image" src="images/user-img.png" alt="submit" id="login-img">
                        </form>
                        <div class="absolute">
                            <form action="mainController" method="post">
                                <input type="hidden" value="clearCookie" name="action">
                                <input type="image" src="images/button-x.png" alt="submit" id="login-x">
                            </form>
                        </div>
                    </div>
                </div>
                
            </c:if>
        </div>

        <h3>Note: Use Remember me to Have a better experience</h3>
        <h3 style="color: red">${requestScope.warning}</h3>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
