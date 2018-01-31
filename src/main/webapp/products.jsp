<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title> Products </title>
    <meta charset="UTF-8">
</head>

<body>
<h1><p>Products</p></h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.prods}" var="prod">
        <tr>
            <td>
                <p>${prod.nameProduct}</p>
            </td>
            <td>
                <p>${prod.manufacturer.nameManufact}</p>
            </td>
            <td>
                <form action="/saveProd" method="post">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="id" value="${prod.idProduct}"/>
                    <input type="hidden" name="name" value="${prod.nameProduct}"/>
                    <input type="hidden" name="price" value="${prod.priceProduct}"/>
                    <input type="hidden" name="manufId" value="${prod.manufacturer.idManufact}"/>
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <td>
                <form action="/prod" method="post">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${prod.idProduct}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
<form action="/saveProd" method="get">
    <input type="hidden" name="action" value="save"/>
    <input type="submit" value="New product"/>
</form>

</body>
</html>
