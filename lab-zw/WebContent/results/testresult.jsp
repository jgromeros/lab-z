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
        <script src="/lab-zw/js/jquery-ui.min.js"></script>
		<script src="/lab-zw/js/jquery.validate.min.js"></script>
        <script>
            $(document).ready(function () {
                $( "#dialog-confirm" ).dialog({
                    resizable: false,
                    height:140,
                    modal: true,
                    autoOpen: false,
                });

                $( ".cancel" ).click(function(e) {
    	            e.preventDefault();
    	            var hrefAttribute = $(this).attr("href");
    	        	$( "#dialog-confirm" ).dialog({
                        buttons: {
                            "Cancelar Prueba": function() {
                            	window.location.href = hrefAttribute;
                            },
                            "No cancelar": function() {
                              $( this ).dialog( "close" );
                            }
                          }
    	        	});
            	  	$( "#dialog-confirm" ).dialog( "open" );
            	});
	        });
        </script>
		<script>
		    $("#resultsForm").validate();
		</script>
        <title>Lab-z</title>
    </head>
    <body>
        <c:import url="/common/header.jspf"/>

<jsp:directive.include file="../common/menu.jspf"/>
<div class="layerder">
	<form id="resultsForm" action="registered.htm" method="post">
        <div id="dialog-confirm" title="Empty the recycle bin?">
            <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Está seguro que desea cancelar esta prueba? No podrá deshacer este cambio</p>
        </div>
		<input type="hidden" name="testdesc" value="${model.testdesc }"/>
		<h3><c:out value="${model.testDescription }"/></h3>
		<table>
			<tr>
				<td colspan="3">Registre los resultados</td>
			</tr>
			<c:forEach var="animal" items="${labcase.animals }">
				<c:forEach var="test" items="${animal.tests }">
                    <c:if test="${test.testDescription.id == model.testdesc && test.status == 'C'}">
                        <tr><td><table border="1">
                            <tr>
                                <th>
                                    <c:out value="${animal.name }"/>&nbsp Cancelado
                                </th>
                            </tr>
                        </table></td></tr>
                    </c:if>
					<c:if test="${test.testDescription.id == model.testdesc && test.status != 'C'}">
						<c:set var="testForLabProfessional" value="${test }"/>
						<tr><td><table border="1">
							<tr>
								<th>
                                    <c:out value="${animal.name }"/>
                                    <a class="cancel" href="testresult.htm?action=cancel&id=${test.id }&testdesc=${test.testDescription.id }">
                						<img alt="Cancelar" src="../img/cancel.png" width="16" height="16"/>
    					            </a>
                                </th>
							</tr>
							<tr>
								<td colspan="2">
									<table>
										<c:set var="group"/>
										<c:set var="withResult" value="false"/>
										<c:forEach var="result" items="${test.results }">
											<c:set var="withResult" value="true"/>
											<c:if test="${group != result.resultFactor.group }">
												<tr>
													<td>
														<b><c:out value="${result.resultFactor.group }"/></b>
													</td>
												</tr>
											</c:if>
											<tr>
												<td><c:out value="${result.resultFactor.name }"/></td>
												<td>
												    <c:choose>
													    <c:when test="${result.resultFactor.calculated == true}">
															<input type="text" value="<c:out value="${result.value }"/>" readonly="readonly"
																	name="test<c:out value="${test.id }"/>factor<c:out value="${result.resultFactor.id }"/>"/>
													    </c:when>
                                                        <c:when test="${ result.resultFactor.valueType =='T' }">
                                                            <textarea name="test<c:out value="${test.id }"/>factor<c:out value="${result.resultFactor.id }"/>"
                                                                    rows="10" cols="80"><c:out value="${result.value }"/></textarea>
                                                        </c:when>
													    <c:otherwise>
                                                            <input type="text" value="<c:out value="${result.value }"/>"
                                                                    name="test<c:out value="${test.id }"/>factor<c:out value="${result.resultFactor.id }"/>"/>
													    </c:otherwise>
													</c:choose>
												</td>
												<td><c:out value="${result.resultFactor.unit }"/></td>
												<c:if test="${result.resultFactor.computedValue == true }">
												    <td>
												        <input type="text" value="<c:out value="${result.relativeValue }"/>"
												                name="test<c:out value="${test.id }"/>relativefactor<c:out value="${result.resultFactor.id }"/>"/>%
												    </td>
												</c:if>
											</tr>
											<c:set var="group" value="${result.resultFactor.group }"/>
										</c:forEach>
										<c:if test="${withResult == false }">
											<c:forEach var="resultfactor" items="${test.testDescription.resultFactors }">
												<c:if test="${group != resultfactor.group }">
													<tr>
														<td>
															<b><c:out value="${resultfactor.group }"/></b>
														</td>
													</tr>
												</c:if>
												<tr>
													<td><c:out value="${resultfactor.name }"/></td>
													<td>
														<input type="text" name="test<c:out value="${test.id }"/>factor<c:out value="${resultfactor.id }"/>"/>
													</td>
													<td><c:out value="${resultfactor.unit }"/></td>
												</tr>
											<c:set var="group" value="${resultfactor.group }"/>
											</c:forEach>
										</c:if>
									</table>
								</td>
							</tr>
						</table></td></tr>
					<tr>
						<td colspan="2">Observaciones:</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="4" cols="80" name="obs_test<c:out value="${test.id }"/>"><c:out value="${test.observations }"/></textarea>
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<th colspan="2">Valores de referencia</th>
						</tr>
						<c:forEach var="refvalue" items="${model.references }">
							<tr>
								<td><c:out value="${refvalue.description }"/></td>
								<td><c:out value="${refvalue.minAbsoluteValue}"/></td>
								<td><c:out value="${refvalue.maxAbsoluteValue}"/></td>
 							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
            <tr><td><table>
			<tr>
				<td>Profesional que realiza la prueba:</td>
				<td>
					<select id="labProfessional" name="lab_professional" required>
						<option></option>
						<c:forEach var="labpro" items="${model.labpros }">
							<c:choose>
								<c:when test="${testForLabProfessional.labProfessional.id == labpro.id }">
									<option value="${labpro.id }" selected="selected"><c:out value="${labpro }"/></option>
								</c:when>
								<c:otherwise>
									<option value="${labpro.id }"><c:out value="${labpro }"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Director cient&iacute;fico del laboratorio:</td>
				<td>
					<select id="techDir" name="tech_dir" required>
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
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" id="guardarAction" name="action" value="Guardar"/>
				</td>
			</tr>
           </table></tr></tr>
		</table>
	</form>

        </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>