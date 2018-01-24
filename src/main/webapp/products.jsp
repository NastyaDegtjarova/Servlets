<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

	<head>
		<title> Products </title>
		<meta charset="UTF-8">
	</head>

	<body>
	<h1><p>Products</p></h1>
 <table border="1">
    <tr><th>Name</th><th></th><th></th></tr>
     <c:forEach items="${requestScope.prods}" var="prod">
     <tr>
        <td>
            <p>${prod.nameProd}</p>
        </td>
        <td>
            <form action = "/prod" method="get">
                <input type = "submit" value = "Edit"/>
                <input type="hidden" name="id" value="${prod.idProd}"/>
            </form>
        </td>
        <td>
            <form action = "/prod" method="delete">
                <input type = "submit" value = "Delete"/>
            </form>
        </td>
     </tr>
     </c:forEach>

</table>
            <form action = "/saveProd.jsp" method="post">
                <input type = "submit" value = "New company"/>
            </form>
<%--	 <p></p>
	<a href = 'createProduct.html'><input type = "button" value = "create Product"/></a>
	<p></p>
	<a href = 'updateProduct.html'><input type = "button" value = "update Product"/></a>
	<p></p>
	<a href = 'showAllProduct.html'><input type = "button" value = "showAll Product"/></a>
	<p></p>
	<a href = 'deleteProduct.html'><input type = "button" value = "delete Product"/></a>
	<p></p>
	<p></p> --%>


	  <select multiple = "true">
	  <option></option>
	  <option></option>
	  <option></option>
	  <input type = "submit" value = "send"/>
	  <input type = "text/">
	  <form></form>
	  <form action = "/manufacturer/delete">
	  <select name = "name">

	  </select>
		<p></p>
	</body>
</html>
