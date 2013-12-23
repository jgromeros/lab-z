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
        <script>
            $(document).ready(function() {
            	
            }
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
        				<form name="form" action="page3.htm" method="post">
        					<table align="center">
        						<tr>
        							<td align="center">Seleccione las pruebas a realizar</td>
        						</tr>

                                    <tr><td><table>
                                        <tr>
                                            <th>Perfil</th>
                                            <th>Descuento</th>
                                        </tr>
                                        <c:forEach var="tp" items="${model.profiles }">
                                            <tr><td>
                                                <input type="checkbox" name="profile" value="${tp.id }"/>
                                                <c:out value="${tp.description }"/>
                                            </td>
                                            <td align="center">
                                                <input type="checkbox" name="discount" value="${td.id }"/>
                                            </td></tr>
                                        </c:forEach>
                                    </table></td></tr>

        						<tr><td><table align="center">
        							<tr>
        								<th>Examen</th>
                                        <th>Descuento</th>
                                        <th>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</th>
                                        <th>Examen</th>
                                        <th>Descuento</th>
        							</tr>
        							<c:set var="i" value="1"/>
        							<c:forEach var="td" items="${model.testDescriptions }">
        								<c:set var="selected" value="false"/>
        								<c:forEach var="test" items="${labcase.animals[0].tests }">
        									<c:if test="${td.id == test.testDescription.id }">
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
        												<input type="checkbox" name="testdesc"
        														value="${td.id }" checked="checked"/>
    											        <c:out value="${td.description }"/>
        											</c:when>
        											<c:otherwise>
        												<input type="checkbox" name="testdesc" value="${td.id }"/>
        													<c:out value="${td.description }"/>
        											</c:otherwise>
        										</c:choose>
        									</td>
                                            <td align="center">
                                                <input type="checkbox" name="discount" value="${td.id }"/>
                                            </td>
                                            <td></td>
        								<c:if test="${i%2 != 0}">
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
        							<td>
        								<input type="submit" name="action"
        										value="Cargar"/>
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