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
		<script src="/lab-zw/js/jquery-1.10.2.min.js"></script>
		<script src="/lab-zw/js/jquery.validate.min.js"></script>
		<script>
		    $(document).ready(function() {
		        $('#region').change(function(event) {
		        var $region=$("select#region").val();
		           $.get('ciudades.do',{regionname:$region},function(responseJson) {
		            var $select = $('#city');
		               $select.find('option').remove();
		               $.each(responseJson, function(key, value) {
		                   $('<option>').val(key).text(value).appendTo($select);
		                    });
		            });
		        });
		    });
		</script>
		<script>
		    $("#labcase1Form").validate();
		</script>

        <title>Lab-z</title>
    </head>
    <body>
        <c:import url="/common/header.jspf"/>

<jsp:directive.include file="../common/menu.jspf"/>
<div class="layerder">
	<table align="center">
		<tr>
			<td>
				<form id="labcase1Form" name="form" action="page2.htm" method="post">
					<table align="center">
						<tr><td align="center">
							Registre la información del caso de laboratorio
						</td></tr>
						<tr><td><table align="center">
							<tr>
								<td>Entidad solicitante:</td>
								<td>
									<select id="enterprise" name="enterprise" required>
										<option></option>
										<c:forEach var="enterprise" items="${model.enterprises }">
											<c:choose>
												<c:when test="${enterprise.id == labcase.enterpriseSender.id }">
													<option value="${enterprise.id }" selected="selected">
														<c:out value="${enterprise.name }"/>
													</option>
												</c:when>
												<c:otherwise>
													<option value="${enterprise.id }">
														<c:out value="${enterprise.name }"/>
													</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									<a href="../admin/enterprise.htm">
										<img alt="Agregar" src="../img/add.png" width="16" height="16"/>
									</a>
								</td>
							</tr>
							<tr>
								<td>Responsable</td>
								<td colspan="3">
									<input type="text" name="sender" value="${labcase.sender }"
											size="32" maxlength="128"/>
								</td>
							</tr>
							<tr>
								<td>Propietario</td>
								<td colspan="3">
									<input type="text" name="owner" value="${labcase.owner }"
											size="32" maxlength="128"/>
								</td>
							</tr>
							<tr>
								<td>Número de animales</td>
								<td>
									<input type="text" id="animalNumber" name="number" size="4"
											value="${labcase.totalOfAnimals }" required/>
								</td>
			    				<td>Especie</td>
									<td>
										<select id="specie" name="specie" required>
											<option></option>
											<c:forEach var="spec" items="${model.species }">
												<c:choose>
													<c:when test="${spec.id == labcase.animals[0].race.specie.id }">
														<option value="${spec.id }" selected="selected">
															<c:out value="${spec.name }"/>
														</option>
													</c:when>
													<c:otherwise>
														<option value="${spec.id }">
															<c:out value="${spec.name }"/>
														</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</td>
							</tr>
						</table></td></tr>
						<tr><td><table align="center">
							<tr>
								<td>Departamento</td>
								<td>Ciudad</td>
							</tr>
							<tr>
								<td>
									<select name="region" id="region" required>
										<option></option>
										<c:forEach var="reg" items="${model.regions }">
											<c:choose>
												<c:when test="${reg.id == labcase.city.placedIn.id }">
													<option value="${reg.id }" selected="selected">
														<c:out value="${reg.name }"/>
													</option>
												</c:when>
												<c:otherwise>
													<option value="${reg.id }">
														<c:out value="${reg.name }"/>
													</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</td>
								<td>
									<select name="city" id="city" required>
										<option></option>
										<c:forEach var="city" items="${model.cities }">
											<c:choose>
												<c:when test="${city.id == labcase.city.id }">
													<option value="${city.id }" selected="selected">
														<c:out value="${city.name }"/>
													</option>
												</c:when>
												<c:otherwise>
													<option value="${city.id }">
														<c:out value="${city.name }"/>
													</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>Propósito del análisis</td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="purpose" rows="2" cols="70"><c:out value="${labcase.analysisPurpose }"/></textarea></td>
							</tr>
						</table></td></tr>
					</table>
					<table align="center">
						<tr><td align="center">
							Seleccione las muestras
						</td></tr>
						<tr><td><table align="center">
							<c:set var="i" value="1"/>
							<c:forEach var="samptype" items="${model.sampleTypes }">
								<c:set var="selected" value="false"/>
								<c:forEach var="test" items="${labcase.animals[0].tests}">
									<c:if test="${test.testDescription.sampleType.id == samptype.id }">
										<c:set var="selected" value="true"/>
									</c:if>
								</c:forEach>
								<c:set var="i" value="${i + 1 }"/>
								<c:if test="${i%2 == 0 }">
								<tr>
								</c:if>
									<td>
										<c:choose>
											<c:when test="${selected == true }">
												<input type="checkbox" name="sampletype"
														value="${samptype.id }" checked="checked"/>
													<c:out value="${samptype.description }"/>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="sampletype"
														value="${samptype.id }"/>
													<c:out value="${samptype.description }"/>
											</c:otherwise>
										</c:choose>
									</td>
								<c:if test="${i%2 != 0 }">
								</tr>
								</c:if>
							</c:forEach>
						</table></td></tr>
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
        <br/>
        <div class="layerdown">
            <c:import url="/common/footer.jspf"/>
        </div>
    </body>
</html>
