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

<jsp:directive.include file="../common/menu.jspf"/>
<div class="layerder">
	<p>Estos son los bancos de sueros existentes en el laboratorio</p>
	<table border="1">
		<tr>
			<th>Nombre</th>
			<th>Descripci&oacute;n</th>
		</tr>
		<c:forEach var="bank" items="${model.banks }">
			<tr>
				<td><a href="viewbank.htm?id=${bank.id }"><c:out value="${bank.name }"/></a></td>
				<td><c:out value="${bank.longDescription }"/></td>
				<td><a href="bankform.htm?id=${bank.id }">Editar</a></td>
			</tr>
		</c:forEach>
	</table>
	<p><a href="bankform.htm">Crear Banco</a></p>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>