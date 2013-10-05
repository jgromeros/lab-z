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
	<form action="selectcases.htm" method="post">
		<p>Seleccione el tipo de montaje que desea realizar:</p>
		<c:forEach var="td" items="${model.testDescriptions }">
			<input type="radio" name="testdescription" value="<c:out value="${td.id }"/>"/>
			<label><c:out value="${td.description }"/></label>
			<br/>
		</c:forEach>
		<input type="submit" name="action" value="next"/></td>
	</form>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>