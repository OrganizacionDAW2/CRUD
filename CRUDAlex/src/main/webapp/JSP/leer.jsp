<%-- 
    Document   : Leer
    Created on : 05-nov-2017, 21:18:43
    Author     : Alex
--%>

<%@page import="es.albarregas.CRUDAlex.beans.Ave"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de las aves</title>
    </head>
    <%
        ArrayList<Ave> aves = (ArrayList<Ave>) session.getAttribute("pajaros");
    %>
    <body>
        <h1>Datos de las aves</h1>
        <table>

            <%
                if (!aves.isEmpty()) {
            %>
            <tr>
                <th>Anilla</th>
                <th>Especie</th>
                <th>Lugar</th>
                <th>Fecha</th>
            </tr>
            <%
                for (Ave ave : aves) {
            %>

            <tr>    
                <td><%=ave.getAnilla()%></td>
                <td><%=ave.getEspecie()%></td>
                <td><%=ave.getLugar()%></td>
                <td><%=ave.getFecha()%></td>
            </tr>
            <%
                }
            } else {
            %>

            <td colspan="4">No existen aves que visualizar</td>

            <%
                }
            %>
        </table>
        <br>
        <a href="<%= request.getContextPath()%>">Volver</a>
    </body>
</html>
