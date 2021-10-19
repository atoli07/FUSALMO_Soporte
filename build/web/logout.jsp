<%-- 
    Document   : newjsp
    Created on : 10-15-2021, 03:27:44 AM
    Author     : Nemy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="REFRESH" content="2;URL=index.xhtml"/>
    </head>
    <body>
        Espera, por favor...
        
        <c:remove var="idEmpleado" scope="session"/>
        <c:remove var="nombre" scope="session"/>
        
    </body>
</html>
