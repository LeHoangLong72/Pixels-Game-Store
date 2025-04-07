<%-- 
    Document   : login
    Created on : Apr 1, 2025, 4:48:20 PM
    Author     : LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login"/>
            UserID <input type="text" name="txtUserID"/><br/>
            Password <input type="password" name="txtPassword"/><br/>
            <input type="submit" value="Login"/>
        </form>
        
        <%
            String message = request.getAttribute("message") + "";
            message = message.equals("null") ? "" : message;
        %>
        
        <span style="color: red"><%=message%></span>
    </body>
</html>
