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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="../common/menu.jspf"/>
<div class="layerder">
	<form action="newassembly.htm" method="post">
		<input type="hidden" name="testdescription" value="<c:out value="${model.testDescription.id }"/>"/>
		<p>Seleccione los casos que desea incluir en este montaje</p>
			<c:forEach var="case" items="${model.cases }">
				<input type="checkbox" name="case" value="<c:out value="${case.id }"/>"/>
				<label><c:out value="${case.code }"/></label>
				<br/>
			</c:forEach>
			<input type="submit" name="action" value="do assembly"/>
	</form>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>