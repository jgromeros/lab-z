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
	<label>Profesional que realiza la prueba:</label>
	<select name="lab_professional">
		<option></option>
		<c:forEach var="labpro" items="${model.labpros }">
			<c:choose>
				<c:when test="${labcase.labProfessional.id == labpro.id }">
					<option value="${labpro.id }" selected="selected"><c:out value="${labpro }"/></option>
				</c:when>
				<c:otherwise>
					<option value="${labpro.id }"><c:out value="${labpro }"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<br/>
	<label>Director t&eacute;cnico laboratorio:</label>
	<select name="tech_dir">
		<option></option>
		<c:forEach var="td" items="${model.techdirectors }">
			<c:choose>
				<c:when test="${labcase.technicalDirector.id == td.id }">
					<option value="${td.id }" selected="selected"><c:out value="${td }"/></option>
				</c:when>
				<c:otherwise>
					<option value="${td.id }"><c:out value="${td }"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<br/>
	<input type="submit" value="Imprimir" name="accion"/>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>