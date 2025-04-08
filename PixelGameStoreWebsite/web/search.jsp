<%-- 
    Document   : search
    Created on : Apr 1, 2025, 4:58:33 PM
    Author     : LAM
--%>

<%@page import="java.util.List"%>
<%@page import="dto.GameDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
        %>

        <h1>Welcome <%=user.getFullName()%> </h1>
        
        <% 
        String searchTerm = request.getAttribute("searchTerm") + ""; 
        searchTerm = searchTerm.equals("null") ? "" : searchTerm;
        %>

        <form action="MainController">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Logout"/>
        </form>

        <form action="MainController">
            <input type="hidden" name="action" value="search"/>
            Search: <input type="text" name="searchTerm" value="<%=searchTerm%>"/>
            <input type="submit" value="Search"/>
        </form>

            
            <a href="gameForm.jsp">
                Add
            </a>
        <%
            if (request.getAttribute("listGame") != null) {
                List<GameDTO> listGame = (List<GameDTO>) request.getAttribute("listGame");
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Developer</th>
                    <th>Genre</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                <%                    int i = 0;
                    for (GameDTO game : listGame) {
                        i++;
                %>
                <tr>
                    <td><%=i%></td>
                    <td><%=game.getGameID()%></td>
                    <td><%=game.getGameName()%></td>
                    <td><%=game.getDeveloper()%></td>
                    <td><%=game.getGenre()%></td>
                    <td><%=game.getPrice()%>$</td>
                    <td><a href="MainController?action=delete&id=<%=game.getGameID()%>&searchTerm=<%=searchTerm%>">
                            <img src="assets/img/delete.jpg" style="width: 20px; height: 20px"/>
                        </a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <% }%>
        <% } else {
        %>
        You do not have permission to access this content.
        <%
    }%>
    </body>
</html>
