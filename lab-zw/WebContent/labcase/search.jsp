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
	<table align="center">
		<tr>
			<td>
				<form name="form" action="found.htm" method="post">
					<table align="center">
						<tr><td align="center" colspan="2">
							Busque aqui el caso de laboratorio que quiere ver
						</td></tr>
						<tr>
							<td>Numero de caso:</td>
							<td>
								<input type="text" name="code"
										size="24" maxlength="128"/>
							</td>
							<td>Entidad solicitante:</td>
							<td>
								<select name="enterprise">
									<option></option>
									<c:forEach var="enterprise" items="${model.enterprises }">
										<option value="${enterprise.id }">
											<c:out value="${enterprise.name }"/>
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Propietario:</td>
							<td>
								<input type="text" name="owner" size="24"/>
							</td>
							<td>Fecha de registro:</td>
							<td>
								<input type="text" name="regdate"/>
							</td>
						</tr>
						<tr>
							<td>Nombre animal:</td>
							<td>
								<input type="text" name="animalName"
										size="24" maxlength="128"/>
							</td>
							<td>Caso sin registro completo
								<input type="checkbox" name="finished" value="R"/>
							</td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td>
								<input type="submit" name="action"
										value="Continuar"/>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>