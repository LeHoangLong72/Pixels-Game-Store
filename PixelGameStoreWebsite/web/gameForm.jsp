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

            String gameName_error = request.getAttribute("gameName_error") + "";
            gameName_error = gameName_error.equals("null") ? "" : gameName_error;
            
            String developer_error = request.getAttribute("developer_error") + "";
            developer_error = developer_error.equals("null") ? "" : developer_error;
            
            String genre_error = request.getAttribute("genre_error") + "";
            genre_error = genre_error.equals("null") ? "" : genre_error;
            
            String price_error = request.getAttribute("price_error") + "";
            price_error = price_error.equals("null") ? "" : price_error;
        %>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="add"/>
            Game ID: <input type="text" name="txtGameID" value="<%=game.getGameID()%>"/><br/>
            <span style="color: red"><%=gameID_error%></span><br/>
            Game Name: <input type="text" name="txtGameName" value="<%=game.getGameName()%>"/><br/>
            <span style="color: red"><%=gameName_error%></span><br/>
            Developer: <input type="text" name="txtDeveloper" value="<%=game.getDeveloper()%>"/><br/>
            <span style="color: red"><%=developer_error%></span><br/>
            Genre: <input type="text" name="txtGenre" value="<%=game.getGenre()%>"/><br/>
            <span style="color: red"><%=genre_error%></span><br/>
            Price: <input type="number" name="txtPrice" value="<%=game.getPrice()%>"/><br/>
            <span style="color: red"><%=price_error%></span><br/>
            <input type="submit" value="Save"/> 
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
