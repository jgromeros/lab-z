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
	<table>
		<tr>
			<td>Estos son los montajes que todavia no ha cerrado</td>
		</tr>
		<tr>
			<td>Seleccione el montaje para el que desea registrar resultados</td>
		</tr>
		<c:forEach var="assembly" items="${model.pendingAssemblies }">
			<tr>
				<td>
					<a href="assemblyresult.htm?id=<c:out value="${assembly.id }"/>"/>
						Montaje # <c:out value="${assembly.id }"/> de <fmt:formatDate pattern="yyyy-MM-dd" value="${assembly.assemblyDate }"/>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>