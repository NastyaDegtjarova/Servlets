<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

	<head>
		<title> Manufacturer </title>
		<meta charset="UTF-8">
	</head>

	<body>
	<h1><p>Manufacturers</p></h1>
	<button onclick="location.href='http://www.google.com'" type="button">
     www.google.com</button>
     <c:forEach items="${requestScope.manufs}" var="manuf">

        <p>${manuf}</p>
        <p><c:out value="${requestScope.manufs}"></c:out></p>
     </c:forEach>
	 <p></p>
	<a href = 'createManufacturer.html'><input type = "button" value = "create Manufacturer"/></a>
	<p></p>
	<a href = 'updateManufacturer.html'><input type = "button" value = "update Manufacturer"/></a>
	<p></p>
	<a href = 'showAllManufacturer.html'><input type = "button" value = "showAll Manufacturer"/></a>
	<p></p>
	<a href = 'deleteManufacturer.html'><input type = "button" value = "delete Manufacturer"/></a>
	<p></p>
	<p></p>


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
