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
		<script src="/lab-zw/js/jquery-ui.min.js"></script>
		<script>
		    $(document).ready(function() {
		        $('#consult').click(function(event) {
		        var $enterprise=$("select#enterprise").val();
		        var $begin=$("input#begin").val();
		        var $end=$("input#end").val();
		           $.get('clientcases.do',{enterprise:$enterprise, begin:$begin, end:$end},function(responseJson) {
		            var $div = $('#labcases');
		               $div.find('input').remove();
		               $div.find('label').remove();
		               $div.find('td').remove();
		               $div.find('tr').remove();
		               var $cases_table = $('<table id="cases_table">').appendTo($div);
		               var $header_row = $('<tr>').appendTo($cases_table);
		               $('<th></th>').appendTo($header_row);
		               $('<th>Caso</th>').appendTo($header_row);
		               $('<th>Fecha</th>').appendTo($header_row);
		               $('<th>Prueba</th>').appendTo($header_row);
		               $('<th>Paciente</th>').appendTo($header_row);
		               $('<th>Observaciones</th>').appendTo($header_row);
		               $('<th>Valor</th>').appendTo($header_row);
		               $('<th>Descuento</th>').appendTo($header_row);
		               $header_row.appendTo($cases_table);
		               $.each(responseJson, function() {
		            	   var $row = $('<tr></tr>');
		                   $('<td><input type="checkbox" id="selected" name="selected" checked="checked" value="' + this.testId + '&' + this.testProfile + '"></td>').appendTo($row);
		                   $('<td>' + this.labcaseCode + '</td>').appendTo($row);
		                   $('<td>' + this.receptionDate + '</td>').appendTo($row);
		                   $('<td>' + this.testDescription + '</td>').appendTo($row);
		                   $('<td>' + this.patientName + '</td>').appendTo($row);
		                   $('<td class="limited">' + this.comment + '</td>').appendTo($row);
		                   $('<td align="right">' + this.price + '</td>').appendTo($row);
		                   $('<td><input type="text" id="price' + this.testId + '&' + this.testProfile + '" name="price' + this.testId + '&' + this.testProfile + '" size="8"/></td>').appendTo($row);
		            	   $row.appendTo($cases_table);
	                    });
		    		    $("#cases_table tr:even").css("background-color", "#EEEEEE");
		            });
		        });
		    });
		</script>
		<script>
			$(function() {
				$('#begin').datepicker({ dateFormat: "dd/mm/yy" });
				$('#end').datepicker({ dateFormat: "dd/mm/yy" });
			});
		</script>
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
					<td>
						<form id="billingForm" name="billingForm" action="billed.htm" method="post">
							<input type="hidden" name="id" value="${model.enterprise.id }"/>
							<table align="center">
								<tr><td align="center">
									Seleccione la entidad que quieres consultar
								</td></tr>
								<tr><td><table align="center">
									<tr>
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
										</td>
									</tr>
									<tr>
										<td><input type="text" id="begin" /></td>
										<td><input type="text" id="end" /></td>
									</tr>

									<tr align="center">
										<td>
											<button id="consult" type="button">Consultar</button>
										</td>
									</tr>

								</table></td></tr>
                                <tr>
                                    <td>
                                        <table>
                                            <div id="labcases">
                                            </div>
                                        </table>
                                    </td>
                                </tr>
								<tr align="center">
									<td>
										<input type="submit" id="generateAction" name="action" value="Generar"/>
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
