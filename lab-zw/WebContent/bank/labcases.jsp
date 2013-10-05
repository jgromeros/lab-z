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
	<form action="save.htm" method="post">
		<table align="center">
			<tr>
				<td>Estos son los casos que tienen muestras que no han sido almacenadas en el banco de sueros:</td>
			</tr>
			<tr>
				<td>
					<table border="1">
						<tr align="center">
							<th>C&oacute;digo</th>
							<th>Agregar</th>
							<th>Mover</th>
						</tr>
						<c:forEach var="case" items="${model.cases }">
							<tr align="center">
								<td><c:out value="${case.code }"/></td>
								<td><input type="checkbox" name="add" value="${case.id }"/></td>
								<td><input type="checkbox" name="movable" value="${case.id }"/></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td>Seleccione el banco en el que quiere almacenar las muestras</td>
			</tr>
			<tr>
				<td>
					<table>
						<c:forEach var="bank" items="${model.banks }">
							<tr>
								<td><c:out value="${bank.name }"/></td>
								<td><input type="radio" name="bank" value="${bank.id }"/></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<input type="submit" name="accion" value="Agregar"/>
				</td>
			</tr>
		</table>
	</form>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>