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
	       <p>Los siguientes son los casos que cerrara:</p>
	       <form name="form" action="close.htm" method="post">
	           <table>
			        <c:forEach var="labcase" items="${model.labcasesToFinish }">
			            <tr>
			                <td>
			                    <c:out value="${labcase.code }"/>
			                </td>
                            <td>
                                <c:out value="${labcase.enterpriseSender.name }"/>
                            </td>
			            </tr>
			        </c:forEach>
			    </table>
                <table align="center">
	                <tr>
	                    <td>
	                        <input type="submit" name="action" value="Cerrar"/>
	                    </td>
	                </tr>
	            </table>
			</form>
	    </div>
        <c:import url="/common/footer.jspf"/>
    </body>
</html>