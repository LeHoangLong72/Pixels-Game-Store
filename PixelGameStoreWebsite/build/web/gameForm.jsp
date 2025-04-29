<%-- 
    Document   : gameForm
    Created on : Apr 8, 2025, 6:21:20 PM
    Author     : LAM
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page import="dto.GameDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <c:set var="isAdmin" value="<%=AuthUtils.isAdmin(session)%>"/>
        
        <c:if test="${isAdmin}">
            <jsp:useBean id="game" class="dto.GameDTO" scope="request">

                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="add"/>
                    Game ID: <input type="text" name="txtGameID" value="${game.gameID}"/><br/>
                    <c:if test="${not empty requestScope.gameID_error}">
                        <span style="color: red">${requestScope.gameID_error}</span><br/>
                    </c:if>
                    Game Name: <input type="text" name="txtGameName" value="${game.gameName}"/><br/>
                    <c:if test="${not empty requestScope.gameName_error}">
                        <span style="color: red">${requestScope.gameName_error}</span><br/>
                    </c:if>
                    Developer: <input type="text" name="txtDeveloper" value="${game.developer}"/><br/>
                    <c:if test="${not empty requestScope.deverloper_error}">
                        <span style="color: red">${requestScope.developer_error}</span><br/>
                    </c:if>
                    Genre: <input type="text" name="txtGenre" value="${game.genre}"/><br/>
                    <c:if test="${not empty requestScope.genre_error}">
                        <span style="color: red">${requestScope.genre_error}</span><br/>
                    </c:if>
                    Price: <input type="text" name="txtPrice" value="${game.price}"/><br/>
                    <c:if test="${not empty requestScope.price_error}">
                        <span style="color: red">${requestScope.price_error}</span><br/>
                    </c:if>
                    <input type="submit" value="Save"/> 
                    <input type="reset" value="Reset"/>
                </form>
            </jsp:useBean>
        </c:if>
        <c:if test="${not isAdmin}">

            <p style="text-align: center"><img src="assets/img/403.jpg" alt="Status Code 403"/></p>

        </c:if>
    </body>
</html>
