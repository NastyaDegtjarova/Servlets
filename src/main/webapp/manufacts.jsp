<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

	<head>
		<title> Manufacturer </title>
		<meta charset="UTF-8">
	</head>

	<body>
	<h1><p>Manufacturers</p></h1>
    <table border="1">
    <tr><th>Name</th><th></th><th></th></tr>
     <c:forEach items="${requestScope.manufs}" var="manuf">
     <tr>
        <td>
            <p>${manuf.nameManufact}</p>
        </td>
        <td>
            <form action = "/manuf" method="get">
                <input type = "submit" value = "Edit"/>
                <input type="hidden" name="id" value="${manuf.idManufact}"/>
            </form>
        </td>
        <td>
            <form action = "/manuf" method="post">
                <input type = "hidden" name="action" value = "delete"/>
                <input type = "hidden" name="id" value="${manuf.idManufact}"/>
                <input type = "submit" value = "Delete"/>
            </form>
        </td>
     </tr>
     </c:forEach>

</table>
            <form action = "/saveManuf.jsp" method="post">
                <input type = "hidden" name="action" value = "save"/>
                <input type = "submit" value = "New company"/>
            </form>

	</body>
</html>
