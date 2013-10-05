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
	<form action="savebank.htm" method="post">
		<input type="hidden" name="id" value="${model.bank.id }"/>
		<c:choose>
			<c:when test="${model.bank.placedIn == null }">
				<input type="hidden" name="parent" value="${model.parent.id }"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="parent" value="${model.bank.placedIn.id }"/>
			</c:otherwise>
		</c:choose>
		<table>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="name" value="${model.bank.name }"/></td>
			</tr>
			<tr>
				<td>Descripci&oacute;n</td>
				<td><input type="text" name="description" value="${model.bank.longDescription }"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="accion" value="Guardar"/>
					<input type="submit" name="accion" value="Borrar"/>
				</td>
			</tr>
		</table>
	</form>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>