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
        <title>Lab-z</title>
    </head>
    <body>
        <c:import url="/common/header.jspf"/>

<jsp:directive.include file="../common/menu.jspf"/>
<div class="layerder">
    <div id="dialog-confirm" title="Cancelar Prueba?">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Está seguro que desea cancelar esta prueba? No podrá deshacer este cambio</p>
    </div>
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
		<tr><td>
			<c:forEach var="animal" items="${labcase.animals }">
				<table border="1">
					<tr><td><c:out value="${animal.name }"/></td><td></td></tr>
					<c:forEach var="test" items="${animal.tests }">
			            <tr>
			            	<td class="data"><c:out value="${test.testDescription.description }"/></td>
			            	<td><table border="0">
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
			    			</table></td>
			            </tr>
					</c:forEach>
				</table>
				<br/>
			</c:forEach>
		</td></tr>
	</table>
    </div>
    <c:import url="/common/footer.jspf"/>
    </body>
</html>