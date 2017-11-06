<%-- 
    Document   : inicioInsertar
    Created on : 06-nov-2017, 1:24:15
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar</title>
    </head>
    <body>
        <h1>Introduzca los datos del ave que desea ingresar en la base de datos.</h1>
        
        <form action="../Realizar" method="post">
            <input type='hidden' name="operacion" value="insertar"/>
            <table>
                <tr>
                    <td>Anilla:</td>
                    <td><input type="text" name="anilla"/></td>
                </tr>
                
                <tr>
                    <td>Especie:</td>
                    <td><input type="text" name="especie"/></td>
                </tr>
                
                <tr>
                    <td>Lugar:</td>
                    <td><input type="text" name="lugar"/></td>
                </tr>
                
                <tr>
                    <td>Fecha:</td>
                    <td><input type="date" name="fecha"/></td>
                </tr>
                
                <!-- tr vacio para que el boton de enviar quede un poco más separado de los campos del formulario-->
                <tr>
                    <td></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="enviar" value="Enviar"/>
                        <input type="reset" name="limpiar" value="Limpiar"/>
                    </td>
                </tr>
                
            </table>            
            
        </form>        

        <br>
        
        <a href="<%= request.getContextPath()%>">Volver</a>
    </body>
</html>
