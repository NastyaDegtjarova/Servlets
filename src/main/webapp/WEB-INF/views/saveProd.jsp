<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
        <form action = "/prod" method="post">
            <input type = "text" name="name" value = ""/>
            <input type = "text" name="price" value = "0"/>
            <select size="3" name="manufId">
                <c:forEach items="${requestScope.manufs}" var="manuf">
                    <option value="${manuf.idManufact}">${manuf.nameManufact}</option>
                </c:forEach>
            </select>
            <input type = "hidden" name="action" value = "save"/>
            <input type = "submit" value = "Save"/>
        </form>
</body>
</html>
