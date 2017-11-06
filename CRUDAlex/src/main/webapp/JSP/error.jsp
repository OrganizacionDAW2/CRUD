<%-- 
    Document   : Error
    Created on : 05-nov-2017, 21:18:54
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h2><%=request.getSession().getAttribute("error")%></h2>
        <a href="<%= request.getContextPath()%>">Volver</a>
    </body>
</html>

