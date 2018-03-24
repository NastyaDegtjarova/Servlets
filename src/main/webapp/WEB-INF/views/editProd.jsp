<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<form action = "/prod" method="post">
    <input type = "text" name="name" value = "${requestScope.name}"/>
    <input type = "text" name="price" value = "${requestScope.price}"/>
    <select size="3" name="manufId">
        <c:forEach items="${requestScope.manufs}" var="manuf">
            <c:if test="${manuf.idManufact == requestScope.manufIdSelect}">
                <option value="${manuf.idManufact}" selected>${manuf.nameManufact}</option>
            </c:if>
            <c:if test="${manuf.idManufact != requestScope.manufIdSelect}">
                <option value="${manuf.idManufact}">${manuf.nameManufact}</option>
            </c:if>
            <option value="${manuf.idManufact}">${manuf.nameManufact}</option>
        </c:forEach>
    </select>
    <input type = "hidden" name="action" value = "update"/>
    <input type = "hidden" name="id" value = "${requestScope.id}"/>
    <input type = "submit" value = "Save"/>
</form>
</body>
</html>
