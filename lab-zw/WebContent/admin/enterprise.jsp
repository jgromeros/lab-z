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
		<script src="/lab-zw/js/jquery.validate.min.js"></script>
		<script>
		    $("#enterpriseForm").validate();
		</script>

        <title>Lab-z</title>
    </head>
    <body>
        <c:import url="/common/header.jspf"/>

		<jsp:directive.include file="../common/menu.jspf"/>
		<div class="layerder">
			<p><c:out value="${model.errores }"/></p>
			<table align="center">
				<tr>
					<td>
						<form id="enterpriseForm" name="enterpriseForm" action="enterprises.htm" method="post">
							<input type="hidden" name="id" value="${model.enterprise.id }"/>
							<table align="center">
								<tr><td align="center">
									Registre la información de la empresa
								</td></tr>
								<tr><td><table align="center">
									<tr>
										<td>Identificaci&oacute;n:</td>
										<td>
											<input type="text" id="idnumber" name="idnumber"
													value="${model.enterprise.identityNumber }" size="32" maxlength="16"/>
										</td>
										<td>Nombre:</td>
										<td>
											<input type="text" id="name" name="name"
													value="${model.enterprise.name }" size="32" maxlength="128" required/>
										</td>
									</tr>
									<tr>
										<td>Direcci&oacute;n:</td>
										<td>
											<input type="text" id="address" name="address"
													value="${model.enterprise.address }" size="32" maxlength="255"/>
										</td>
										<td>Tel&eacute;fono:</td>
										<td>
											<input type="text" id="phone" name="phone"
													value="${model.enterprise.phone }" size="32" maxlength="16"/>
										</td>
									</tr>
									<tr>
										<td>Correo electr&oacute;nico:</td>
										<td>
											<input type="text" id="email" name="email"
													value="${model.enterprise.email }" size="32" maxlength="64"/>
										</td>
									</tr>
								</table></td></tr>
								<tr align="center">
									<td>
										<input type="submit" id="saveAction" name="action" value="Guardar"/>
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