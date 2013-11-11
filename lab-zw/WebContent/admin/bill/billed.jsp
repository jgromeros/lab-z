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
        <link rel="stylesheet" href="/lab-zw/css/jquery-ui.css" type="text/css" />
		<script src="/lab-zw/js/jquery-1.10.2.min.js"></script>
		<script src="/lab-zw/js/jquery.validate.min.js"></script>
		<script>
		    $("#billingForm").validate();
		</script>

        <title>Lab-z</title>
    </head>
    <body>
        <c:import url="/common/header.jspf"/>

		<jsp:directive.include file="../../common/menu.jspf"/>
		<div class="layerder">
			<p><c:out value="${model.errores }"/></p>
			<table align="center">
				<tr>
					<td>Documento generado con &eacute;xito</td>
					<td><a href="">Imprimir</a></td>
				</tr>
			</table>
        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>
