<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/lab-zw/css/lab-z.css" type="text/css" />
        <link rel="stylesheet" href="/lab-zw/css/capas.css" type="text/css" />
        <link rel="stylesheet" href="/lab-zw/css/menu.css" type="text/css" />
        <title>Lab-z</title>
    </head>
    <body>
        <c:import url="/common/header.jspf"/>
		<form method="POST" action='<%= response.encodeURL("j_security_check") %>' >
			<table border ="0" id="parrafo">
				<tr>
					<td>Usuario</td>
					<td><input type="text" name="j_username" value="" /></td>
				</tr>
				<tr>
					<td>Contrasena</td>
					<td><input type="password" name='j_password' value="" /></td>
				</tr>                
			</table>
			<h1> 
				<input id="boton" type="submit" value="Ingresar" />
			</h1>
		</form>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>