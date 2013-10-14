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
			<th>C&oacute;digo</th>
			<th>Solicitante</th>
			<th>Propietario</th>
			<th>Acciones</th>
		</tr>
			<c:forEach var="labcase" items="${model.labcases }">
				<tr>
					<td><c:out value="${labcase.code }"/></td>
					<td><c:out value="${labcase.enterpriseSender.name }"/></td>
					<td align="center"><c:out value="${labcase.owner }"/></td>
					<td><a href="../labcase/page1.htm?code=${labcase.code }">
						<img alt="Editar" src="../img/edit.png" width="16" height="16"/>
					</a>&nbsp;<a href="../results/caseresult.htm?id=${labcase.id }">
						<img alt="Resultados" src="../img/results.png" width="16" height="16"/>
					</a></td>
				</tr>
			</c:forEach>
	</table>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>