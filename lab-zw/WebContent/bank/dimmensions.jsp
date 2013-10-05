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
		<input type="hidden" name="bank" value="${model.level.placedIn.id }"/>
		<input type="hidden" name="level" value="${model.level.id }"/>
		<table>
			<tr>
				<td colspan="2">Defina el nivel <c:out value="${model.level.name }"/></td>
			</tr>
			<tr>
				<td>N&uacute;mero de gavetas</td>
				<td>
					<select name="level-parts">
						<c:forEach var="i" begin="1" end="10">
							<option value="${i }">${i }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>N&uacute;mero de espacios por gaveta</td>
				<td>
					<select name="spaces">
						<c:forEach var="i" begin="1" end="10">
							<option value="${i }">${i }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>N&uacute;mero de gradillas por espacio</td>
				<td>
					<select name="grids">
						<c:forEach var="i" begin="1" end="20">
							<option value="${i }">${i }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>N&uacute;mero de muestras por gradilla</td>
				<td>
					<select name="grid-places">
						<c:forEach var="i" begin="50" end="100" step="50">
							<option value="${i }">${i }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="accion" value="Guardar"/>
				</td>
			</tr>
		</table>
	</form>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>