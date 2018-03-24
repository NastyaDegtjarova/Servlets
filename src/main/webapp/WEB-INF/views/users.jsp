<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

	<head>
		<title> Users </title>
		<meta charset="UTF-8">
	</head>

	<body>
	<h1><p>Users</p></h1>
    <table border="1">
    <tr><th>Name</th><th></th><th></th></tr>
     <c:forEach items="${requestScope.users}" var="user">
     <tr>
        <td>
            <p>${user.firstName}</p>
        </td>
        <td>
            <form action = "/users/edit" method="get">
                <input type = "submit" value = "Edit"/>
                <input type="hidden" name="id" value="${user.id}"/>
            </form>
        </td>
        <td>
            <form action = "/users/delete" method="get">
                <input type = "hidden" name="action" value = "delete"/>
                <input type = "hidden" name="id" value="${user.id}"/>
                <input type = "submit" value = "Delete"/>
            </form>
        </td>
     </tr>
     </c:forEach>

</table>
            <form action = "/users/save" method="get">
                <input type = "hidden" name="action" value = "save"/>
                <input type = "submit" value = "New user"/>
            </form>

	</body>
</html>
