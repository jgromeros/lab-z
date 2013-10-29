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
						<form name="form" action="enterprises.htm" method="post">
							<p><c:out value="${model.errores }"/></p>
							<table align="center">
								<tr><td align="center">
									Listado de empresas remitentes
								</td></tr>
								<tr><td><table align="center" border="1">
									<tr>
										<th>Identificaci&oacute;n</th>
										<th>Nombre</th>
										<th>Direcci&oacute;n</th>
										<th>Tel&eacute;fono</th>
										<th>Correo electr&oacute;nico</th>
									</tr>
									<c:forEach var="enterprise" items="${model.enterprises }">
										<tr>
											<td>
												<c:out value="${enterprise.identityNumber }"/>
											</td>
											<td>
												<c:out value="${enterprise.name }"/>
											</td>
											<td>
												<c:out value="${enterprise.address }"/>
											</td>
											<td>
												<c:out value="${enterprise.phone }"/>
											</td>
											<td>
												<c:out value="${enterprise.email }"/>
											</td>
											<td>
												<a href="enterprise.htm?id=${enterprise.id }">
													<img alt="Editar" src="../img/edit.png" width="16" height="16"/>
												</a>
											</td>
										</tr>
									</c:forEach>
								</table></td></tr>
								<tr align="center">
									<td>
										<input type="submit" name="action" value="Guardar"/>
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