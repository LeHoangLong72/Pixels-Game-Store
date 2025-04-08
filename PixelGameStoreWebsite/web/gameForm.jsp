<%-- 
    Document   : gameForm
    Created on : Apr 8, 2025, 6:21:20 PM
    Author     : LAM
--%>

<%@page import="dto.GameDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            GameDTO game = new GameDTO();
            try {
                game = (GameDTO) request.getAttribute("game");
            } catch (Exception e) {
            }
            if (game == null) {
                game = new GameDTO();
            }

            String gameID_error = request.getAttribute("gameID_error") + "";
            gameID_error = gameID_error.equals("null") ? "" : gameID_error;
        %>


        <form action="MainController" method="post">
            <input type="hidden" name="action" value="add"/>
            Game ID: <input type="text" name="txtGameID" value="<%=game.getGameID()%>"/><br/>
            Game Name: <input type="text" name="txtGameName" value="<%=game.getGameName()%>"/><br/>
            Developer: <input type="text" name="txtDeveloper" value="<%=game.getDeveloper()%>"/><br/>
            Genre: <input type="text" name="txtGenre" value="<%=game.getGenre()%>"/><br/>
            Price: <input type="text" name="txtPrice" value="<%=game.getPrice()%>"/><br/>
            <input type="submit" value="Save"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
