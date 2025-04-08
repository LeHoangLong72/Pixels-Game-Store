<%-- 
    Document   : gameForm
    Created on : Apr 8, 2025, 6:21:20 PM
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
            <input type="hidden" name="action" value="add"/>
            Game ID: <input type="text" name="txtGameID"/><br/>
            Game Name: <input type="text" name="txtGameName"/><br/>
            Developer: <input type="text" name="txtDeveloper"/><br/>
            Genre: <input type="text" name="txtGenre"/><br/>
            Price: <input type="text" name="txtPrice"/><br/>
            <input type="submit" value="Save"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
