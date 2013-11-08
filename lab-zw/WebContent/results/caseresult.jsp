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
			<td>Registre aqui los resultados</td>
		</tr>
		<tr>
			<td>C&oacute;digo del caso:</td>
			<td><c:out value="${labcase.code }"/></td>
		</tr>
		<tr>
			<td colspan="2">Los siguientes son los ex&aacute;menes registrados en el caso</td>
		</tr>
		<tr>
			<td colspan="2">Seleccione al que desee registrarle los resultados</td>
		</tr>
		<c:forEach var="animal" items="${labcase.animals }">
			<c:set var="animal1" value="${animal }"/>
		</c:forEach>
		<c:forEach var="test" items="${animal1.tests }">
            <tr>
            	<td><c:out value="${test.testDescription.description }"/></td>
                <c:if test="${labcase.status == 'S' || labcase.status == 'W' }">
    				<td>
    					<a href="testresult.htm?testdesc=<c:out value="${test.testDescription.id }"/>">
    						<img alt="Editar" src="../img/edit.png" width="16" height="16"/>
    					</a>
    				</td>
    			</c:if>
    			<c:if test="${(labcase.status == 'W' || labcase.status == 'F') && test.resultsSize > 0 && test.labProfessional != null}">
    				<td>
    					<a href="printresults.htm?id=${labcase.id }&test=${test.id }">
    						<img alt="Imprimir" src="../img/print.png" width="16" height="16"/>
    					</a>
    				</td>
    			</c:if>
            </tr>
		</c:forEach>
	</table>
    </div>
    <c:import url="/common/footer.jspf"/>
    </body>
</html>