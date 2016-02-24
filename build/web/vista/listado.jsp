<%-- 
    Document   : listado
    Created on : 15-feb-2016, 12:31:17
    Author     : Jose
--%>

<%@page import="pojo.Persona"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List array=(List)session.getAttribute("APersonas");%> <!-- Lista Censo-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css"/>
        <title>JSP Page</title>
    </head>
    <body> 
        
        <table class="tabla">
            <tr  class="tdgris">
                <td>NIF</td>
                <td>Nombre</td>
                <td>Domicilio</td>
                <td>Emails</td>
            </tr>
            <% 
                Iterator iter=array.iterator();
                while(iter.hasNext()){
                    Persona persona=(Persona)iter.next();
                %>
                <tr>   
                    <td><%=persona.getNif()%></td>   
                    <td><%=persona.getNombre()%></td>
                    <td><%=persona.getDomicilio()%></td>   
                    <td><%=persona.getDireccions()%></td>   
                </tr>
                <%
                }
            
            %>
              
        </table>
            <a href="../index.html">Volver</a>
        
    </body>
</html>
