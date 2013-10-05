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
	<p>Montaje realizado con exito para los casos:</p>
	<c:forEach var="case" items="${model.cases }">
		<label><c:out value="${case.code }"/></label>
	</c:forEach>
	<c:forEach var="plaques" begin="0" end="${model.assembly.numberOfPlaques }">
		<label>Placa <c:out value="${plaques }"/></label>
		<table border="1">
			<c:forEach var="rows" begin="0" end="${model.assembly.totalRows - 1}">
				<tr>
					<c:forEach var="cols" begin="0" end="${model.assembly.totalCols - 1}">
						<td><c:out value="${model.assemblyArray[plaques][rows][cols] }"/></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</c:forEach>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>