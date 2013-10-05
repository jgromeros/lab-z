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
				<form name="forma" action="done.htm" method="post">
					<table align="center">
						<tr>
							<td align="center">Digite aqui la información de cada animal</td>
						</tr>
						<tr><td><table align="center">
							<tr>
			    				<td>Identificación</td>
			    				<td>Sexo</td>
			    				<td>Edad</td>
			    				<td>Raza</td>
			    				<td>Observaciones</td>
			    			</tr>
							<c:forEach var="i" begin="${model.nextAnimalIndex }" end="${model.endAnimalIndex }">
				    			<tr>
									<td>
										<input type="text" name="animalname"
											size="16" maxlength="32"
											value="${labcase.animals[i].name }"/>
									</td>
									<td>
										<select name="gender">
											<option></option>
											<c:choose>
												<c:when test="${labcase.animals[i].gender == 'M' }">
													<option value="M" selected="selected">Macho</option>
													<option value="H">Hembra</option>
												</c:when>
												<c:when test="${labcase.animals[i].gender == 'H' }">
													<option value="M">Macho</option>
													<option value="H" selected="selected">Hembra</option>
												</c:when>
												<c:otherwise>
													<option value="M">Macho</option>
													<option value="H">Hembra</option>
												</c:otherwise>
											</c:choose>
										</select>
									</td>
									<td>
										<input type="text" name="age"
											size="16" maxlength="16"
											value="${labcase.animals[i].age }"/>
									</td>
									<td>
										<select name="race">
											<option></option>
											<c:forEach var="race" items="${selectedSpecie.races }">
												<c:choose>
													<c:when test="${labcase.animals[i].race.id == race.id }">
														<option value="${race.id }" selected="selected">
															<c:out value="${race.name }"/>
														</option>
													</c:when>
													<c:otherwise>
														<option value="${race.id }">
															<c:out value="${race.name }"/>
														</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</td>
									<td>
										<textarea name="notes" rows="1" cols="50"><c:out value="${labcase.animals[i].observations }"/></textarea>
									</td>
								</tr>
							</c:forEach>
						</table></td></tr>
					</table>
					<table align="center">
						<tr>
							<td>
				    			<input type="hidden" name="nextAnimalIndex" value="${model.nextAnimalIndex + 20 }"/>
								<input type="submit" name="action"
									value="Guardar"/>
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